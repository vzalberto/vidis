package vidis.sim.framework.components;

import java.util.LinkedList;

import vidis.sim.framework.interfaces.ComponentFactoryInt;
import vidis.sim.framework.interfaces.SimulatorComponentInt;
import vidis.sim.framework.observe.SimulatorEventInt;
import vidis.sim.framework.observe.events.PacketReceiveEvent;
import vidis.sim.framework.observe.events.PacketSendEvent;

/**
 * basic node class
 * 
 * implements basic node functionality
 * 
 * NOTE FOR MODULE WRITERS:
 * USE THIS CLASS TO IMPLEMENT A NEW MODULE NODE!
 * @author dominik
 *
 */
public abstract class SimulatorComponentNode implements SimulatorComponentInt {
	
	/**
	 * the factory that knows about the needed classes
	 */
	private ComponentFactoryInt factory;
	
	/**
	 * determines how many connections this node can handle
	 */
	private static final int WIRES_MAX = 10;
	
	/**
	 * list of all wires connected to this node
	 */
	private LinkedList<SimulatorComponentLink> links;
	
	/**
	 * empty constructor
	 */
	private SimulatorComponentNode() {
		this.links = new LinkedList<SimulatorComponentLink>();
	}
	
	/**
	 * basic constructor
	 */
	public SimulatorComponentNode(ComponentFactoryInt factory) {
		this();
		this.setFactory(factory);
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
	 * 
	 * @return count of outgoing connections
	 */
	public int getConnectionsCount() {
		return this.getLinks().size();
	}
	
	/**
	 * check if this node can handle more connections
	 * @return true or false
	 */
	public boolean hasAvailableConnections() {
		return this.getConnectionsCount() < WIRES_MAX;
	}
	
	/**
	 * retrieves if this class has connections
	 * @return true or false
	 */
	private boolean hasOpenConnections() {
		return this.getConnectionsCount() > 0;
	}
	
	/**
	 * accept a connection, used by bidirectional pipes
	 * @param link to accept
	 * @return true or false
	 */
	public boolean accept(SimulatorComponentLink link) {
		return this.getLinks().add(link);
	}
	
	/**
	 * connect to another node unidirectional
	 * @param node node to connect to
	 * @param delay the delay this wire has
	 * @return true or false
	 */
	public boolean connect(SimulatorComponentNode node, long delay) {
		if(this.hasAvailableConnections()) {
			// create new link
			SimulatorComponentLink link = this.getFactory().getNewLink(delay);
			link.connect(this, node);
			// accept link here
			if(this.accept(link)) {
				// inform all
				//this.informAll(new NodeConnectEvent(this, node));
				return true;
			}
		}
		return false;
	}
	
	/**
	 * connect to another node bidirectional (other node accepts)
	 * @param node node to connect to
	 * @param delay the timeout (ms) of delay
	 * @return true or false
	 */
	public boolean connect_bidi(SimulatorComponentNode node, int delay) {
		if(this.hasAvailableConnections()) {
			// create another new link
			SimulatorComponentLink link2 = this.getFactory().getNewLink(delay);
			link2.connect(node, this);
			// accept link remotely (if false, no more slots available)
			if(node.accept(link2)) {
				//this.informAll(new NodeConnectEvent(this, node));
				// create new link
				SimulatorComponentLink link = this.getFactory().getNewLink(delay);
				link.connect(this, node);
				// accept locally
				if(this.accept(link)) {
					//this.informAll(new NodeConnectEvent(node, this));
					// established bidirectional link (both can send over this one)
					return true;
				}
			}
		}
		return false;
	}
	
	private LinkedList<SimulatorComponentLink> getLinks() {
		return this.links;
	}
	
	/**
	 * send a packet over all connections
	 * @param packet
	 */
	public void send(SimulatorComponentPacket packet) {
		// check if there are open connections
		if(this.hasOpenConnections()) {
			// if so, push the packet to all open links
			for(SimulatorComponentLink link : this.getLinks()) {
				this.informAll(new PacketSendEvent(link, packet));
				link.push(packet);
			}
		}
	}

	/**
	 * receives a packet from a incoming stream
	 * @param packet
	 */
	public void receive(SimulatorComponentLink from, SimulatorComponentPacket packet) {
		//Const.STD_ERR.println("RCV " + packet);
		this.informAll(new PacketReceiveEvent(from, packet));
	}
	
	public void informAll(SimulatorEventInt event) {
		this.factory.beInformed(event);
	}
	
	public String toXML() {
		return "<node>"+this.toXML_CID()+"</node>";
	}
	
	/**
	 * simple to string function
	 */
	public String toString() {
		return "Node#"+this.hashCode() + "{"+getLinks()+"}";
	}
	
	public int getCID() {
		return this.hashCode();
	}
	
	public String toXML_CID() {
		return "<id>" + this.getCID() + "</id>";
	}

	public String toXML_byREF() {
		return "<node>"+this.toXML_CID()+"</node>";
	}
}
