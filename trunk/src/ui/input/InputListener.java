package ui.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import org.apache.log4j.Logger;

import ui.events.KeyPressedEvent;
import ui.events.KeyReleasedEvent;
import ui.events.MouseClickedEvent;
import ui.events.MousePressedEvent;
import ui.events.MouseReleasedEvent;
import ui.mvc.api.Dispatcher;

public class InputListener implements KeyListener, MouseWheelListener, MouseListener, MouseMotionListener {
	
	/**
	 * listens to all sorts of AWT input events and forwards them into Vidis own Event System
	 * @author Christoph
	 */
	private static Logger logger = Logger.getLogger( InputListener.class );	
	
	public InputListener(){
	}
	
	public void keyPressed(KeyEvent e) {
		logger.debug( "keyPressed("+e+")");
		Dispatcher.forwardEvent( new KeyPressedEvent( e ) );
	}

	public void keyReleased(KeyEvent e) {
		logger.debug( "keyReleased("+e+")");
		Dispatcher.forwardEvent( new KeyReleasedEvent( e ) );
	}

	public void keyTyped(KeyEvent e) {
//		logger.debug( "keyTyped("+e+")");
//		Dispatcher.forwardEvent( new KeyTypedEvent( e ) );
	}

	public void mouseWheelMoved(MouseWheelEvent e) {
		
	}

	public void mouseClicked(MouseEvent e) {
		logger.debug( "mouseClicked("+e+")");
		Dispatcher.forwardEvent( new MouseClickedEvent( e ) );
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		logger.debug( "mousePressed("+e+")");
		Dispatcher.forwardEvent( new MousePressedEvent( e ) );
	}

	public void mouseReleased(MouseEvent e) {
		logger.debug( "mouseReleased("+e+")");
		Dispatcher.forwardEvent( new MouseReleasedEvent( e ) );
	}
	
	public void mouseDragged(MouseEvent e) {
	}

	public void mouseMoved(MouseEvent e) {
	}

	
}
