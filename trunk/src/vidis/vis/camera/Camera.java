package vidis.vis.camera;

import javax.media.opengl.GL;

import vidis.vis.gui.input.ActionHandler;

public interface Camera extends ActionHandler{
	
	public void init(GL gl);
	public void applyProjectionMatrix(GL gl) throws WrongMatrixStackException;
	public void applyViewMatrix(GL gl) throws WrongMatrixStackException;
	
	public void reshape(int x, int y, int width, int height);
}
