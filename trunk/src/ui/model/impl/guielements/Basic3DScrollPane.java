package ui.model.impl.guielements;

import java.awt.Color;

import javax.media.opengl.GL;

import org.apache.log4j.Logger;

import ui.model.structure.AGuiContainer;

public class Basic3DScrollPane extends AGuiContainer {
	private static Logger logger = Logger.getLogger(Basic3DScrollPane.class);
	private Color color = Color.ORANGE;
	private Color marker_color = Color.ORANGE;
	private double marker_opaque = 0.7;
	private double opaque = 0.3;
	private double scrollpane_padding_right = 0.7;
	private double scrollpane_padding_top = 1.1;
	private double scrollpane_padding_bottom = 1.1;
	private double scrollpane_width = 0.3;
	// position in percentage
	private double scrollpane_position = 0.3;
	
	private double test = 0;
	private double test2 = 1;
	
	public void renderContainer(GL gl) {
		if(test2 >= 0) {
			setPosition(test2);
			test2 -= 0.01;
		} else if(test <= 1) {
			setPosition(test);
			test += 0.01;
		} else {
			// reset
			test = 0;
			test2 = 1;
		}
		gl.glPushMatrix();
			if (true) {
				gl.glColor4d(marker_color.getRed()/255d, marker_color.getGreen()/255d, marker_color.getBlue()/255d, marker_opaque);
				// upper arrow
				gl.glBegin(GL.GL_TRIANGLE_STRIP);
					gl.glVertex2d(getWidth()/2, 0 - 0.5);
					gl.glVertex2d(getWidth()/2 + 0.5, 0.8 - 0.5);
					gl.glVertex2d(getWidth()/2-0.5, 0.8 -0.5);
				gl.glEnd();
				// lower arrow
				gl.glBegin(GL.GL_TRIANGLE_STRIP);
					gl.glVertex2d(getWidth()/2, getHeight()+0.5);
					gl.glVertex2d(getWidth()/2 + 0.5, getHeight()-0.8+0.5);
					gl.glVertex2d(getWidth()/2-0.5, getHeight()-0.8+0.5);
				gl.glEnd();
				// bar at scrollpane_position
				gl.glBegin(GL.GL_QUADS);
					gl.glVertex2d(getWidth()/2 - 0.4, (getHeight())*scrollpane_position - 0.2);
					gl.glVertex2d(getWidth()/2 - 0.4, (getHeight())*scrollpane_position + 0.2);
					gl.glVertex2d(getWidth()/2 + 0.5, (getHeight())*scrollpane_position + 0.2);
					gl.glVertex2d(getWidth()/2 + 0.5, (getHeight())*scrollpane_position - 0.2);	
				gl.glEnd();
				// line
				gl.glColor4d(color.getRed()/255d, color.getGreen()/255d, color.getBlue()/255d, opaque);
				gl.glBegin(GL.GL_QUADS);
					gl.glVertex2d(0, 0);
					gl.glVertex2d(0, getHeight());
					gl.glVertex2d(getWidth(), getHeight());
					gl.glVertex2d(getWidth(), 0);	
				gl.glEnd();
			}
		gl.glPopMatrix();
	}
	
	/**
	 * sets the position (percentage)
	 * @param position double in [0..1]
	 */
	public void setPosition(double position) {
		this.scrollpane_position = Math.max(0, Math.min(1.0, position));
	}
	
	@Override
	public double getX() {
		return getParent().getWidth()-scrollpane_padding_right-getWidth();
	}
	
	@Override
	public double getY() {
		return scrollpane_padding_top;
	}
	
	@Override
	public double getWidth() {
		return scrollpane_width;
	}
	
	@Override
	public double getHeight() {
		return getParent().getHeight()-scrollpane_padding_bottom-scrollpane_padding_top;
	}
}
