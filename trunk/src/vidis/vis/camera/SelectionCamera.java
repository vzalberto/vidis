package vidis.vis.camera;

import java.awt.Rectangle;

import javax.media.opengl.GL;

import vidis.vis.gui.input.Action;
import vidis.vis.gui.input.NotResponsibleException;
import vidis.vis.objects.interfaces.Renderable;
import vidis.vis.util.Vector3d;

public class SelectionCamera implements Camera {

	private Rectangle target;
	private Renderable selection;
	private Vector3d position;
	public void setTarget(Renderable t) {
		this.selection = t;
	}

	public void init(GL gl) {
		gl.glClear(GL.GL_DEPTH_BUFFER_BIT);
		gl.glEnable(GL.GL_DEPTH_TEST);
		gl.glViewport((int)target.getX(), (int)target.getY(), (int)target.getWidth(), (int)target.getHeight());
	}
	public void applyProjectionMatrix(GL gl) throws WrongMatrixStackException {
		gl.glOrtho(1, -1, -1, 1, 3, -3);
	}

	public void applyViewMatrix(GL gl) throws WrongMatrixStackException {
		if (this.selection != null)
			gl.glTranslated(-this.selection.getPos().x, -this.selection.getPos().y, 0);
	}

	public void reshape(int x, int y, int width, int height) {
		this.target = new Rectangle(x+10, y+10, height/5, height/5);
		
	}

	public Renderable getSelection() {
		return selection;
	}

	public void setSelection(Renderable selection) {
		this.selection = selection;
	}

	public void handleAction(Action a) throws NotResponsibleException {
		switch (a) {
		case SELECTION_CHANGED: if (a.getAnhang() instanceof Renderable) this.selection = (Renderable) a.getAnhang(); break;
		default : throw new NotResponsibleException();
		}
	}



}
