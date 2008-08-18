package ui.model.impl;

import javax.media.opengl.GL;

import ui.events.IVidisEvent;
import ui.model.structure.ASimObject;
import data.var.IVariableContainer;

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
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void handleEvent(IVidisEvent e) {
		// TODO Auto-generated method stub
		
	}

}
