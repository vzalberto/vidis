package data.sim;

import java.util.LinkedList;
import java.util.List;

import javax.vecmath.Tuple3d;
import javax.vecmath.Vector3d;

import org.apache.log4j.Logger;

import sim.exceptions.ObstructInitCallException;
import sim.exceptions.ObstructInitRuntimeCallException;
import data.mod.IUserLink;
import data.mod.IUserNode;
import data.var.AVariable;

public class SimLink extends AComponent implements ISimLinkCon {
	
	private static Logger logger = Logger.getLogger( SimLink.class );

    protected IUserLink logic;

    // -------- sim fields ------- //
    private SimNode a;
    private SimNode b;
    private long delay;
    private List<PacketQueueHolder> queue;

    public SimLink(IUserLink link, long delay) {
		super();
		init();
		init(link);
		// set fields
		setDelay(delay);
    }

    private void init() {
    	this.queue = new LinkedList<PacketQueueHolder>();
    }

    @Override
    public void kill() {
		synchronized (queue) {
		    for (PacketQueueHolder pqh : queue) {
			pqh.packet.kill();
		    }
		    queue.clear();
		}
		super.kill();
    }

    private void init(IUserLink link) {
		this.logic = link;
		try {
		    link.init(this);
		    initVars();
		} catch (ObstructInitCallException e) {
		    // never happens, if anyway throw a real severe exception
		    throw new ObstructInitRuntimeCallException(e.getCause());
		}
    }

    private void setDelay(long delay) {
    	this.delay = delay;
    }

    public void connect(SimNode a, SimNode b) {
		this.a = a;
		this.b = b;
    }

    public void execute() {
		processQueue();
		super.execute();
		if (!isSleeping()) {
		    this.logic.execute();
		} else
		    logger.debug("skip logic.execute()");
		super.checkVariablesChanged();
    }

	public IUserNode getNodeA() {
    	return a.getUserLogic();
    }

    public IUserNode getNodeB() {
    	return b.getUserLogic();
    }

    /**
     * retrieve the other node connected to this link
     * 
     * @param node
     *          this is me
     * @return the other node
     */
    public final SimNode getOtherNode(SimNode node) {
		if (getNodeASim().compareTo(node) == 0) {
		    return getNodeBSim();
		} else if (getNodeBSim().compareTo(node) == 0) {
		    return getNodeASim();
		} else {
		    logger.debug("getOtherNode(" + node
			    + ") => null");
		    return null;
		}
    }

    /**
     * retrieve the user link contained in this simulator link
     * 
     * @return the user link
     */
    public final IUserLink getUserLogic() {
    	return this.logic;
    }

    /**
     * retrieve the delay of this node
     * 
     * @return the delay
     */
    public final long getDelay() {
    	return this.delay;
    }

    public void send(SimPacket packet, SimNode to) {
		if (a.equals(to) || b.equals(to)) {
		    if (getDelay() <= 0) {
			// deliver immediatly
			deliver(packet, to);
		    } else {
			queue(packet, to);
		    }
		}
    }

    private void queue(SimPacket packet, SimNode to) {
    	queue.add(new PacketQueueHolder(packet, to, getDelay()));
    }

    private void deliver(SimPacket packet, SimNode to) {
    	to.receive(packet);
    }

    private void processQueue() {
		synchronized (queue) {
		    for (int i = 0; i < queue.size(); i++) {
			PacketQueueHolder tmp = queue.get(i);
			/*
			 * 
			 * if (getVariableById(String.class,
			 * "system.id").getData().compareToIgnoreCase("link11") == 0)
			 * Logger.output(LogLevel.DEBUG, this, "checkQueue(" + i + ":" +
			 * tmp.timeout + ":" + tmp.packet + ")");
			 */
			if (tmp.timeout < 0) {
			    // deliver
			    deliver(tmp.packet, tmp.to);
			    // remove from queue
			    if (queue.remove(tmp)) {
				i--;
			    }
			} else {
			    // queue
			    tmp.timeout--;
			}
		    }
		}
    }

    private static class PacketQueueHolder {
		public PacketQueueHolder(SimPacket packet, SimNode to, long timeout) {
		    this.packet = packet;
		    this.to = to;
		    this.timeout = timeout;
		    // this.startMillis = System.currentTimeMillis();
		    // this.lastMillis = System.currentTimeMillis();
		}
	
		SimPacket packet;
		SimNode to;
		long timeout;
		// long startMillis;
		// long lastMillis;
    }

    private PacketQueueHolder getPacketQueueHolderForPacket(SimPacket packet) {
		for (int i = 0; i < queue.size(); i++) {
		    PacketQueueHolder tmp = queue.get(i);
		    if (tmp.packet.equals(packet)) {
			return tmp;
		    }
		}
		return null;
    }

    
    public long getStepsOnLinkForPacket(SimPacket packet) {
    	logger.debug("getStepsOnLinkForPacket("+packet+");");
		PacketQueueHolder tmp = getPacketQueueHolderForPacket(packet);
		if (tmp != null) {
		    int dir = packet.getDirection();
		    // long durationLastStep = Simulator.getInstance().getLastStepDuration();
		    // long timeStart = tmp.startMillis;
		    long stepsToTarget = tmp.timeout;
		    long length = this.delay;
	
		    return length - stepsToTarget;
		}
		return -1;
    }
    
    /**
     * determine the alpha position of a packet
     * 
     * @param packet
     *          the packet to probe
     * @return -1: unknown, otherwise value in [0.0, 1.0]
     */
    // private double getSimpleAlphaForPacket(SimPacket packet) {
    // PacketQueueHolder tmp = getPacketQueueHolderForPacket(packet);
    // if (tmp != null) {
    // return (double) tmp.timeout / (double) getDelay();
    // }
    // return -1;
    // }
    // private long lastAlphaRealTime, lastRealTimeDiff;
    // private long lastTime = 0;
    // private double lastAlpha, deltaAlpha;
    public double getAlphaForPacket(SimPacket packet) {
    	logger.debug("getAlphaForPacket("+packet+");");
		PacketQueueHolder tmp = getPacketQueueHolderForPacket(packet);
		if (tmp != null) {
		    int dir = packet.getDirection();
		    // long durationLastStep = Simulator.getInstance().getLastStepDuration();
		    // long timeStart = tmp.startMillis;
		    long stepsToTarget = tmp.timeout;
		    long length = this.delay;
	
		    long stepsOnLink = length - stepsToTarget;
	
		    /*
		     * double alpha=0; long now = Simulator.getInstance().getNow(); if
		     * (lastTime == now) { // interpolate long nowReal =
		     * System.currentTimeMillis()-lastAlphaRealTime; double smallAlpha =
		     * (double) nowReal /(double) lastRealTimeDiff * deltaAlpha; if(dir < 0) {
		     * smallAlpha = deltaAlpha-smallAlpha; //dark magic alpha calculation
		     * alpha = lastAlpha + deltaAlpha - smallAlpha; } else{ alpha = lastAlpha +
		     * smallAlpha; } } else { // calculate double delta = tmp.timeout; double
		     * max = delay; alpha = delta/max; if(dir < 0) alpha = 1.0-alpha;
		     * deltaAlpha = alpha-lastAlpha; lastAlpha = alpha; lastRealTimeDiff =
		     * System.currentTimeMillis() - lastAlphaRealTime; lastAlphaRealTime =
		     * System.currentTimeMillis(); }
		     */
	
		    /*
		     * // 1/alpha = timeOnLink/timeSpendOnLink long timeSpendOnLink =
		     * System.currentTimeMillis() - timeStart; long timeOnLink =
		     * durationLastStep * length; double alpha = ((double)timeSpendOnLink) /
		     * ((double)timeOnLink);
		     */
	
		    // grobe berechnung:
		    // 1/alpha_g = length / stepsOnLink
		    double alpha = ((double) stepsOnLink) / ((double) length);
		    /*
		     * // feine berechnung: double alpha_f_max = 1d / ((double) length); long
		     * timeOnLink = System.currentTimeMillis() - timeStart;
		     * 
		     * long timeOnThisStep = timeOnLink - (stepsOnLink * durationLastStep); //
		     * alpha_f_max/alpha_f = durationLastStep / timeOnThisStep double alpha_f =
		     * alpha_f_max * timeOnThisStep / durationLastStep; if (alpha_f<0)
		     * alpha_f = 0; if (alpha_f>alpha_f_max) alpha_f = alpha_f_max; double
		     * alpha = alpha_g + alpha_f;
		     */
		    // long timeOnLink = stepsOnLink * durationLastStep;
		    // //long timeSpentOnLink = (stepsOnLink - stepsToGo) * durationLastStep;
		    // long timeSpentOnLink = System.currentTimeMillis() - timeStart;
		    // // 1/alpha = timeOnLink/timeSpendOnLink
		    // double alpha = (double)timeSpentOnLink / (double)timeOnLink;
		    if (dir == 0)
			logger.debug("figfut");
		    // if (dir < 0)
		    // alpha = 1.0 - alpha;
		    // Logger.output(this, "alpha = "+alpha);
		    if (alpha > 1)
			alpha = 1;
		    if (alpha < 0)
			alpha = 0;
		    return alpha;
		    // estimated millis left
		    // double millisUntilNow = (tmp.lastMillis - tmp.startMillis);
		    // double stepsUntilNow = getDelay() - tmp.timeout;
		    // double millisPerStep = millisUntilNow / stepsUntilNow;
		    // double stepsLeft = tmp.timeout;
		    // double millisLeft = millisPerStep * stepsLeft;
		    // double millisTotal = millisUntilNow + millisLeft;
		    // double alpha = millisUntilNow / millisTotal;
		    // return alpha;
		}
		return -1;
    }

    /**
     * determine the direction of a packet
     * 
     * @param packet
     *          the packet to find
     * @return 0: unknown, 1: to A, -1: to B
     */
    public int getDirectionForPacket(SimPacket packet) {
    	logger.debug("getDirectionForPacket("+packet+");");
		PacketQueueHolder tmp = getPacketQueueHolderForPacket(packet);
		if (tmp != null) {
		    if (tmp.to.equals(a)) {
			return 1;
		    } else if (tmp.to.equals(b)) {
			return -1;
		    }
		}
		return 0;
    }

    /**
     * Bewegungsvector skaliert auf 1 Simulator Step
     */
    private Vector3d move = null;

    public Vector3d getMove() {
    	logger.debug("getMove()");
		if (move == null)
		    calcMove();
		return move;
    }

    public void calcMove() {
    	logger.debug("calcMove()");
		// TODO safe checks
		Tuple3d a = (Tuple3d) getNodeA().getVariable(AVariable.COMMON_IDENTIFIERS.POSITION).getData();
		Tuple3d b = (Tuple3d) getNodeB().getVariable(AVariable.COMMON_IDENTIFIERS.POSITION).getData();
		Vector3d AB = new Vector3d(b);
		AB.sub(a);
		Vector3d moveSimStep = new Vector3d(AB);
		moveSimStep.scale(1d / delay);
		// grob zum testen
		move = moveSimStep;
    		
    }

    public SimNode getNodeASim() {
    	return a;
    }

    public SimNode getNodeBSim() {
    	return b;
    }

    public String toString() {
		return super.toString() + "{" + getNodeASim() + "-" + getNodeBSim()
			+ "}";
    }
}
