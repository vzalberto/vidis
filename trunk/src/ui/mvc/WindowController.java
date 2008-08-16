package ui.mvc;

import javax.media.opengl.GLCanvas;

import org.apache.log4j.Logger;

import ui.events.IVidisEvent;
import ui.events.VidisEvent;
import ui.mvc.api.AController;
import ui.vis.FrameContainer;

public class WindowController extends AController {
	
	private static Logger logger = Logger.getLogger(WindowController.class);

	private FrameContainer frame;
	
	public WindowController() {
		logger.debug( "Constructor()" );
		registerEvent( IVidisEvent.InitWindow );
	}
	
	@SuppressWarnings( "unchecked" )
	@Override
	public void handleEvent(IVidisEvent event) {
		logger.debug( "handleEvent( "+event+" )" );
		switch (event.getID()) {
		case IVidisEvent.InitWindow:
			frame = new FrameContainer( "VIDIS", ((VidisEvent<GLCanvas>) event).getData() );
			frame.setVisible( true );
			break;
		}

	}
	

}
