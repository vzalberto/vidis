package vidis.vis.objects;

import java.nio.DoubleBuffer;
import java.nio.IntBuffer;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

import vidis.vis.objects.interfaces.Renderable;
import vidis.vis.util.Vector3d;
import vidis.vis.util.Vector4d;

public class Clicker extends Renderable {

	private int mausx=0, mausy=0;
	public DoubleBuffer 						 
	 point1 = DoubleBuffer.allocate(3),
	 point2 = DoubleBuffer.allocate(3),
	 point3 = DoubleBuffer.allocate(3);
	
	public Vector4d thepoint = null;
	
	public void render(GL gl) {
		
		gl.glBegin(GL.GL_LINES);
			gl.glColor3f(0, 0, 0);
			gl.glVertex3dv(point1);
			gl.glColor3f(0.5f, 0, 0);
			gl.glVertex3dv(point2);
			gl.glColor3f(1, 0, 0);
			gl.glVertex3dv(point3);
		gl.glEnd();
		if (thepoint != null) {
			gl.glColor3f(0, 1, 0);
			gl.glPointSize(6);
			gl.glBegin(GL.GL_POINTS);
				gl.glVertex3d(thepoint.x, thepoint.y, thepoint.z);
			gl.glEnd();
		}
	}



	public int getMausx() {
		return mausx;
	}



	public void setMausx(int mausx) {
		this.mausx = mausx;
	}



	public int getMausy() {
		return mausy;
	}



	public void setMausy(int mausy) {
		this.mausy = mausy;
	}
	public Vector3d getPos() {
		return new Vector3d(this.mausx, this.mausy, 0);
	}
}
