package vidis.ui.events;

import java.awt.event.KeyEvent;

public abstract class AKeyEvent implements IVidisEvent {
	
	public int key;
	public KeyEvent keyEvent;
	
	public AKeyEvent( KeyEvent k ) {
		this.key = k.getKeyCode();
	}
}
