package vidis.ui.events;

public class StartEvent implements IVidisEvent {

	private int id;
	public StartEvent( int id ){
		this.id = id;
	}
	
	public int getID() {
		return id;
	}

}
