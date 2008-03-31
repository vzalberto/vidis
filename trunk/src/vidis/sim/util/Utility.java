package vidis.sim.util;

import java.io.InputStream;
import java.io.PrintStream;

import vidis.util.Const;

/**
 * useful class that holds constant data (configuration)
 * @author dominik
 *
 */
public class Utility {
	/**
	 * tell the system what the standard error printstream should be
	 */
	public static final PrintStream STD_ERR = Const.STD_ERR;
	/**
	 * tell the system what the standard output printstream should be
	 */
	public static final PrintStream STD_OUT = Const.STD_OUT;
	/**
	 * tell the system what the standard input stream should be
	 */
	public static final InputStream STD_IN = Const.STD_IN;
	/**
	 * this is the base timestamp in millis
	 */
	private static long NOW = System.currentTimeMillis();
	//private static long NOW = 0;
	/**
	 * retrieve the current time
	 * @return current time (ms)
	 */
	public static long getTime() {
		return NOW;
	}
	/**
	 * go forward in the time line of the simulation by one step (1ms)
	 */
	public static void timeForward() {
		NOW++;
	}
}
