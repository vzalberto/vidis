package vidis.vis.util;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 
 * 
 * @author Christoph
 *
 */
public class GraphicsInfo {
	/**
	 * Constructor 
	 * 
	 */
	public GraphicsInfo(){
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		
		for (Method m : gd.getClass().getMethods()){
		/*	if (m.getName().startsWith("get") && 
					( m.getReturnType().equals(String.class) ||
					  m.getReturnType().isPrimitive()
					))*/
				try {
					System.out.print(m.getName()+ " -> ");
					System.out.print(m.invoke(gd, (Object[])null) );
					
				} catch (IllegalArgumentException e) {
					System.out.print(e.getMessage());
				} catch (IllegalAccessException e) {
					System.out.print(e.getMessage());
				} catch (InvocationTargetException e) {
					System.out.print(e.getMessage());
				} finally {
					System.out.println();
				}
		}
		
		for (DisplayMode mode : GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayModes()) {
			//System.out.println("Found Mode: " +mode.getWidth()+"x"+mode.getHeight()+"@"+mode.getRefreshRate()+ "  "+ mode.getBitDepth()	);
		}

	}
}
