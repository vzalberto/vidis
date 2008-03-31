package vidis.sim.framework.observe.events;

import java.util.LinkedList;
import java.util.List;

import vidis.sim.framework.components.SimulatorComponentLink;
import vidis.sim.framework.interfaces.SimulatorComponentInt;
import vidis.sim.framework.observe.SimulatorEventInt;
import vidis.sim.util.Utility;

/**
 * node disconnect event
 * 
 * NOTE: CURRENTLY ONLY PLACEHOLDER AND STUB FOR NEW EVENTS
 * @author dominik
 *
 */
public class NodeDisconnectEvent implements SimulatorEventInt {
	/**
	 * the time when this event occurred
	 */
	private long time;

	private SimulatorComponentLink link;
	
	/**
	 * public constructor
	 */
	private NodeDisconnectEvent() {
		// retrieve and set the time
		this.setTime(Utility.getTime());
	}
	
	public NodeDisconnectEvent(SimulatorComponentLink link) {
		this();
		this.setLink(link);
	}
	
	private void setLink(SimulatorComponentLink link) {
		this.link = link;
	}
	
	public SimulatorComponentLink getLink() {
		return link;
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
	
	public List<SimulatorComponentInt> getInvolvedComponents() {
		LinkedList<SimulatorComponentInt> list = new LinkedList<SimulatorComponentInt>();
		// TODO nobody involved, add them here!
		return list;
	}
	
	public String toXML() {
		return "<disconnect>"+this.getLink().toXML()+"</disconnect>";
	}
}
