package ui.mvc;

import org.apache.log4j.Logger;

import ui.events.IVidisEvent;
import ui.mvc.api.AController;

public class SimulatorController extends AController {
	private static Logger logger = Logger.getLogger( SimulatorController.class );

	public SimulatorController() {
		logger.debug( "Constructor()" );
	}
	
	@Override
	public void handleEvent(IVidisEvent event) {
		logger.debug( "handleEvent( "+event+" )" );

	}

}
