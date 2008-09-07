package vidis.ui.mvc.api;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import vidis.ui.events.AEventHandler;
import vidis.ui.events.IEventHandler;
import vidis.ui.events.IVidisEvent;

public abstract class AController extends AEventHandler implements IController {

	private static Logger logger = Logger.getLogger( AController.class );
	
	private Set<IController> childs = new HashSet<IController>();
	
	protected void registerEvent( int eventId ) {
		logger.debug( "registerEvent( "+eventId+" )");
		Dispatcher.registerEvent( this, eventId );
	}
	
	protected void registerEvent ( int... eventIds ) {
		logger.debug( "registerEvent( "+eventIds+" )");
		for ( int eventId : eventIds ) {
			registerEvent( eventId );
		}
	}
	
	protected void addChildController( IController c ) {
		logger.debug( "addChildController()" );
		childs.add(c);
	}
	
	protected void forwardEventToChilds( IVidisEvent event) {
		logger.debug( "forwardEventToChilds()" );
		for ( IController c : childs ) {
			c.fireEvent(event);
		}
	}
	
	protected void forwardEventToOtherHandler( IEventHandler h, IVidisEvent e ) {
		logger.debug( "forwardEventToOtherHandler()" );
		h.fireEvent( e );
	}
	
	public abstract void handleEvent( IVidisEvent event );

}
