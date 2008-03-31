package vidis.sim.framework.observe;

/**
 * marker interface for event listeners on the simulator
 * all interfaces must extend this one
 * @author dominik
 *
 */
public interface SimulatorEventListenerInt {
	/**
	 * inform about a new event
	 * @param event event that occurred
	 */
	public void beInformed(SimulatorEventInt event);
}
