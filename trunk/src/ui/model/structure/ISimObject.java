package ui.model.structure;

import javax.media.opengl.GL;

import ui.events.IEventHandler;

public interface ISimObject extends IVisObject, IEventHandler{

	public void render( GL gl );
	
}
