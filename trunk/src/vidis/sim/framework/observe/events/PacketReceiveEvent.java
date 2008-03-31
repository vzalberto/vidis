package vidis.sim.framework.observe.events;

import java.util.LinkedList;
import java.util.List;

import vidis.sim.framework.components.SimulatorComponentLink;
import vidis.sim.framework.components.SimulatorComponentPacket;
import vidis.sim.framework.interfaces.SimulatorComponentInt;
import vidis.sim.framework.observe.SimulatorEventInt;
import vidis.sim.util.Utility;

/**
 * send packet event
 * @author dominik
 *
 */
public class PacketReceiveEvent implements SimulatorEventInt {
	/**
	 * the time when this event occurred
	 */
	private long time;
	
	/**
	 * the data packet contained by the event
	 */
	private SimulatorComponentPacket packet;
	
	/**
	 * over which link this packet has been sent
	 */
	private SimulatorComponentLink over;
	
	/**
	 * public constructor
	 * @param packet 
	 */
	public PacketReceiveEvent(SimulatorComponentLink over, SimulatorComponentPacket packet) {
		// retrieve and set the time
		this.setTime(Utility.getTime());
		this.setPacket(packet);
		this.setOver(over);
	}
	
	/**
	 * set the time
	 * @param time timestamp (long) in milliseconds
	 */
	private void setTime(long time) {
		this.time = time;
	}
	
	public long getTime() {
		return time;
	}
	
	private void setPacket(SimulatorComponentPacket packet) {
		this.packet = packet;
	}
	
	public SimulatorComponentPacket getPacket() {
		return this.packet;
	}
	
	private void setOver(SimulatorComponentLink over) {
		this.over = over;
	}
	
	public SimulatorComponentLink getOver() {
		return this.over;
	}
	
	public List<SimulatorComponentInt> getInvolvedComponents() {
		LinkedList<SimulatorComponentInt> list = new LinkedList<SimulatorComponentInt>();
		list.add(this.getPacket());
		return list;
	}
	
	public String toXML() {
		return "<receive>"+getPacket().toXML()+"</receive>";
	}
	
	public String toString() {
		return "PacketReceive@"+getTime()+"{" + getPacket() + "}";
	}
}
