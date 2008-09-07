package vidis.ui.events;

public class DummyEvent implements IVidisEvent {

	private int id;
	public DummyEvent( int id ){
		this.id = id;
	}
	
	public int getID() {
		return id;
	}

	@Override
	public String toString() {
		return "DummyEvent( "+id+" )";
	}
}
