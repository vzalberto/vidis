package ui;


import org.apache.log4j.Logger;

import ui.events.IVidisEvent;
import ui.mvc.VidisController;
import ui.mvc.api.Dispatcher;



public class StartUp {
	private static Logger logger = Logger.getLogger( StartUp.class );
	
	public static void main(String[] args){
//		BasicConfigurator.configure();
		
		logger.info( "starting up" );
		logger.info( "java.library.path:" );
		String[] l = System.getProperty( "java.library.path" ).split(":");
		for (String s : l) logger.info( " * " + s );
		
		Dispatcher.registerController( new VidisController() );	
		Dispatcher.forwardEvent( IVidisEvent.Init );
		
//		Simulator.createInstance();
//		Simulator.getInstance().importSimFile( new File("data/modules/demo/demo.msim"));
//		Simulator.getInstance().getPlayer().play();
		
	}
}
