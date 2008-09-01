package ui.mvc;

import org.apache.log4j.Logger;

import ui.events.CameraEvent;
import ui.events.IVidisEvent;
import ui.events.ObjectEvent;
import ui.events.VidisEvent;
import ui.gui.Gui;
import ui.mvc.api.AController;
import ui.mvc.api.Dispatcher;
import ui.vis.camera.GuiCamera;

public class GuiController extends AController {

	private static Logger logger = Logger.getLogger( GuiController.class );
	
	private GuiCamera guiCamera;
	private Gui gui;
	
	public GuiController() {
		logger.debug("Constructor()");
		registerEvent( IVidisEvent.InitGui );
		
		registerEvent( IVidisEvent.MouseClickedEvent,
				   IVidisEvent.MousePressedEvent,
				   IVidisEvent.MouseReleasedEvent );
		
		registerEvent( IVidisEvent.FPS );
		
		
	}
	
	@Override
	public void handleEvent(IVidisEvent event) {
		logger.debug( "handleEvent( "+event+" )");
		switch ( event.getID() ) {
		case IVidisEvent.InitGui:
			initialize();
			break;
		case IVidisEvent.MouseClickedEvent:
			guiCamera.fireEvent( event );
			break;
		case IVidisEvent.FPS:
			String fps = ((VidisEvent)event).getData().toString();
			gui.fps.setText( String.format( "%4.1ffps", Double.parseDouble(fps) ) );
			break;
		}
	}
	
	private void initialize() {
		logger.debug( "initialize()" );
		gui = new Gui();
		guiCamera = new GuiCamera(gui);
		Dispatcher.forwardEvent( new CameraEvent( IVidisEvent.CameraRegister, guiCamera ));
		Dispatcher.forwardEvent( new ObjectEvent( IVidisEvent.ObjectRegister, gui.getMainContainer() ));
	}

}
