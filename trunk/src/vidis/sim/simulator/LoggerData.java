package vidis.sim.simulator;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import vidis.sim.framework.interfaces.SimulatorComponentInt;
import vidis.sim.framework.observe.SimulatorEventInt;

public class LoggerData {
	
	/**
	 * store all events that occurr here
	 */
	private HashMap<Long, LinkedList<SimulatorEventInt>> events = new HashMap<Long, LinkedList<SimulatorEventInt>>();
	
	/**
	 * keep track of the simulator component instances
	 */
	private HashMap<Integer, SimulatorComponentInt> componentInstances = new HashMap<Integer, SimulatorComponentInt>();

	public Long getId() {
		return System.currentTimeMillis();
	}

	public Integer getObjectCount() {
		return componentInstances.size();
	}

	public void clear() {
		events.clear();
		componentInstances.clear();
	}

	public void registerComponentInstance(SimulatorComponentInt instance) {
		if(componentInstances.containsKey(instance.getCID())) {
			// already registered
		} else {
			// add it
			componentInstances.put(instance.getCID(), instance);
		}
	}
	
	private void registerComponentInstances(List<SimulatorComponentInt> instances) {
		for(int i=0; i<instances.size(); i++) {
			registerComponentInstance(instances.get(i));
		}
	}

	public void registerEvent(SimulatorEventInt event) {
		if(!events.containsKey(event.getTime())) {
			// if not exists, create new node
			events.put(event.getTime(), new LinkedList<SimulatorEventInt>());
		}
		// get all involved components and add new ones
		this.registerComponentInstances(event.getInvolvedComponents());
		// add event
		events.get(event.getTime()).add(event);
	}

	public Long getTimeStart() {
		return Collections.min(events.keySet());
	}

	public Long getTimeEnd() {
		return Collections.max(events.keySet());
	}

	public Long getTimeLength() {
		return getTimeEnd()-getTimeStart();
	}

	public HashMap<Integer, SimulatorComponentInt> getInstances() {
		return componentInstances;
	}

	public HashMap<Long, LinkedList<SimulatorEventInt>> getEvents() {
		return events;
	}
	
}
