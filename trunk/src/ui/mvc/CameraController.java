package ui.mvc;


import org.apache.log4j.Logger;

import ui.events.CameraEvent;
import ui.events.IVidisEvent;
import ui.mvc.api.AController;
import ui.mvc.api.Dispatcher;
import ui.vis.camera.DefaultCamera;


public class CameraController extends AController{

	private static Logger logger = Logger.getLogger( CameraController.class );
	
	private DefaultCamera defaultCamera;
	
	public CameraController() {
		logger.debug( "Constructor()" );
		
		registerEvent( IVidisEvent.InitCamera );
		
		registerEvent(	IVidisEvent.ScrollDown,
						IVidisEvent.ScrollUp,
						IVidisEvent.ScrollLeft,
						IVidisEvent.ScrollRight,
						IVidisEvent.SkewDown,
						IVidisEvent.SkewUp,
						IVidisEvent.RotateLeft,
						IVidisEvent.RotateRight);
		
		registerEvent(	IVidisEvent.MouseClickedEvent );
	}
	
	@Override
	public void handleEvent( IVidisEvent event ) {
		logger.debug( "handleEvent("+event+")" );
		switch ( event.getID() ) {
		case IVidisEvent.InitCamera:
			initialize();
			break;
		case IVidisEvent.RotateLeft:
		case IVidisEvent.RotateRight:
		case IVidisEvent.SkewDown:
		case IVidisEvent.SkewUp:
		case IVidisEvent.ScrollDown:
		case IVidisEvent.ScrollLeft:
		case IVidisEvent.ScrollUp:
		case IVidisEvent.ScrollRight:
			forwardEventToOtherHandler( defaultCamera, event );
			break;
		case IVidisEvent.MouseClickedEvent:
			defaultCamera.fireEvent( event );
			break;
		}
	}
	
	private void initialize() {
		defaultCamera = new DefaultCamera();
		Dispatcher.forwardEvent( new CameraEvent( IVidisEvent.CameraRegister, defaultCamera ) );
	}
	
}
