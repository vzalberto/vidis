package vidis.sim.framework.components;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import vidis.sim.framework.interfaces.ComponentFactoryInt;
import vidis.sim.framework.interfaces.SimulatorComponentInt;
import vidis.sim.framework.observe.SimulatorEventInt;
import vidis.sim.framework.observe.events.NodeConnectEvent;
import vidis.sim.util.Utility;

/**
 * basic wire class
 * 
 * implements wire function (connects two nodes)
 * 
 * NOTE FOR MODULE WRITERS:
 * USE THIS CLASS TO IMPLEMENT A NEW MODULE LINK!
 * @author dominik
 *
 */
public abstract class SimulatorComponentLink implements SimulatorComponentInt {
	/**
	 * internal class that hides stupid network sending by packing the packets
	 * @author dominik
	 *
	 */
	private class PacketHolder {
		/**
		 * received flag
		 */
		private boolean received;
		
		/**
		 * data (packet)
		 */
		private SimulatorComponentPacket packet;
		
		/**
		 * default constructor
		 * @param packet data
		 */
		public PacketHolder(SimulatorComponentPacket packet) {
			this.setReceived(false);
			this.setPacket(packet);
		}
		
		/**
		 * set received flag
		 * @param received true or false
		 */
		private void setReceived(boolean received) {
			this.received = received;
		}
		
		/**
		 * set data packet
		 * @param packet the packet
		 */
		private void setPacket(SimulatorComponentPacket packet) {
			this.packet = packet;
		}
		
		/**
		 * get received flag
		 * @return true or false
		 */
		public boolean getReceived() {
			return received;
		}

		/**
		 * set the packet: received
		 */
		public void received() {
			this.setReceived(true);
		}

		public SimulatorComponentPacket getData() {
			return packet;
		}
	}
	/**
	 * the simulator factory
	 */
	private ComponentFactoryInt factory;
	
	/**
	 * node this link is pointed from
	 */
	private SimulatorComponentNode nodeFrom;
	
	/**
	 * node this links is pointed to
	 */
	private SimulatorComponentNode nodeTo;
	
	/**
	 * packet holder instance, maps from "timestamp" -> list(packets)
	 */
	private HashMap<Long, LinkedList<PacketHolder>> packets;
	
	/**
	 * determines the delay the wire has
	 * to send a packet from one node to another
	 */
	private long delay;

	/**
	 * component id
	 */
	private int cid = this.hashCode();
	
	private SimulatorComponentLink() {
		this.packets = new HashMap<Long, LinkedList<PacketHolder>>();
	}
	
	private SimulatorComponentLink(long delay) {
		this();
		this.setDelay(delay);
	}
	
	/**
	 * constructor of this wire
	 * @param delay delay in milliseconds
	 */
	public SimulatorComponentLink(ComponentFactoryInt factory, long delay) {
		this(delay);
		this.setFactory(factory);
	}
	
	/**
	 * retrieve packets
	 * @return hash map (long -> linked list(packets))
	 */
	private HashMap<Long, LinkedList<PacketHolder>> getPackets() {
		return this.packets;
	}
	
	/**
	 * adds a new time node to the packet holder instance
	 * @param millis time stamp
	 * @return true or false
	 */
	private boolean packetsAddNewTimeNode(Long millis) {
		if(this.packetsTimeNodeExists(millis)) {
			return true;
		} else {
			this.getPackets().put(millis, new LinkedList<PacketHolder>());
			return true;
		}
	}
	
	/**
	 * retrieves if a packet time node exists
	 * @param millis the time node in millis
	 * @return true or false
	 */
	private boolean packetsTimeNodeExists(Long millis) {
		return this.getPackets().containsKey(millis);
	}
	
	/**
	 * add new packet to a time node
	 * @param millis the time node
	 * @param packet the packet to add
	 * @return true or false
	 */
	private boolean packetsAddNewPacket(Long millis, PacketHolder packet) {
		if(this.packetsAddNewTimeNode(millis)) {
			return this.getPackets().get(millis).add(packet);
		}
		return false;
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
	 * send a data packet from node to all nodes connected to this wire
	 * (usually only one node is connected)
	 * @param data t	he data packet sent
	 */
	public void push(SimulatorComponentPacket data) {
		if(this.packetsAddNewPacket(Utility.getTime(), new PacketHolder(data))) {
			// added, check on execute if packets arrived
		}
	}
	
	/**
	 * wird von simulator angestossen
	 */
	public boolean execute() {
		Long millis = Utility.getTime();
		for(PacketHolder packet : this.getPacketsBefore(millis)) {
			this.getNodeTo().receive(this, packet.getData());
			// set this packet to received
			packet.received();
		}
		return true;
	}
	
	public SimulatorComponentNode getNodeFrom() {
		return nodeFrom;
	}

	public SimulatorComponentNode getNodeTo() {
		return nodeTo;
	}

	/**
	 * retrieves all packets before 
	 * @param currentTimeMillis
	 */
	private List<PacketHolder> getPacketsBefore(long currentTimeMillis) {
		LinkedList<PacketHolder> list = new LinkedList<PacketHolder>();
		for(Long time : this.getPackets().keySet()) {
			if(time+this.getDelay() < currentTimeMillis) {
				// push element
				for(PacketHolder packet : this.getPackets().get(time)) {
					if(!packet.getReceived()) {
						list.add(packet);
					}
				}
			}
		}
		return list;
	}

	/**
	 * connect a node to this wire
	 * @param from one end of the node to connect
	 * @param to the other end of the connection
	 * @return true or false
	 */
	public boolean connect(SimulatorComponentNode from, SimulatorComponentNode to) {
		this.nodeFrom = from;
		this.nodeTo = to;
		this.informAll(new NodeConnectEvent(this, from, to));
		return true;
	}
	
	/**
	 * set the delay of this wire in milliseconds
	 * @param delay delay in milliseconds
	 */
	public void setDelay(long delay) {
		this.delay = delay;
	}
	
	/**
	 * retrieve the delay of this wire
	 * @return delay in milliseconds
	 */
	public long getDelay() {
		return this.delay;
	}
	
	public void informAll(SimulatorEventInt event) {
		this.factory.beInformed(event);
	}
	
	public String toXML() {
		return "<link>"+this.toXML_CID()+"<from>"+this.getNodeFrom().toXML_byREF()+"</from><to>"+this.getNodeTo().toXML_byREF()+"</to>" + "</link>";
	}
	
	/**
	 * simple to string function
	 */
	public String toString() {
		return "Link#"+this.hashCode();
	}
	
	public void setCID(int CID) {
		this.cid = CID;
	}
	
	public int getCID() {
		return this.cid;
	}
	
	public String toXML_CID() {
		return "<id>" + this.getCID() + "</id>";
	}
	
	public String toXML_byREF() {
		return "<link>"+this.toXML_CID()+"</link>";
	}
}
