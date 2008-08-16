package vis.model.impl;

import javax.media.opengl.GL;

import com.sun.opengl.util.GLUT;

import data.var.IVariableContainer;

import vis.model.IEvent;
import vis.model.structure.ASimObject;

public class Link extends ASimObject {

	public Link(IVariableContainer c) {
		super(c);
		// TODO Auto-generated constructor stub
	}

	// need to override render to get rid of the automatic positioning
	@Override
	public void render(GL gl) {
		renderObject(gl);
	}
	
	@Override
	protected void renderObject(GL gl) {
		GLUT glut = new GLUT();
		// FIXME

	}

	@Override
	protected void handleEvent(IEvent e) {
		// TODO Auto-generated method stub

	}

}
