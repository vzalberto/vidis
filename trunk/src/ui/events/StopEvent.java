package ui.events;

public class StopEvent implements IVidisEvent {

	private int id;
	public StopEvent( int id ){
		this.id = id;
	}
	
	public int getID() {
		return id;
	}
}
