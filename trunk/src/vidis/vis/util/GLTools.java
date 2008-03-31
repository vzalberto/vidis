package vidis.vis.util;

import javax.media.opengl.GL;

public class GLTools {
	public static void drawCircle(double x, double y, double z, double radius, int segments, GL gl){
		gl.glPushMatrix();
			gl.glBegin(GL.GL_LINE_STRIP);
			for (int i=0; i<=segments; i++){
				double w = i*36d/180*Math.PI;
				
					gl.glVertex3d(x + Math.cos(w)*radius, y + Math.sin(w)*radius, z);
				
			}
			gl.glEnd();
		gl.glPopMatrix();
	}
	public static void drawFilledCircle(double x, double y, double z, double radius, int segments, GL gl){
		gl.glPushMatrix();
			gl.glPolygonMode(GL.GL_BACK, GL.GL_FILL);
			gl.glBegin(GL.GL_POLYGON);
			for (int i=0; i<=segments; i++){
				double w = i*36d/180*Math.PI;
				
					gl.glVertex3d(x + Math.cos(w)*radius, y + Math.sin(w)*radius, z);
				
			}
			gl.glEnd();
		gl.glPopMatrix();
	}

	
}
