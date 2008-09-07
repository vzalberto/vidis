package vidis.ui.events;


public abstract class AEventHandler implements IEventHandler {

	public void fireEvent( IVidisEvent e ) {
		handleEvent( e );
	}
	
	protected abstract void handleEvent( IVidisEvent e );
	
}
