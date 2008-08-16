package ui.vis.camera;

import javax.media.opengl.GL;

import vis.WrongMatrixStackException;

/**
 * Camera Interface 
 * @author Christoph
 *
 */
public interface ICamera {
	
	
	public void init(GL gl);
	public void applyProjectionMatrix(GL gl) throws WrongMatrixStackException;
	public void applyViewMatrix(GL gl) throws WrongMatrixStackException;
	
	/**
	 * 
	 */
	public void update();
	
	public void reshape(int x, int y, int width, int height);
	
}
