package modules.vectorClockAlgorithm;

import util.Logger;
import util.Logger.LogLevel;
import data.AUserNode;
import data.annotation.ComponentInfo;
import data.annotation.Display;
import data.mod.IUserLink;
import data.mod.IUserPacket;

@ComponentInfo(name = "FloodNode")
public class VectorClockAlgorithmNode extends AUserNode {
    private VectorTime localTimeVector = new VectorTime(this, 0);

    @Display(name = "tVector")
    public VectorTime getTimeVector() {
    	return localTimeVector;
    }
    
    @Display(name = "lTime")
    public int getLocalTime() {
    	return localTimeVector.getNodeTime(this);
    }
    
    public void execute() {
		// in 3% of all cases do 'some action' and increase local time
		// then notify the neighbors
		if (Math.random() < 0.03d) {
		    increaseLocalTime();
		    for (IUserLink link : this.getConnectedLinks()) {
		    	send(link, new VectorClockAlgorithmPacket(getTimeVector()));
		    }
		}
    }

    private void increaseLocalTime() {
    	getTimeVector().update(this, getLocalTime()+1);
	}

	/**
     * simply wraps the send functionality to unify advanced parameters such as processing time
     * @param link the link to send over
     * @param packet the packet to send
     */
    private void send(IUserLink link, VectorClockAlgorithmPacket packet) {
    	send(packet, link, 1 + (long) (Math.random() * 2));
    }

    private void receive(VectorClockAlgorithmPacket packet) {
    	increaseLocalTime();
		VectorTime remoteTimeVector = packet.getTime();
		// update my time despite on the time we receive
		getTimeVector().update(remoteTimeVector);
    }

    public void receive(IUserPacket packet) {
		if (packet instanceof VectorClockAlgorithmPacket) {
		    receive((VectorClockAlgorithmPacket) packet);
		} else {
		    Logger.output(LogLevel.ERROR, this, "receive 'unknown' packet from " + packet.getSource());
		}
    }
}
