package ui.model.impl;

import javax.media.opengl.GL;
import javax.vecmath.Tuple3d;

import org.apache.log4j.Logger;

import ui.events.IVidisEvent;
import ui.model.structure.ASimObject;
import data.sim.SimLink;
import data.var.IVariableContainer;

public class Link extends ASimObject {
	
	private static Logger logger = Logger.getLogger( Link.class );
	

	public Link(IVariableContainer c) {
		super(c);
		logger.error( "new link with the following vars: \n"+ getVariableIds());
	}

	// need to override render to get rid of the automatic positioning
	@Override
	public void render(GL gl) {
		renderObject(gl);
	}

	@Override
	protected void renderObject(GL gl) {
		logger.error((Tuple3d) getVariableById( SimLink.POINT_A ).getData());
		logger.error((Tuple3d) getVariableById( SimLink.POINT_B ).getData());
		Tuple3d posA = (Tuple3d) getVariableById( SimLink.POINT_A ).getData();
		Tuple3d posB = (Tuple3d) getVariableById( SimLink.POINT_B ).getData();
		
		gl.glPushMatrix();
		gl.glColor3d( 1, 1, 0);
		gl.glBegin( GL.GL_LINES );
			gl.glVertex3d(posA.x, posA.z, posA.y);
			gl.glVertex3d(posB.x, posB.z, posB.y);
		gl.glEnd();
		gl.glPopMatrix();
	}
	

	@Override
	protected void handleEvent(IVidisEvent e) {
		// TODO Auto-generated method stub
		
	}

}
