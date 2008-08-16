package vis.mvc;

import java.util.HashSet;
import java.util.Set;

public class Dispatcher {

	public static Set<IController> controller = new HashSet<IController>();
	
	@SuppressWarnings("unchecked")
	public static void forwardEvent( int type ) {
		forwardEvent( new VidisEvent( type ) );
	}
	
	@SuppressWarnings("unchecked")
	public static void forwardEvent( VidisEvent event ) {
		for ( IController c : controller ) {
			c.fireEvent( event );
		}
	}
	
	public static void registerController( IController c ) {
		controller.add( c );
	}
}
