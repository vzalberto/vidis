package vidis.sim.framework.observe.events;

import java.util.LinkedList;
import java.util.List;

import vidis.sim.framework.components.SimulatorComponentLink;
import vidis.sim.framework.components.SimulatorComponentNode;
import vidis.sim.framework.interfaces.SimulatorComponentInt;
import vidis.sim.framework.observe.SimulatorEventInt;
import vidis.sim.util.Utility;

/**
 * send packet event
 * @author dominik
 *
 */
public class NodeConnectEvent implements SimulatorEventInt {
	/**
	 * the time when this event occurred
	 */
	private long time;
	
	private SimulatorComponentNode who;
	
	private SimulatorComponentNode to;

	private SimulatorComponentLink link;
	
	/**
	 * public constructor
	 */
	private NodeConnectEvent() {
		// retrieve and set the time
		this.setTime(Utility.getTime());
	}
	
	public NodeConnectEvent(SimulatorComponentLink link, SimulatorComponentNode who, SimulatorComponentNode to) {
		this();
		this.setLink(link);
		this.setFrom(who);
		this.setTo(to);
	}

	private void setLink(SimulatorComponentLink link) {
		this.link = link;
	}

	/**
	 * set the time
	 * @param time timestamp (long) in milliseconds
	 */
	private void setTime(long time) {
		this.time = time;
	}
	
	public long getTime() {
		// TODO Auto-generated method stub
		return time;
	}
	
	private void setFrom(SimulatorComponentNode who) {
		this.who = who;
	}
	
	private void setTo(SimulatorComponentNode to) {
		this.to = to;
	}
	
	public SimulatorComponentLink getLink() {
		return link;
	}
	
	public SimulatorComponentNode getWho() {
		return who;
	}
	
	public SimulatorComponentNode getTo() {
		return to;
	}
	
	public List<SimulatorComponentInt> getInvolvedComponents() {
		LinkedList<SimulatorComponentInt> list = new LinkedList<SimulatorComponentInt>();
		list.add(this.getLink());
		list.add(this.getWho());
		list.add(this.getTo());
		return list;
	}
	
	public String toXML() {
		return "<connect>"+getLink().toXML()+"</connect>";
	}
	
	public String toString() {
		return "NodeConnect@"+getTime()+"{"+getWho().toXML()+"->"+getTo().toXML()+"}";
	}
}
