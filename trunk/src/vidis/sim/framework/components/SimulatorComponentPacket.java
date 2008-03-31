package vidis.sim.framework.components;

import vidis.sim.framework.interfaces.ComponentFactoryInt;
import vidis.sim.framework.interfaces.SimulatorComponentInt;
import vidis.sim.framework.observe.SimulatorEventInt;

/**
 * basic packet class
 * 
 * implements basic packet functionality
 * 
 * NOTE FOR MODULE WRITERS:
 * USE THIS CLASS TO IMPLEMENT A NEW MODULE PACKET!
 * @author dominik
 *
 */
public abstract class SimulatorComponentPacket implements SimulatorComponentInt {
	/**
	 * the simulator factory
	 */
	private ComponentFactoryInt factory;
	
	/**
	 * field from who the packet was generated
	 */
	private SimulatorComponentNode from;
	
	/**
	 * field to who the packet goes
	 */
	private SimulatorComponentNode to;
	
	/**
	 * time
	 */
	private long time;
	
	/**
	 * component id
	 */
	private int cid = this.hashCode();
	
	/**
	 * default constructor
	 */
	private SimulatorComponentPacket() {
		// set default values here
	}

	/**
	 * constructor
	 * @param from who the packet was generated from
	 */
	public SimulatorComponentPacket(ComponentFactoryInt factory, SimulatorComponentNode from, SimulatorComponentNode to, long time) {
		this();
		this.setFactory(factory);
		this.setFrom(from);
		this.setTo(to);
		this.setTime(time);
	}
	
	/**
	 * set the factory that should be used by this class
	 * a factory provides the correct classes that should be
	 * used throughout the used module
	 * @param factory factory to use
	 */
	public void setFactory(ComponentFactoryInt factory) {
		this.factory = factory;
	}
	
	/**
	 * get the factory used by this class
	 * @return the factory
	 */
	public ComponentFactoryInt getFactory() {
		return factory;
	}
	
	/**
	 * private setter for the from field
	 * @param from who the packet was generated from
	 */
	private void setFrom(SimulatorComponentNode from) {
		this.from = from;
	}
	/**
	 * retrieve who generated the packet
	 * @return who generated the packet
	 */
	public SimulatorComponentNode getFrom() {
		return this.from;
	}
	
	/**
	 * private setter for the to field
	 * @param to who the packet was targeted to
	 */
	private void setTo(SimulatorComponentNode to) {
		this.to = to;
	}
	/**
	 * retrieve who generated the packet
	 * @return who generated the packet
	 */
	public SimulatorComponentNode getTo() {
		return this.to;
	}
	
	/**
	 * to string function
	 */
	public String toString() {
		return "Packet#"+super.hashCode();
	}
	
	/**
	 * set time this packet was generated
	 * @param time time (long)
	 */
	private void setTime(long time) {
		this.time = time;
	}
	
	public void informAll(SimulatorEventInt event) {
		this.factory.beInformed(event);
	}
	
	public String toXML() {
		return "<packet>"+this.toXML_CID()+"<from>"+this.getFrom().toXML_byREF()+"</from><to>"+this.getTo().toXML_byREF()+"</to>"+"</packet>";
	}

	/**
	 * get the time when this packet was generated
	 * @return time (long)
	 */
	public long getTime() {
		return time;
	}
	
	public void setCID(int cid) {
		this.cid = cid;
	}
	
	public int getCID() {
		return this.cid;
	}
	
	public String toXML_CID() {
		return "<id>" + this.getCID() + "</id>";
	}
	
	public String toXML_byREF() {
		return "<packet>"+this.toXML_CID()+"</packet>";
	}
}
