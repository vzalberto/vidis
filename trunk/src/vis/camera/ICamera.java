package vis.camera;

import javax.media.opengl.GL;

import vis.WrongMatrixStackException;
import vis.input.ActionHandler;

public interface ICamera extends ActionHandler{
	
	public void init(GL gl);
	public void applyProjectionMatrix(GL gl) throws WrongMatrixStackException;
	public void applyViewMatrix(GL gl) throws WrongMatrixStackException;
	
	public void reshape(int x, int y, int width, int height);
}
