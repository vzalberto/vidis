package vidis.ui.config;

import java.io.File;

/**
 * a basic configuration class that holds all information about configurations
 * @author Dominik
 *
 */
public class Configuration {
	/**
	 * library path for vidis
	 */
	public static final String LIBRARY_PATH = "lib";
	
	/**
	 * jogl version
	 */
	public static final String JOGL_VERSION = "jogl-1.1.1";
	
	/**
	 * data path for vidis
	 */
	public static final String DATA_PATH = "data";
	
	/**
	 * log path for vidis
	 */
	public static final String LOG_PATH = DATA_PATH + File.separatorChar + "logs";
	
	/**
	 * modules path for vidis
	 */
	public static final String MODULE_PATH = DATA_PATH + File.separatorChar + "modules";
	
	/**
	 * resources path for vidis
	 */
	public static final String RESOURCE_PATH = DATA_PATH + File.separatorChar + "resources";
	
	/**
	 * shaders path for vidis
	 */
	public static final String SHADER_PATH = DATA_PATH + File.separatorChar + "shaders";

	/**
	 * jogl path for vidis
	 */
	public static final String JOGL_PATH = LIBRARY_PATH + File.separatorChar + JOGL_VERSION;
	
	/**
	 * just display wireframe
	 */
	public static boolean DISPLAY_WIREFRAME = true;
}
