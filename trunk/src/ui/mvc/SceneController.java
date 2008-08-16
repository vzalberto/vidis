package ui.mvc;

import java.util.LinkedList;
import java.util.List;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;

import org.apache.log4j.Logger;

import ui.events.CameraEvent;
import ui.events.IVidisEvent;
import ui.events.ObjectEvent;
import ui.events.VidisEvent;
import ui.input.InputListener;
import ui.model.structure.IGuiContainer;
import ui.model.structure.IVisObject;
import ui.mvc.api.AController;
import ui.mvc.api.Dispatcher;
import ui.vis.camera.GuiCamera;
import ui.vis.camera.ICamera;
import ui.vis.multipass.RenderPass;
import ui.vis.objects.Axis;
import ui.vis.objects.Grid;

import com.sun.opengl.util.Animator;

public class SceneController extends AController implements GLEventListener {

	private static Logger logger = Logger.getLogger( SceneController.class );
	private static Logger glLogger = Logger.getLogger( "vis.opengl" );
	
	private List<ICamera> cameras = new LinkedList<ICamera>();
	private List<IVisObject> objects = new LinkedList<IVisObject>();
	
	private GLCanvas canvas;
	
	
	/**
	 * Used to animate the scene
	 */
	private Animator animator;
	
	public SceneController() {
		logger.debug( "Constructor()" );
		addChildController( new CameraController() );
		addChildController( new GuiController() );
		
		registerEvent( IVidisEvent.InitScene );
		
		registerEvent( IVidisEvent.CameraRegister,
					   IVidisEvent.CameraUnregister );
		
		registerEvent( IVidisEvent.ObjectRegister, 
					   IVidisEvent.ObjectUnregister );
	}
	
	@Override
	public void handleEvent( IVidisEvent event ) {
		logger.debug( "handleEvent( "+event+" )" );
		switch ( event.getID() ) {
		case IVidisEvent.InitScene:
			initialize();
			Dispatcher.forwardEvent( new VidisEvent<GLCanvas>( IVidisEvent.InitWindow, canvas ) );
			
			Dispatcher.forwardEvent( IVidisEvent.InitCamera );
			Dispatcher.forwardEvent( IVidisEvent.InitGui );
			break;
		case IVidisEvent.CameraRegister:
			registerCamera( ((CameraEvent)event).getCamera() );
			break;
		case IVidisEvent.CameraUnregister:
			unregisterCamera( ((CameraEvent)event).getCamera() );
			break;	
		case IVidisEvent.ObjectRegister:
			registerObject( ((ObjectEvent)event).getObject() );
			break;
		case IVidisEvent.ObjectUnregister:
			unregisterObject( ((ObjectEvent)event).getObject() );
			break;
		}	
		forwardEventToChilds( event );
	}

	private void initialize() {
		canvas = new GLCanvas();
		canvas.addGLEventListener( this );
		
		InputListener l = new InputListener();
		canvas.addKeyListener(l);
		canvas.addMouseWheelListener(l);
		canvas.addMouseMotionListener(l);
		canvas.addMouseListener(l);
		
		// add some default objects
		
		registerObject( new Grid() );
		registerObject( new Axis() );
	}
	
	/**
	 * display event
	 * draws the whole scene
	 */
	public void display(GLAutoDrawable drawable) {
		final GL gl = drawable.getGL();
		
		//XXX update cameras somewhere else
		for ( ICamera c : cameras ) {
			c.update();
		}
		
		for ( ICamera c : cameras ) {
			// INIT
			c.init(gl);
			
			// PROJECTION
			gl.glMatrixMode( GL.GL_PROJECTION );
			gl.glLoadIdentity();
			c.applyProjectionMatrix(gl);
			
			// VIEW
			gl.glMatrixMode( GL.GL_MODELVIEW );
			gl.glLoadIdentity();
			c.applyViewMatrix(gl);
			
			// MODEL
			drawModel( gl, c);
			
		}

	}

	private void drawModel( GL gl, ICamera c ) {
		if ( c instanceof GuiCamera) {
			gl.glPushMatrix();
			for ( IVisObject o : objects ) {
				if ( o instanceof IGuiContainer ) {
					o.render(gl);
				}
			}
			gl.glPopMatrix();
		}
		else {
			for ( RenderPass p : RenderPass.values()) {
				p.setup(gl);
				// MODEL
				gl.glPushMatrix();
					for ( IVisObject o : objects ) {
						if ( ! (o instanceof IGuiContainer) ) {
							o.render(gl);
						}
					}
				gl.glPopMatrix();
			}
		}
	}
	
	
	
	/**
	 * displayChanged event
	 * called when displaymode has changed
	 */
	public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
		glLogger.debug( "displayChanged()" );
	}

	/**
	 * init method
	 * called when gl context is created
	 */
	public void init(GLAutoDrawable drawable) {
		glLogger.debug( "init()" );
		final GL gl = drawable.getGL();
		
		// enable / disable some global stuff
		
		animator = new Animator(drawable);
		animator.start();
	}

	/**
	 * reshape event
	 */
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		glLogger.debug( "reshape()" );
		// update the camera targets
		for (ICamera c : cameras)
			c.reshape(x, y, width, height);
	}
	
	private void registerCamera( ICamera c ) {
		cameras.add(c);
	}
	private void unregisterCamera( ICamera c ) {
		cameras.remove(c);
	}

	private void registerObject( IVisObject o ) {
		objects.add(o);
	}
	private void unregisterObject( IVisObject o ) {
		objects.remove(o);
	}
}
