package ui.mvc;

import java.util.ArrayList;
import java.util.Collections;
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
import ui.model.impl.Link;
import ui.model.impl.Node;
import ui.model.impl.Packet;
import ui.model.structure.IGuiContainer;
import ui.model.structure.IVisObject;
import ui.mvc.api.AController;
import ui.mvc.api.Dispatcher;
import ui.vis.Light;
import ui.vis.camera.GuiCamera;
import ui.vis.camera.ICamera;
import ui.vis.objects.Axis;
import ui.vis.objects.Grid;
import ui.vis.shader.ShaderFactory;

import com.sun.opengl.util.Animator;

public class SceneController extends AController implements GLEventListener {

	private static Logger logger = Logger.getLogger( SceneController.class );
	private static Logger glLogger = Logger.getLogger( "vis.opengl" );
	
	private List<ICamera> cameras = new LinkedList<ICamera>();
	private List<IVisObject> objects = Collections.synchronizedList( new LinkedList<IVisObject>() );
	private List<IVisObject> toDel = Collections.synchronizedList( new ArrayList<IVisObject>() );
	
	private GLCanvas canvas;
	
	
	/**
	 * Used to animate the scene
	 */
	private Animator animator;
	
	private int wantedFps = 25;
	
	private long startTime;
	
	
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
		
		// do the del thing
		objects.removeAll( toDel );
		toDel.clear();
		
		final GL gl = drawable.getGL();
		
		startTime = System.currentTimeMillis();
		
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
		
		long usedTime = System.currentTimeMillis() - startTime;
		
		double fps = 1000d / usedTime;
		
		Dispatcher.forwardEvent( new VidisEvent<Double>( IVidisEvent.FPS, fps ) );
		
	}

	private void drawModel( GL gl, ICamera c ) {
		if ( c instanceof GuiCamera) {
			gl.glPushMatrix();
			synchronized ( objects ) {
				for ( IVisObject o : objects ) {
					if ( o instanceof IGuiContainer ) {
						o.render(gl);
					}
				}
			}
			gl.glPopMatrix();
		}
		else {
//			for ( RenderPass p : RenderPass.values()) {
//				p.setup(gl);
				// MODEL  with draw order:
				// first draw the nodes
			    // then the back side of the links
				// then the packets
				// and finally the front sides of the links
				gl.glPushMatrix();
				synchronized ( objects ) {
					gl.glEnable( GL.GL_LIGHTING );
					// nodes
					gl.glDisable( GL.GL_LIGHT0 );
					gl.glEnable( GL.GL_LIGHT1 );
					gl.glDisable( GL.GL_LIGHT2 );
					for ( IVisObject o : objects ) {
						if ( (o instanceof Node) ) {
							o.render(gl);
						}
					}
					// back side of links
					gl.glEnable( GL.GL_LIGHT0 );
					gl.glDisable( GL.GL_LIGHT1 );
					gl.glDisable( GL.GL_LIGHT2 );
					gl.glEnable( GL.GL_CULL_FACE );
					gl.glCullFace( GL.GL_BACK );
					Link.useShaderProgram(gl);
					for ( IVisObject o : objects ) {
						if ( (o instanceof Link) ) {
							o.render(gl);
						}
					}
					ShaderFactory.removeAllPrograms(gl);
					gl.glDisable( GL.GL_CULL_FACE );
					// packets
					gl.glDisable( GL.GL_LIGHT0 );
					gl.glDisable( GL.GL_LIGHT1 );
					gl.glEnable( GL.GL_LIGHT2 );
					for ( IVisObject o : objects ) {
						if ( (o instanceof Packet) ) {
							o.render(gl);
						}
					}
					// front side of links
					gl.glEnable( GL.GL_LIGHT0 );
					gl.glDisable( GL.GL_LIGHT1 );
					gl.glDisable( GL.GL_LIGHT2 );
					gl.glEnable( GL.GL_CULL_FACE );
					gl.glCullFace( GL.GL_FRONT );
					Link.useShaderProgram(gl);
					for ( IVisObject o : objects ) {
						if ( (o instanceof Link) ) {
							o.render(gl);
						}
					}
					ShaderFactory.removeAllPrograms(gl);
					gl.glDisable( GL.GL_CULL_FACE );
					// rest
					gl.glDisable( GL.GL_LIGHTING );
					for ( IVisObject o : objects ) {
						if ( !(o instanceof Node) && !(o instanceof Link) && !(o instanceof Packet) && !(o instanceof IGuiContainer) ) {
							o.render(gl);
						}
					}
				}
				gl.glPopMatrix();
//			}
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
		Light.initNodeLight(gl);
		Light.initLinkLight(gl);
		Light.initPacketLight(gl);
		
		Link.setupShaderProgram(gl);
		
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
		toDel.add(o);
	}
}
