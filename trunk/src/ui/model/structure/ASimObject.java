package ui.model.structure;

import javax.media.opengl.GL;
import javax.vecmath.Tuple3d;

import ui.events.AEventHandler;

import data.var.AVariable;
import data.var.IVariableContainer;

public abstract class ASimObject extends AEventHandler implements ISimObject {

	private IVariableContainer obj; // FIXME
	private IGuiContainer guiObj; // FIXME
	
	public ASimObject( IVariableContainer c ) {
		obj = c;
	}
	
	protected AVariable getVariableById( String id ) {
		return obj.getVariableById( id );
	}
	
	public void render( GL gl ) {
		gl.glPushMatrix();
		try {
			Tuple3d pos = (Tuple3d) obj.getVariableById( AVariable.COMMON_IDENTIFIERS.POSITION ).getData();
			gl.glTranslated(pos.x, pos.z, pos.y);
		}
		catch ( Exception e ) {
			System.out.println("error getting pos variable of "+obj +"  ");
			e.printStackTrace();
			
		}
		renderObject( gl );
		gl.glPopMatrix();
	}
	
	protected abstract void renderObject( GL gl );

}
