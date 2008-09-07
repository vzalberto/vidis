package vidis.ui.events;

import vidis.ui.model.structure.IVisObject;


public class ObjectEvent implements IVidisEvent {

	private int eventId;
	private IVisObject object;
	
	public ObjectEvent( int eventId, IVisObject object ) {
		this.eventId = eventId;
		this.object = object;
	}
	public int getID() {
		return eventId;
	}
	
	public IVisObject getObject() {
		return this.object;
	}

}
