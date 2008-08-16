package vis.mvc;

import java.util.HashSet;
import java.util.Set;

public abstract class AController implements IController {

	private Set<IController> childs = new HashSet<IController>();
	protected void addChildController( IController c ) {
		childs.add(c);
	}
	
	public void fireEvent(VidisEvent event) {
		handleEvent( event );
	}
	
	protected void forwardEventToChilds(VidisEvent event) {
		for ( IController c : childs ) {
			c.fireEvent(event);
		}
	}
	protected void forwardEventToChilds(int eventId) {
		VidisEvent event = new VidisEvent(eventId);
		for ( IController c : childs ) {
			c.fireEvent(event);
		}
	}
	
	public abstract void handleEvent(VidisEvent event);

}
