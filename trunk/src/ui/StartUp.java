package ui;

import org.apache.log4j.BasicConfigurator;

import ui.events.IVidisEvent;
import ui.mvc.VidisController;
import ui.mvc.api.Dispatcher;
import util.Log;



public class StartUp {
	public static void main(String[] args){
//		BasicConfigurator.configure();
		
		Log.debug( "starting up" );
		Log.debug( "java.library.path = " + System.getProperty( "java.library.path" ) );
		
		Dispatcher.registerController( new VidisController() );	
		Dispatcher.forwardEvent( IVidisEvent.Init );
		
//		Simulator.createInstance();
//		Simulator.getInstance().importSimFile( new File("data/modules/demo/demo.msim"));
//		Simulator.getInstance().getPlayer().play();
		
	}
}
