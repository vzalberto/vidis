package ui.model.impl;

import javax.media.opengl.GL;

import ui.events.IVidisEvent;
import ui.model.structure.ASimObject;

import com.sun.opengl.util.GLUT;

import data.var.IVariableContainer;


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
	protected void handleEvent(IVidisEvent e) {
		
	}


}
