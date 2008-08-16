package vis.model.structure;

import vis.model.IEvent;

public abstract class AEventHandler implements IEventHandler {

	public void fireEvent( IEvent e ) {
		handleEvent( e );
		// FIXME check event and do the calls
	}
	
	protected abstract void handleEvent( IEvent e );
	
//	FIXME
//	protected abstract void mouseDown( Object someparas );
//	protected abstract void mouseUp( Object someparas );
//	protected abstract void mouseClicked( Object someparas );
//	protected abstract void mouseMove( Object someparas );
//	
//	protected abstract void keyPressed( Object someparas );
	

}
