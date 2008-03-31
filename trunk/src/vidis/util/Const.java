package vidis.util;

import java.io.InputStream;
import java.io.PrintStream;

/**
 * useful class that holds constant data (configuration)
 * @author dominik
 *
 */
public class Const {
	/**
	 * tell the system what the standard error printstream should be
	 */
	public static final PrintStream STD_ERR = System.err;
	/**
	 * tell the system what the standard output printstream should be
	 */
	public static final PrintStream STD_OUT = System.out;
	/**
	 * tell the system what the standard input stream should be
	 */
	public static final InputStream STD_IN = System.in;
}
