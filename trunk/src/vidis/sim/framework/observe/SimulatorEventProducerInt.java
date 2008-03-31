package vidis.sim.framework.observe;

/**
 * interface that all event generators must implement
 * 
 * @author dominik
 *
 */
public interface SimulatorEventProducerInt {
	/**
	 * inform all registrants about a event
	 * @param event the event that all should be informed about
	 */
	public void informAll(SimulatorEventInt event);
}
