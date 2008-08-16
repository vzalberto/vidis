package vis.model.impl;

import javax.media.opengl.GL;

import com.sun.opengl.util.GLUT;

import data.var.IVariableContainer;

import vis.model.IEvent;
import vis.model.structure.ASimObject;

public class Packet extends ASimObject {

	public Packet(IVariableContainer c) {
		super(c);
	}

	@Override
	protected void renderObject(GL gl) {
		GLUT glut = new GLUT();
		glut.glutSolidCube(0.2f);
	}

	@Override
	protected void handleEvent(IEvent e) {
		// TODO Auto-generated method stub

	}

}
