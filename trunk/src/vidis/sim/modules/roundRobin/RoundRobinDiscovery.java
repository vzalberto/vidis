package vidis.sim.modules.roundRobin;

import java.util.LinkedList;
import java.util.List;

import vidis.sim.framework.components.SimulatorComponentFactory;
import vidis.sim.framework.components.SimulatorComponentLink;
import vidis.sim.framework.components.SimulatorComponentNode;
import vidis.sim.framework.components.SimulatorComponentPacket;
import vidis.sim.framework.interfaces.SimulatorComponentInt;
import vidis.sim.framework.observe.events.NodeJoinEvent;

/**
 * demo factory class
 * @author dominik
 *
 */
public class RoundRobinDiscovery extends SimulatorComponentFactory {
	
	private LinkedList<SimulatorComponentInt> components = new LinkedList<SimulatorComponentInt>();
	private int nodes;
	
	public RoundRobinDiscovery(int nodes) {
		this.nodes = nodes;
	}
	
	public void init() {
		// create nodes
		for(int i=0; i<nodes; i++) {
			@SuppressWarnings("unused")
			SimulatorComponentNode node = this.getNewNode();
		}
		for(int i=0; i<this.getComponents().size(); i++) {
			for(int j=0; j<this.getComponents().size(); j++) {
				if(this.getComponents().get(i) instanceof SimulatorComponentNode) {
					if(this.getComponents().get(j) instanceof SimulatorComponentNode) {
						if(!this.getComponents().get(i).equals(this.getComponents().get(j))) {
							// connect
							SimulatorComponentNode node1 = (SimulatorComponentNode)this.getComponents().get(i);
							SimulatorComponentNode node2 = (SimulatorComponentNode)this.getComponents().get(j);
							node1.connect(node2, (long)Math.max(10, Math.random()*250));
						}
					}
				}
			}
		}
		// get nodes
		// SimulatorComponentNode node1 = this.getNewNode();
		// SimulatorComponentNode node2 = this.getNewNode();
		// create a pipe for (2ms timeout)
		//System.out.println("node1->node2,2 = " + node1.connect(node2, 2));
		// create another pipe (3ms timeout), this way all packets are sent over both ways
		//System.out.println("node1->node2,3 = " + node1.connect(node2, 3));
		// create a pipe from node2 to node1 (NOTE: THIS RESULTS NOT TO BE A BIDIPIPE!!)
		//System.out.println("node2->node1,4 = " + node2.connect(node1, 4));
		// create a bidi-pipe from 1 to 2
		//System.out.println("node1<->node2,5 = " + node1.connect_bidi(node2, 5));
	}

	public List<SimulatorComponentInt> getComponents() {
		return components;
	}
	
	/**
	 * register a new component to the structure
	 * @param component
	 */
	protected void addComponent(SimulatorComponentInt component) {
		this.components.add(component);
	}

	/**
	 * create a new link, add it and return it to the caller
	 */
	public SimulatorComponentLink getNewLink(long delay) {
		SimulatorComponentLink link = new RoundRobinLink(this, delay);
		this.addComponent(link);
		return link;
	}

	/**
	 * create new node, add it and return it to the caller
	 */
	public SimulatorComponentNode getNewNode() {
		SimulatorComponentNode node = new RoundRobinNode(this);
		this.addComponent(node);
		this.informAll(new NodeJoinEvent(node));
		return node;
	}

	/**
	 * create new packet, add it and return it to the caller
	 */
	public SimulatorComponentPacket getNewPacket(SimulatorComponentNode from, SimulatorComponentNode to, long time) {
		SimulatorComponentPacket packet = new RoundRobinPacket_default(this, from, to, time);
		// must not register this
		this.addComponent(packet);
		return packet;
	}

	public List<SimulatorComponentNode> getNodes() {
		LinkedList<SimulatorComponentNode> nodes = new LinkedList<SimulatorComponentNode>();
		for(SimulatorComponentInt component : this.getComponents()) {
			if(component instanceof SimulatorComponentNode) {
				nodes.add((SimulatorComponentNode) component);
			}
		}
		return nodes;
	}
	
	public long getSimulationTimelength() {
		return 1500;
	}
}
