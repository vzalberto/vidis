package vis;

import vis.mvc.AController;
import vis.mvc.IVidisEvents;
import vis.mvc.VidisEvent;

public class VidisController extends AController {

	public VidisController() {
		addChildController( new WindowController() );
		addChildController( new SceneController() );
	}
	
	public void handleEvent(VidisEvent event) {
		switch ( event.type ) {
		case IVidisEvents.Init:
			forwardEventToChilds( IVidisEvents.InitWindow );
			break;
		}

	}

}
