package vidis.sim.framework.components;

import java.util.LinkedList;
import java.util.List;

import vidis.sim.framework.interfaces.ComponentFactoryInt;
import vidis.sim.framework.interfaces.SimulatorComponentInt;
import vidis.sim.framework.observe.SimulatorEventInt;
import vidis.sim.framework.observe.SimulatorEventListenerInt;

/**
 * basic factory class;
 * this class knows about the concrete components and constructors
 * 
 * NOTE FOR MODULE WRITERS:
 * USE THIS CLASS TO IMPLEMENT A NEW MODULE FACTORY!
 * @author dominik
 *
 */
public abstract class SimulatorComponentFactory implements ComponentFactoryInt {
	
	private LinkedList<SimulatorEventListenerInt> eventListeners = new LinkedList<SimulatorEventListenerInt>();

	public abstract List<SimulatorComponentInt> getComponents();

	public abstract SimulatorComponentLink getNewLink(long delay);

	public abstract SimulatorComponentNode getNewNode();

	public abstract SimulatorComponentPacket getNewPacket(SimulatorComponentNode from, SimulatorComponentNode to, long time);
	
	public void register(SimulatorEventListenerInt listener) {
		if(!this.eventListeners.contains(listener))
			this.eventListeners.add(listener);
	}
	
	public void unregister(SimulatorEventListenerInt listener) {
		this.eventListeners.remove(listener); 
	}
	
	public void beInformed(SimulatorEventInt event) {
		this.informAll(event);
	}
	
	public void informAll(SimulatorEventInt event) {
		for(SimulatorEventListenerInt listener : this.eventListeners) {
			listener.beInformed(event);
		}
	}
	
	public long getSimulationTimelength() {
		return 50;
	}
}
