package data.sim;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import sim.exceptions.ObstructInitCallException;
import sim.exceptions.ObstructInitRuntimeCallException;
import ui.events.IVidisEvent;
import ui.events.ObjectEvent;
import ui.model.impl.Packet;
import ui.model.structure.IVisObject;
import ui.mvc.api.Dispatcher;
import util.Logger;
import util.Logger.LogLevel;
import data.mod.IUserLink;
import data.mod.IUserNode;
import data.mod.IUserPacket;
import data.var.AVariable;
import data.var.vars.DefaultVariable;

public class SimPacket extends AComponent implements ISimPacketCon {

	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger( SimPacket.class );
	
    protected IUserPacket logic;

    // -------- sim fields ------- //
    private SimLink through;
    private SimNode from;
    private SimNode to;

    private IVisObject visObject;
    
    public SimPacket(IUserPacket packet, SimLink link, SimNode source,
	    SimNode target) {
		super();
		init();
		init(packet);
		// set variables
		setThrough(link);
		setFrom(source);
		setTo(target);
		// set 3d object; this (SimPacket) class should be fully initialized at this
		// call!
		// setObject3D(new Packet3D(this));
		
		visObject = new Packet( this );
		ObjectEvent nextEvent = new ObjectEvent( IVidisEvent.ObjectRegister, visObject );
		Dispatcher.forwardEvent( nextEvent );

    }

    private void init() {
		// yet nothing as we have no lists, maps or other composite objects
		// generate id for this packet
    }

    private void init(IUserPacket packet) {
		setLogic(packet);
		try {
		    packet.init(this);
		    initVars();
		} catch (ObstructInitCallException e) {
		    // never happens, if anyway throw a real severe exception
		    throw new ObstructInitRuntimeCallException(e.getCause());
		}
    }

    public void execute() {
		super.execute();
		if (!isSleeping()) {
		    this.logic.execute();
		} else
		    Logger.output(LogLevel.DEBUG, this, "skip logic.execute()");
		super.checkVariablesChanged();
    }

    public final IUserPacket getUserLogic() {
    	return logic;
    }

    public final double getAlpha() {
    	return getThrough().getAlphaForPacket(this);
    }

    public final int getDirection() {
    	return getThrough().getDirectionForPacket(this);
    }

    public final SimLink getThrough() {
    	return through;
    }

    private final void setFrom(SimNode from) {
    	this.from = from;
    }

    private final void setLogic(IUserPacket logic) {
    	this.logic = logic;
    }

    private final void setThrough(SimLink through) {
    	this.through = through;
    }

    private final void setTo(SimNode to) {
    	this.to = to;
    }

    public IUserNode getFrom() {
    	return from.getUserLogic();
    }

    public IUserNode getTo() {
    	return to.getUserLogic();
    }

    public IUserLink getLink() {
    	return through.getUserLogic();
    }
    
    private AVariable positionOverride() {
    	// FIXME
//		double alpha = getThrough().getAlphaForPacket(this);
		double direction = getThrough().getDirectionForPacket(this);
		SimNode nodeA = getThrough().getNodeASim();
		SimNode nodeB = getThrough().getNodeBSim();
		SimNode nodeFrom = null, nodeTo = null;
		if(direction > 0) {
			nodeFrom = nodeA;
			nodeTo = nodeB;
		} else {
			nodeFrom = nodeB;
			nodeTo = nodeA;
		}
		Point3d posFrom = (Point3d) nodeFrom.getVariableById(AVariable.COMMON_IDENTIFIERS.POSITION).getData();
		Point3d posTo = (Point3d) nodeTo.getVariableById(AVariable.COMMON_IDENTIFIERS.POSITION).getData();
		// calculate pos
		Point3d pos = null;
		long stepsOnLink = getThrough().getStepsOnLinkForPacket(this);
		Vector3d move = getThrough().getMove();
		Vector3d moveScaled = new Vector3d(move);
		moveScaled.scale(stepsOnLink);
		Vector3d posv = new Vector3d(posFrom);
		posv.add(moveScaled);
		
//		Vector3d way = new Vector3d();
//		way.sub(posTo, posFrom);
//		way.scale(alpha);
		pos = new Point3d(posv);
		
    	
    	
    	
		// set var or register var
		if(hasVariable(AVariable.COMMON_IDENTIFIERS.POSITION)) {
			// update
			((DefaultVariable)super.getVariableById(AVariable.COMMON_IDENTIFIERS.POSITION)).update(pos);
		} else {
			registerVariable(new DefaultVariable(AVariable.COMMON_IDENTIFIERS.POSITION, pos));
		}
		return super.getVariableById(AVariable.COMMON_IDENTIFIERS.POSITION);
    }
    
    @Override
    public AVariable getVariableById(String id) throws ClassCastException {
    	if(id.equals(AVariable.COMMON_IDENTIFIERS.POSITION)) {
    		return positionOverride();
    	} else {
    		return super.getVariableById(id);
    	}
    }
    
    @Override
    public void kill() {
    	super.kill();
    	logger.info("");
    	ObjectEvent oe = new ObjectEvent( IVidisEvent.ObjectUnregister, this.visObject );
    	Dispatcher.forwardEvent( oe );
    }
}