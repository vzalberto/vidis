package ui.events;

import java.awt.event.KeyEvent;

public class KeyReleasedEvent extends AKeyEvent {
	
	public KeyReleasedEvent(KeyEvent k) {
		super(k);
	}

	public int getID() {
		return IVidisEvent.KeyReleasedEvent;
	}
	

}
