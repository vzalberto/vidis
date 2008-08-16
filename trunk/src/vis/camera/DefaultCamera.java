package vis.camera;

import java.awt.Rectangle;
import java.nio.DoubleBuffer;
import java.nio.IntBuffer;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

import vis.WrongMatrixStackException;
import vis.input.Action;
import vis.input.ActionHandler;
import vis.input.NotResponsibleException;

public class DefaultCamera implements ICamera, ActionHandler {
	/* render target */
	private Rectangle target;
	/* Position of the camera */
	private double posx;
	private double posy;
	private double step = 0.1; // Scrollspeed
	/* Zoom */
	private double zoom;
	/**
	 * used for calc3DMousePoint
	 */
	private DoubleBuffer model = DoubleBuffer.allocate(16), proj = DoubleBuffer
			.allocate(16);
	private IntBuffer view = IntBuffer.allocate(4);
	
	public DefaultCamera(){
		this.posx = 0;
		this.posy = 0;
		this.zoom = 1.0;
	}
	public void init(GL gl) {
		gl.glClearColor(0.4f, 0.4f, 0.4f, 1f);
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		gl.glViewport((int)target.getX(), (int)target.getY(), (int)target.getWidth(), (int)target.getHeight());
	}
	public void applyProjectionMatrix(GL gl) throws WrongMatrixStackException {
		GLU glu = new GLU();
		glu.gluPerspective(30, (double) target.getWidth() / (double) target.getHeight(), 1.0, 90.0);
		glu.gluLookAt(0, -5, -10, 0, 0, 0, 0, 1, 0);
	}
	public void applyViewMatrix(GL gl) {
		gl.glTranslated(posx, -posy +zoom/2, zoom);
		// matrizen auslesen für click berechnung
		gl.glGetDoublev(GL.GL_MODELVIEW_MATRIX, model);
		gl.glGetDoublev(GL.GL_PROJECTION_MATRIX, proj);
		gl.glGetIntegerv(GL.GL_VIEWPORT, view);
	}
	public String toString() {
		return "DefaultCamera at ("+posx+", "+posy+") Zoom: "+zoom;
	}
	public void setPosition(double posx, double posy){
		this.posx = posx;
		this.posy = posy;
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
		return posy;
	}
	public void setPosy(double posy) {
		this.posy = posy;
	}
	public void handleAction(Action a) throws NotResponsibleException {
		switch (a){
		case SCROLL_UP:
			this.posy+=step; 
			break;
		case SCROLL_DOWN:
			this.posy-=step;
			break;
		case SCROLL_LEFT:
			this.posx-=step;
			break;
		case SCROLL_RIGHT:
			this.posx+=step;
			break;
		case ZOOM_IN:
			this.zoom-=step;
			break;
		case ZOOM_OUT:
			this.zoom+=step;
			break;
		default:
			throw new NotResponsibleException();
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
/*
	public Vector4d calc3DMousePoint(Point p) {
		DoubleBuffer point1 = DoubleBuffer.allocate(3), point2 = DoubleBuffer
				.allocate(3);
		boolean p1, p2;
		GLU glu = new GLU();
		p1 = glu.gluUnProject(p.getX(), view.get(3) - p.getY() - 1, 0.0, model,
				proj, view, point1);
		p2 = glu.gluUnProject(p.getX(), view.get(3) - p.getY() - 1, 1.0, model,
				proj, view, point2);
		if (!p1 & !p2)
			return null;
		Vector4d P1 = Vector4d.fromDoubleBuffer(point1, 1);
		Vector4d P2 = Vector4d.fromDoubleBuffer(point2, 1);
		// Click Ray
		Vector4d P1P2 = VMath.minus(P2, P1);

		Vector4d E0 = new Vector4d(0, 0, 0, 1);
		Vector4d EX = new Vector4d(1, 0, 0, 1);
		Vector4d EY = new Vector4d(0, 1, 0, 1);
		// Ebene
		Vector4d E0EX = VMath.minus(EX, E0);
		Vector4d E0EY = VMath.minus(EY, E0);

		Vector4d EBENE = VMath.kreuz(E0EX, E0EY);
		EBENE = new Vector4d(0, 0, 1, 0);
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

		return VMath.add(P1, VMath.mul(y, P1P2));
	}
	*/
}
