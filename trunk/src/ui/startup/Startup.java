package ui.startup;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import ui.config.Configuration;

public class Startup {
	private static Map<String, Map<String, List<File>>> libraries = new HashMap<String, Map<String,List<File>>>();
	private static String os;
	private static String arch;
	static {
		System.err.println("loading..");
		getOs();
		getArch();
		getJoglNativeLibs();
	}
	private static void getJoglNativeLibs() {
		// retrieve all JOGL libraries
		File joglPath = new File(Configuration.JOGL_PATH + File.separator + "native");
		if(joglPath.exists() && joglPath.isDirectory()) {
			for(File joglOS : joglPath.listFiles()) {
				libraries.put(joglOS.getName(), new HashMap<String, List<File>>());
				if(joglOS.exists() && joglOS.isDirectory() && !joglOS.getName().startsWith(".")) {
					for(File joglARCH : joglOS.listFiles()) {
						if(joglARCH.exists() && joglARCH.isDirectory() && !joglARCH.getName().startsWith(".")) {
							libraries.get(joglOS.getName()).put(joglARCH.getName(), new LinkedList<File>());
							for(File joglLib : joglARCH.listFiles()) {
								if(joglLib.exists() && joglLib.isFile() && !joglLib.getName().startsWith(".")) {
									libraries.get(joglOS.getName()).get(joglARCH.getName()).add(joglLib);
								}
							}
						}
					}
				}
			}
		}
	}
	private static void getArch() {
		String osArch = System.getProperty("os.arch").toLowerCase();
		 if(osArch.indexOf("64") > -1) {
			 arch = "x86-64";
		 } else if ((osArch.indexOf("x86") > -1)
				|| (osArch.indexOf("i386") > -1)
				|| (osArch.indexOf("i686") > -1)) {
			arch = "x86";
		} else if(osArch.indexOf("ppc") > -1
				|| osArch.indexOf("powerpc") > -1) {
			arch = "ppc";
		} else if(osArch.indexOf("sparc") > -1) {
			arch = "sparc";
		} else if(osArch.indexOf("sparcv9") > -1
				|| osArch.indexOf("sparcv9") > -1) {
			// let's assume linux, maybe it works - probably it crashes
			os = "sparcv9";
		}
	}
	private static void getOs() {
		// define os name
		String osName = System.getProperty("os.name").toLowerCase();
		if ((osName.indexOf("nt") > -1)
				|| (osName.indexOf("windows") > -1) ) {
			os = "windows";
		} else if(osName.indexOf("mac") > -1) {
			os = "macosx";
		} else if(osName.indexOf("solaris") > -1) {
			os = "solaris";
		} else if(osName.indexOf("linux") > -1
				|| osName.indexOf("unix") > -1) {
			// let's assume linux, maybe it works - probably it crashes
			os = "linux";
		}
	}
	public static void main(String[] argv) {
		System.out.println("Running: " + os + "-" + arch);
		
		// load libraries
		try {
			for(File library : libraries.get(os).get(arch)) {
				System.err.println("loadLibrary("+library+");");
				try {
					System.load(library.getAbsolutePath());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// start app
			System.err.println("starting app");
		} catch (NullPointerException e) {
			System.err.println("unsupported os / arch");
		}
	}
}