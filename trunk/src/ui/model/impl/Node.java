package ui.model.impl;

import javax.media.opengl.GL;

import com.sun.opengl.util.GLUT;

import data.var.IVariableContainer;

import vis.model.IEvent;
import vis.model.structure.ASimObject;

public class Node extends ASimObject {

	
	public Node(IVariableContainer c) {
		super(c);
	}

	@Override
	protected void renderObject(GL gl) {
		GLUT glut = new GLUT();
		glut.glutSolidSphere(0.5, 20, 20);
	}

	@Override
	protected void handleEvent(IEvent e) {
		

	}

}
