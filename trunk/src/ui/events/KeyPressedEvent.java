package ui.events;

import java.awt.event.KeyEvent;

public class KeyPressedEvent extends AKeyEvent {

	public KeyPressedEvent(KeyEvent k) {
		super(k);
	}

	public int getID() {
		return IVidisEvent.KeyPressedEvent;
	}


}
