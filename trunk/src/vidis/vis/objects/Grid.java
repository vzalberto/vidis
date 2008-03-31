package vidis.vis.objects;

import javax.media.opengl.GL;

import vidis.vis.objects.interfaces.Renderable;
import vidis.vis.util.Vector3d;
/**
 * Draws a Grid
 * @author Christoph
 *
 */
public class Grid extends Renderable {

	private double x=-10;
	private double y=-10;
	private double deltax=0.5;
	private double deltay=0.5;
	private double zoff = 0.02;
	private int anzx=40;
	private int anzy=40;
	public Grid(){
		
	}
	public void render(GL gl) {
		// draw Lines
		gl.glColor3f(0.5f, 0.5f, 0.5f);
      	gl.glLineWidth(0.5f);
      	gl.glBegin(GL.GL_LINES);
      	for (double ix=x; ix <=x+anzx*deltax; ix+=deltax){
      		gl.glVertex3d(ix, y, zoff);
      		gl.glVertex3d(ix, y+anzy*deltay, zoff);
      	}
      	for (double iy=y;iy <=y+anzy*deltay; iy+=deltay){
      		gl.glVertex3d(x, iy, zoff);
      		gl.glVertex3d(x+anzx*deltax, iy, zoff);
      	}
      	gl.glEnd();
      	// draw Points
		gl.glPointSize(2f);
		gl.glColor3f(0.7f, 0.7f, 0.7f);
		gl.glBegin(GL.GL_POINTS);
      	for (double ix=x; ix <=x+anzx*deltax; ix+=deltax)
      		for (double iy=y;iy <=y+anzy*deltay; iy+=deltay)
      			gl.glVertex3d(ix, iy, zoff);
      	gl.glEnd();
      
      	
    }
	public Vector3d getPos() {
		return new Vector3d(this.x, this.y, 0);
	}
}
