package vidis.sim.framework.observe;

/**
 * marker interface that all observable interfaces must extend
 * @author dominik
 *
 */
public interface SimulatorObservableInt {
	/**
	 * register a listener to be informed about events
	 * @param listener the listener to be registered
	 */
	public void register(SimulatorEventListenerInt listener);
	
	/**
	 * unregister a (registered) listener no more to be informed
	 * @param listener listener to be unregistered
	 */
	public void unregister(SimulatorEventListenerInt listener);
}
