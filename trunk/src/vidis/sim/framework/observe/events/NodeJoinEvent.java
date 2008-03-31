package vidis.sim.framework.observe.events;

import java.util.LinkedList;
import java.util.List;

import vidis.sim.framework.components.SimulatorComponentNode;
import vidis.sim.framework.interfaces.SimulatorComponentInt;
import vidis.sim.framework.observe.SimulatorEventInt;
import vidis.sim.util.Utility;

/**
 * send packet event
 * @author dominik
 *
 */
public class NodeJoinEvent implements SimulatorEventInt {
	/**
	 * the time when this event occurred
	 */
	private long time;
	
	/**
	 * who joined?
	 */
	private SimulatorComponentNode who;
	
	/**
	 * public constructor
	 */
	private NodeJoinEvent() {
		// retrieve and set the time
		this.setTime(Utility.getTime());
	}
	
	public NodeJoinEvent(SimulatorComponentNode who) {
		this();
		this.setWho(who);
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
	
	private void setWho(SimulatorComponentNode who) {
		this.who = who;
	}
	
	/**
	 * get who joined
	 * @return the node that joined
	 */
	public SimulatorComponentNode getWho() {
		return who;
	}
	
	public List<SimulatorComponentInt> getInvolvedComponents() {
		LinkedList<SimulatorComponentInt> list = new LinkedList<SimulatorComponentInt>();
		list.add(this.getWho());
		return list;
	}
	
	public String toXML() {
		return "<join>"+getWho().toXML()+"</join>";
	}
	
	public String toString() {
		return "NodeJoin@"+getTime()+"{" + getWho() + "}";
	}
}
