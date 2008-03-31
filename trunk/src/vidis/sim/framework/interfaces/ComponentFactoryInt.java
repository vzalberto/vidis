package vidis.sim.framework.interfaces;

import java.util.List;

import vidis.sim.framework.components.SimulatorComponentLink;
import vidis.sim.framework.components.SimulatorComponentNode;
import vidis.sim.framework.components.SimulatorComponentPacket;
import vidis.sim.framework.observe.SimulatorEventListenerInt;
import vidis.sim.framework.observe.SimulatorEventProducerInt;
import vidis.sim.framework.observe.SimulatorObservableInt;

/**
 * factory that knows about the currently used classes (for example
 * in modules) and that provides the correct implementations of those
 * @author dominik
 *
 */
public interface ComponentFactoryInt extends SimulatorObservableInt, SimulatorEventListenerInt, SimulatorEventProducerInt {
	/**
	 * create a concrete wire
	 * @param delay delay of the wire in milliseconds
	 * @return the created wire
	 */
	public SimulatorComponentLink getNewLink(long delay);

	/**
	 * create a node
	 * @return the created node
	 */
	public SimulatorComponentNode getNewNode();
	
	/**
	 * create a packet
	 * @param from from who
	 * @param to to who
	 * @return the created packet
	 */
	public SimulatorComponentPacket getNewPacket(SimulatorComponentNode from, SimulatorComponentNode to, long time);
	
	/**
	 * called upon start of simulation;
	 * 
	 * should contain stuff like initialization (create nodes etc.)
	 */
	public void init();

	/**
	 * retrieve all simulator components
	 * @return a list of components
	 */
	public List<SimulatorComponentInt> getComponents();
	
	/**
	 * retrieve all nodes available
	 * @return list of all nodes
	 */
	public List<SimulatorComponentNode> getNodes();
	
	/**
	 * get how long the module should be executed
	 * @return time in milliseconds
	 */
	public long getSimulationTimelength();
}
