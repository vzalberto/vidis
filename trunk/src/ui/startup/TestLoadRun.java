package ui.startup;

import java.io.File;

import javax.media.opengl.GLCanvas;

public class TestLoadRun {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.err.println(System.getProperty("java.library.path"));
		try {
			
			File jogl = new File("./lib/jogl-1.1.1/native/windows/x86/jogl.dll");
			Runtime.getRuntime().load(jogl.getAbsolutePath());
			File jogl_awt = new File("./lib/jogl-1.1.1/native/windows/x86/jogl_awt.dll");
			Runtime.getRuntime().load(jogl_awt.getAbsolutePath());
			File jogl_cg = new File("./lib/jogl-1.1.1/native/windows/x86/jogl_cg.dll");
			Runtime.getRuntime().load(jogl_cg.getAbsolutePath());
			File gluegen_rt = new File("./lib/jogl-1.1.1/native/windows/x86/gluegen-rt.dll");
			Runtime.getRuntime().load(gluegen_rt.getAbsolutePath());

			System.err.println("done loading libraries");
			GLCanvas nix = new GLCanvas();
			
		}
		catch ( SecurityException e ) {
			e.printStackTrace();
		}
		catch ( UnsatisfiedLinkError e) {
			e.printStackTrace();
		}
		catch ( NullPointerException e ) {
			e.printStackTrace();
		}
		
		
	}

}
