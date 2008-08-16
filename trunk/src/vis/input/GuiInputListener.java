package vis.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import vis.Scene;
import vis.camera.DefaultCamera;
import vis.camera.GuiCamera;
import vis.model.structure.IVisObject;

public class GuiInputListener implements KeyListener, MouseWheelListener, MouseListener, MouseMotionListener {
	
	private static Logger logger = Logger.getLogger( GuiInputListener.class );	
	
	private final Scene scene;
	private boolean alive = true;
	private List<Action> activeActions;
	private List<ActionHandler> actionHandlers;
	private KeyMapping mapping;

	public GuiInputListener(Scene scene){
		this.actionHandlers = new ArrayList<ActionHandler>();
		this.activeActions = new LinkedList<Action>();
		this.mapping = new KeyMapping();
		this.scene = scene;
		
		new Thread(new InputWorker(scene)).start();
	}
	
	public void addActionHandler(ActionHandler h) {
		this.actionHandlers.add(h);
	}
	public void removeActionHandler(ActionHandler h) {
		this.actionHandlers.remove(h);
	}
	private class InputWorker implements Runnable {
		private Scene scene;
		public boolean alive = true;
		public InputWorker(Scene scene){
			this.scene = scene;
		}
		public void run() {
			while (alive){
			try {
					Thread.sleep(20); // alle 20ms wird auf die Eingaben reagiert
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// remove timed out Actions
				List<Action> todel = new ArrayList<Action>();
				for (Action a : activeActions){
					if (a.getDieTime() != -1 && System.currentTimeMillis() > a.getDieTime())
						todel.add(a);
				}
				activeActions.removeAll(todel);
				
				// handle Actions
				for (ActionHandler h : actionHandlers)
					for (Action a : activeActions)
						try {
							h.handleAction(a);
						} catch (NotResponsibleException e) {
							// okay
						}
				
//				for (ICamera c : scene.getCameras())
//					if (c instanceof DefaultCamera){
//						DefaultCamera cam = (DefaultCamera) c;
//						for (ActionType a : activeActions){
//							try {
//								cam.handleAction(new Action(a, null));
//							}
//							catch (NotResponsibleException e) {
//								// Handler was not responsible
//								// thats no problem
//							}
//						}
//			}
			}
		}
	}
		
	public void keyPressed(KeyEvent e) {
		List<Action> tmp = mapping.getActions(Key.byCode(e.getKeyCode()));
		if (tmp!=null){
			for (Action a : tmp) 
				if (!activeActions.contains(a)) 
					activeActions.add(a);
		}
		//System.out.println(activeActions);
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode()==27) System.exit(0);
		List<Action> tmp = mapping.getActions(Key.byCode(e.getKeyCode()));
		if (tmp!=null){
			activeActions.removeAll(tmp);
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO fix mousewheel
//		if (scene.getCamera() instanceof DefaultCamera){
//			DefaultCamera cam = (DefaultCamera) scene.getCamera();
//			cam.setZoom(cam.getZoom()+e.getWheelRotation()*0.5);
//		}
//		
	}

	public void mouseClicked(MouseEvent e) {
		logger.debug("mouseClicked() " + e);
		((GuiCamera)scene.getCameraByClass(GuiCamera.class)).handleMouseEvent(e);
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		((GuiCamera)scene.getCameraByClass(GuiCamera.class)).handleMouseEvent(e);
	}

	public void mouseReleased(MouseEvent e) {
		((GuiCamera)scene.getCameraByClass(GuiCamera.class)).handleMouseEvent(e);
	}
	
	public void mouseDragged(MouseEvent e) {
//		if (scene.getCamera() instanceof DefaultCamera) {
//			Vector4d tmp = scene.calc3DMousePoint(e.getPoint());
//			DefaultCamera cam = (DefaultCamera)scene.getCamera();
//			double deltax = tmp.x - basepoint.x;
//			double deltay = tmp.y - basepoint.y;
//			//cam.setPosition(cam.getPosx() + deltax, cam.getPosy() + deltay);
//			basepoint = tmp;
//		}
		
	}

	public void mouseMoved(MouseEvent e) {
		
//		if (scene != null) {
//			DefaultCamera cam = ((DefaultCamera)scene.getCameraByClass(DefaultCamera.class));
//			Vector4d tmp = cam.calc3DMousePoint(e.getPoint());
//			if (tmp != null) {
//				scene.marker.posx = tmp.x;
//				scene.marker.posy = tmp.y;
//				scene.marker.posz = tmp.z;
//			}
//		}
		
	}

	
}
