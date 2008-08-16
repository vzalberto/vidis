package vis;

import ui.vis.FrameContainer;
import vis.mvc.AController;
import vis.mvc.IVidisEvents;
import vis.mvc.VidisEvent;

public class WindowController extends AController {

	public void handleEvent(VidisEvent event) {
		switch ( event.type ) {
		case IVidisEvents.InitWindow:
			createWindow();
			break;
		}	
	}
	
	private void createWindow() {
		Scene scene = Scene.getInstance();
		new FrameContainer("Testlauf", scene.getGLCanvas());
	}

}
