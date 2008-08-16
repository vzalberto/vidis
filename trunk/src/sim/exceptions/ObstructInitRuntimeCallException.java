package sim.exceptions;

public class ObstructInitRuntimeCallException extends RuntimeException {
	/**
	 * generated serial version UID
	 */
	private static final long serialVersionUID = 6840144828942122527L;

	public ObstructInitRuntimeCallException(Throwable cause) {
		super("Obstruct init(); user may called init() before, thus means: we have a DAU sitting in front of this screen", cause);
	}
}
