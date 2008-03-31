package vidis.vis.objects;

import javax.media.opengl.GL;

import vidis.vis.objects.interfaces.Renderable;
import vidis.vis.util.Vector3d;

public class Marker extends Renderable {

	public double posx;
	public double posy;
	public double posz = 0;
	
	private double w;
	
	public Marker(){
		new Thread(){
			@Override
			public void run() {
				while (true){
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
					}
					w += Math.PI /360;
					if (w > 2*Math.PI) w -= 2*Math.PI;
				}
			}
		}.start();
	}
	public void render(GL gl) {
		
		double h=0.3;
		double r=0.1;
		double dw = 2 * Math.PI  / 3;
		Vector3d v0 = new Vector3d(posx, posy, posz);
		Vector3d v1 = new Vector3d(posx + r*Math.sin(w), posy + r*Math.cos(w), posz-h);
		Vector3d v2 = new Vector3d(posx + r*Math.sin(w+dw), posy + r*Math.cos(w+dw), posz-h);
		Vector3d v3 = new Vector3d(posx + r*Math.sin(w+2*dw), posy + r*Math.cos(w+2*dw), posz-h);
		
		gl.glColor3d(1.0, 0.0, 0.0);
		gl.glPolygonMode(GL.GL_FRONT, GL.GL_FILL);
		gl.glBegin(GL.GL_TRIANGLE_STRIP);
			gl.glVertex3d(v1.x, v1.y, v1.z);
			gl.glVertex3d(v2.x, v2.y, v2.z);
			gl.glVertex3d(v3.x, v3.y, v3.z);
			gl.glVertex3d(v0.x, v0.y, v0.z);
			gl.glVertex3d(v1.x, v1.y, v1.z);
			gl.glVertex3d(v2.x, v2.y, v2.z);
		gl.glEnd();
		gl.glColor3d(0.0, 0.0, 0.0);
		gl.glPolygonMode(GL.GL_BACK, GL.GL_NONE);
		gl.glPolygonMode(GL.GL_FRONT, GL.GL_LINE);
		gl.glBegin(GL.GL_TRIANGLE_STRIP);
		gl.glVertex3d(v1.x, v1.y, v1.z);
		gl.glVertex3d(v2.x, v2.y, v2.z);
		gl.glVertex3d(v3.x, v3.y, v3.z);
		gl.glVertex3d(v0.x, v0.y, v0.z);
		gl.glVertex3d(v1.x, v1.y, v1.z);
		gl.glVertex3d(v2.x, v2.y, v2.z);
		gl.glEnd();


	}
	public Vector3d getPos() {
		return new Vector3d(this.posx, this.posy, 0);
	}
}
