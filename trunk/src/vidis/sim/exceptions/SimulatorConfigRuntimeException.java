package vidis.sim.exceptions;

public class SimulatorConfigRuntimeException extends RuntimeException {
	/**
	 * default serial version UID
	 */
	private static final long serialVersionUID = 1L;

	public SimulatorConfigRuntimeException() {
		super("You tried to manipulate the simulator configuration during runtime. Bad boy! Do it before the simulator is initialized!");
	}
}
