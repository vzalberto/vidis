package ui.events;

import ui.vis.camera.ICamera;

public class CameraEvent implements IVidisEvent {

	private int eventId;
	private ICamera camera;
	
	public CameraEvent( int eventId, ICamera camera ){
		this.eventId = eventId;
		this.camera = camera;
	}
	public int getID() {
		return eventId;
	}
	
	public ICamera getCamera() {
		return camera;
	}

}
