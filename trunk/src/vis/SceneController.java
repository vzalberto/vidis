package vis;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

import vis.mvc.AController;
import vis.mvc.IVidisEvents;
import vis.mvc.VidisEvent;

public class SceneController extends AController implements GLEventListener {

	@Override
	public void handleEvent(VidisEvent event) {
		switch ( event.type ) {
		case IVidisEvents.InitScene:
			initialize();
			break;
		}	
	}

	private void initialize() {
		
	}
	
	public void display(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub

	}

	public void displayChanged(GLAutoDrawable drawable, boolean modeChanged,
			boolean deviceChanged) {
		// TODO Auto-generated method stub

	}

	public void init(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub

	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) {
		// TODO Auto-generated method stub

	}

}
