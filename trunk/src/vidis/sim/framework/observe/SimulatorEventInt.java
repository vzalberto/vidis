package vidis.sim.framework.observe;

import java.util.List;

import vidis.sim.framework.interfaces.SimulatorComponentInt;

/**
 * marker interface to mark all events
 * @author dominik
 *
 */
public interface SimulatorEventInt {
	/**
	 * retrieve the time when the event occurred
	 * @return the timestamp (long) in milliseconds
	 */
	public long getTime();
	
	/**
	 * retrieve all involved components
	 * @return list of simulator components
	 */
	public List<SimulatorComponentInt> getInvolvedComponents();
	
	/**
	 * generates and returns a xml structured document
	 * @return string of xml
	 */
	public String toXML();
}
