package vidis.ui.vis.camera;

import javax.media.opengl.GL;

/**
 * Camera Interface 
 * @author Christoph
 *
 */
public interface ICamera {
	
	
	public void init(GL gl);
	public void applyProjectionMatrix(GL gl);
	public void applyViewMatrix(GL gl);
	
	/**
	 * 
	 */
	public void update();
	
	public void reshape(int x, int y, int width, int height);
	
}
