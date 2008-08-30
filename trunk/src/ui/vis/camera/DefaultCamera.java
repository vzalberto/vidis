package ui.vis.camera;

import java.awt.Point;
import java.awt.Rectangle;
import java.nio.DoubleBuffer;
import java.nio.IntBuffer;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import javax.vecmath.Vector4d;

import org.apache.log4j.Logger;

import ui.events.AEventHandler;
import ui.events.IVidisEvent;
import ui.events.MouseClickedEvent;
import ui.events.StartEvent;
import ui.events.StopEvent;
import ui.vis.Light;

public class DefaultCamera extends AEventHandler implements ICamera {
	private static Logger logger = Logger.getLogger( DefaultCamera.class );
	
	/* render target */
	private Rectangle target;
	/* Position of the camera */
	private double posx;
	private double posz;
	private double step = 0.1; // Scrollspeed
	/* Zoom */
	private double zoom;
	
	/* Actions */
	private boolean scrollLeft = false;
	private boolean scrollRight = false;
	private boolean scrollUp = false;
	private boolean scrollDown = false;
	
	private boolean zoomIn = false;
	private boolean zoomOut = false;
	
	/**
	 * used for calc3DMousePoint
	 */
	private DoubleBuffer model = DoubleBuffer.allocate(16), proj = DoubleBuffer
			.allocate(16);
	private IntBuffer view = IntBuffer.allocate(4);
	
	public DefaultCamera(){
		this.posx = 0;
		this.posz = 0;
		this.zoom = 1.0;
	}
	public void init(GL gl) {
		gl.glClearColor(0.4f, 0.4f, 0.4f, 1f);
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		
		gl.glEnable( GL.GL_DEPTH_TEST );
		gl.glEnable( GL.GL_LIGHTING );
		gl.glEnable( GL.GL_LIGHT0 );
//		gl.glEnable( GL.GL_LIGHT1 );
		gl.glViewport((int)target.getX(), (int)target.getY(), (int)target.getWidth(), (int)target.getHeight());
	
		
	}
	public void applyProjectionMatrix(GL gl) {
		GLU glu = new GLU();
		glu.gluPerspective( 30, (double) target.getWidth() / (double) target.getHeight(), 1.0, 90.0);
		glu.gluLookAt( 0, 5, -10, 0, 0, 0, 0, 1, 0);
	}
	public void applyViewMatrix(GL gl) {
		gl.glTranslated(posx, zoom, -posz);
		// matrizen auslesen f�r click berechnung
		gl.glGetDoublev(GL.GL_MODELVIEW_MATRIX, model);
		gl.glGetDoublev(GL.GL_PROJECTION_MATRIX, proj);
		gl.glGetIntegerv(GL.GL_VIEWPORT, view);
	}
	public String toString() {
		return "DefaultCamera at ("+posx+", "+posz+") Zoom: "+zoom;
	}
	public void setPosition(double posx, double posy){
		this.posx = posx;
		this.posz = posy;
	}
	public double getZoom() {
		return zoom;
	}
	public void setZoom(double zoom) {
		this.zoom = zoom;
	}
	public double getPosx() {
		return posx;
	}
	public void setPosx(double posx) {
		this.posx = posx;
	}
	public double getPosy() {
		return posz;
	}
	public void setPosy(double posy) {
		this.posz = posy;
	}
	
	public void handleEvent(IVidisEvent event) {
		logger.debug("handleEvent( "+event+" )" );
		switch ( event.getID() ) {
		case IVidisEvent.ScrollDown:
			if ( event instanceof StartEvent ) {
				this.scrollDown = true;
			}
			else if ( event instanceof StopEvent ) {
				this.scrollDown = false;
			}
			break;
		case IVidisEvent.ScrollLeft:
			if ( event instanceof StartEvent ) {
				this.scrollLeft = true;
			}
			else if ( event instanceof StopEvent ) {
				this.scrollLeft = false;
			}
			break;
		case IVidisEvent.ScrollUp:
			if ( event instanceof StartEvent ) {
				this.scrollUp = true;
			}
			else if ( event instanceof StopEvent ) {
				this.scrollUp = false;
			}
			break;
		case IVidisEvent.ScrollRight:
			if ( event instanceof StartEvent ) {
				this.scrollRight = true;
			}
			else if ( event instanceof StopEvent ) {
				this.scrollRight = false;
			}
			break;
		case IVidisEvent.MouseClickedEvent:
//			logger.info( event );
//			logger.info( ((MouseClickedEvent)event).mouseEvent );
//			logger.info( ((MouseClickedEvent)event).mouseEvent.getPoint() );
//			Vector4d point = calc3DMousePoint( ((MouseClickedEvent)event).mouseEvent.getPoint() );
//			logger.info( "picking: " + point);
			break;
		}
	}
	
	public void update() {
		if ( scrollUp ) {
			this.posz+=step; 
		}
		if ( scrollDown ) {
			this.posz-=step;
		}
		if ( scrollLeft ) {
			this.posx-=step;
		}
		if ( scrollRight ) {
			this.posx+=step;
		}
		if ( zoomIn ) {
			this.zoom-=step;
		}
		if ( zoomOut ) {
			this.zoom+=step;
		}
	}
	
	public Rectangle getTarget() {
		return target;
	}
	public void setTarget(Rectangle target) {
		this.target = target;
	}
	public void reshape(int x, int y, int width, int height) {
		this.target = new Rectangle(x, y, width, height);
		
	}

	public Vector4d calc3DMousePoint(Point p) {
		DoubleBuffer point1 = DoubleBuffer.allocate(3);
		DoubleBuffer point2 = DoubleBuffer
				.allocate(3);
		boolean p1, p2;
		GLU glu = new GLU();
		p1 = glu.gluUnProject(p.getX(), view.get(3) - p.getY() - 1, 0.0, model,
				proj, view, point1);
		p2 = glu.gluUnProject(p.getX(), view.get(3) - p.getY() - 1, 1.0, model,
				proj, view, point2);
		if (!p1 & !p2)
			return null;
		Vector4d P1 = new Vector4d( point1.array() );
		Vector4d P2 = new Vector4d( point2.array() );
		
		// Click Ray
		Vector4d P1P2 = new Vector4d();
		P1P2.sub( P2, P1 );

		Vector4d E0 = new Vector4d(0, 0, 0, 1);
		Vector4d EX = new Vector4d(1, 0, 0, 1);
		Vector4d EY = new Vector4d(0, 1, 0, 1);
		// Ebene
		Vector4d E0EX = new Vector4d();
		E0EX.sub( EX, E0 );
		Vector4d E0EY = new Vector4d();
		E0EY.sub( EY, E0 );

		//Vector4d EBENE = VMath.kreuz(E0EX, E0EY);
		Vector4d EBENE = new Vector4d(0, 0, 1, 0);
		// Schnittpunkt
		// 
		// Gerade:
		// P1 + v * P1P2
		// x = P1.x + v * P1P2.x
		// ...

		// Ebene:
		// EBENE.x * x + EBENE.y * y + EBENE.z * z = 0

		// EBENE.x * (P1.x + v * P1P2.x) +
		// EBENE.y * (P1.y + v * P1P2.y) +
		// EBENE.z * (P1.z + v * P1P2.z) = 0

		// EBENE.x * P1.x + EBENE.y * P1.y + EBENE.z * P1.z
		// --------------------------------------------------------- = y
		// - EBENE.x * P1P2.x - EBENE.y * P1P2.y - EBENE.z * P1P2.z

		double y = (EBENE.x * P1.x + EBENE.y * P1.y + EBENE.z * P1.z)
				/ (-EBENE.x * P1P2.x - EBENE.y * P1P2.y - EBENE.z * P1P2.z);

		Vector4d mul = new Vector4d();
		mul.scale(y, P1P2);
		Vector4d ret = new Vector4d();
		ret.add( P1, mul);
		return ret;
	}
	
	
}
