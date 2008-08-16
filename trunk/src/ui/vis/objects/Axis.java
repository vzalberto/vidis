package ui.vis.objects;


import javax.media.opengl.GL;
import javax.vecmath.Vector3d;

import ui.model.structure.IVisObject;

public class Axis implements IVisObject  {

	public void render(GL gl) {
		
		// x axis red
		gl.glColor3f(1f, 0f, 0f);
		gl.glBegin(GL.GL_LINES);
			gl.glVertex3f(0f, 0f, 0f);
			gl.glVertex3f(1f, 0f, 0f);
			gl.glVertex3f(1f, 0f, 0f);
			gl.glVertex3f(0.9f, 0.1f, 0.0f);
			gl.glVertex3f(1f, 0f, 0f);
			gl.glVertex3f(0.9f, -0.1f, 0.0f);
		gl.glEnd();
		gl.glColor3f(0f, 1f, 0f);
		gl.glBegin(GL.GL_LINES);
			gl.glVertex3f(0f, 0f, 0f);
			gl.glVertex3f(0f, 1f, 0f);
			gl.glVertex3f(0f, 1f, 0f);
			gl.glVertex3f(0.1f, 0.9f, 0.0f);
			gl.glVertex3f(0f, 1f, 0f);
			gl.glVertex3f(-0.1f, 0.9f, 0.0f);
		gl.glEnd();
		gl.glColor3f(0f, 0f, 1f);
		gl.glBegin(GL.GL_LINES);
			gl.glVertex3f(0f, 0f, 0f);
			gl.glVertex3f(0f, 0f, 1f);
			gl.glVertex3f(0f, 0f, 1f);
			gl.glVertex3f(0f, 0.1f, 0.9f);
			gl.glVertex3f(0f, 0f, 1f);
			gl.glVertex3f(0f, -0.1f, 0.9f);
		gl.glEnd();

	}

	public Vector3d getPos() {
		return new Vector3d(0, 0, 0);
	}
}
