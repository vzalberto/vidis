package vidis.vis.objects;

import java.nio.FloatBuffer;

import javax.media.opengl.GL;

import vidis.sim.framework.annotations.ComponentInfo;
import vidis.sim.framework.components.SimulatorComponentPacket;
import vidis.vis.objects.interfaces.Animated;
import vidis.vis.objects.interfaces.Renderable;
import vidis.vis.objects.interfaces.Selectable;
import vidis.vis.util.GLTools;
import vidis.vis.util.Vector3d;
import vidis.vis.util.Vector4d;
/**
 * das renderbare Paket
 * @author Christoph
 *
 */
public class Packet extends Renderable implements Selectable, Animated {

	private boolean selected;
	private SimulatorComponentPacket orginal;
	private double posx;
	private double posy;
	private double zoff = -0.01;
	private FloatBuffer color;
	private double w = 0;
	
	public Packet(SimulatorComponentPacket orginal){
		this.orginal = orginal;
		posx = -1;
		posy = -1;
		if (orginal!=null && orginal.getClass().getAnnotation(ComponentInfo.class)!=null)
			color = FloatBuffer.wrap(new float[]{orginal.getClass().getAnnotation(ComponentInfo.class).color_red(),
							orginal.getClass().getAnnotation(ComponentInfo.class).color_green(),
							orginal.getClass().getAnnotation(ComponentInfo.class).color_blue()});
		else
			color = FloatBuffer.wrap(new float[]{0,0,1});
		
	}
	public void render(GL gl) {
		gl.glLineWidth(1.5f);
		gl.glColor3fv(color);
		gl.glPushMatrix();
			gl.glTranslated(posx, posy, zoff);
			gl.glRotated(w / 180.0 * Math.PI, 0d, 0d, 1d);
			
			gl.glBegin(GL.GL_LINE_STRIP);
				gl.glVertex3d( -0.1,  -0.1, 0);
				gl.glVertex3d( +0.1,  -0.1, 0);
				gl.glVertex3d( +0.1,  +0.1, 0);
				gl.glVertex3d( -0.1,  +0.1, 0);
				gl.glVertex3d( -0.1,  -0.1, 0);
			gl.glEnd();
			gl.glLineWidth(3f);
			gl.glBegin(GL.GL_LINES);
				gl.glVertex3d( -0.12, 0, 0);
				gl.glVertex3d( +0.12, 0, 0);
				gl.glVertex3d(0, -0.12, 0);
				gl.glVertex3d(0,  +0.12, 0);
			gl.glEnd();
			
			
		gl.glPopMatrix();
		if (selected){
			gl.glColor3f(1f, 1f, 0f);
			gl.glLineWidth(1f);
			GLTools.drawFilledCircle(posx, posy, 0.1, 0.12 + 0.05, 10, gl);
		}
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
	public boolean isHit(Vector4d point) {
		//System.out.print("isHit("+point+") ");
		double dx = Math.abs(posx - point.x);
		double dy = Math.abs(posy - point.y);
		double dist = Math.sqrt(Math.pow(dx, 2)+Math.pow(dy, 2));
		this.selected = dist <= 0.12;
		//System.out.println(this.selected);
		return this.selected;
	}
	public boolean isSelected() {
		return this.selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public void animate() {
		this.w += 1;
		if (this.w < 360) this.w-=360;
	}
	public Vector3d getPos() {
		return new Vector3d(this.posx, this.posy, 0);
	}

}
