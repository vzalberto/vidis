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

	private static int displayListId = -1;
	
	@Override
	public void renderObject(GL gl) {
		if ( displayListId == -1 ) {
			displayListId = gl.glGenLists(1);
			preRenderObject(gl);
		}
		gl.glColor3d( 1, 0, 1 );
		gl.glCallList( displayListId );
	}
	
	public void preRenderObject(GL gl) {
		GLUT glut = new GLUT();
		gl.glNewList(displayListId, GL.GL_COMPILE);
			glut.glutSolidCube(0.2f);
		gl.glEndList();
	}

	@Override
	protected void handleEvent(IVidisEvent e) {
		
	}


}
