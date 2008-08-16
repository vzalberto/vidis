package vis.glutils;

import javax.media.opengl.GL;

public class GLOption {
	public interface GLConfig {
		public void execute( GL gl );
	}
	public enum ShadeModel implements GLConfig{
		FLAT(GL.GL_FLAT),
		SMOOTH(GL.GL_SMOOTH);
		int mode;
		private ShadeModel(int param){
			this.mode = param;
		}
		public void execute(GL gl) {
			gl.glShadeModel(mode);
		}
	}
	
	public enum DepthFunc implements GLConfig{
		LEQUAL(GL.GL_LEQUAL),
		EQUAL(GL.GL_EQUAL);
		private int func;
		private DepthFunc(int func){
			this.func = func;
		}
		public void execute(GL gl) {
			gl.glDepthFunc(func);
		}
	}

	public enum Blending implements GLConfig{
		NONE(),
		ONE_ONE( GL.GL_ONE, GL.GL_ONE ),
		ZERO_SRC_COLOR( GL.GL_ZERO, GL.GL_SRC_COLOR);
		boolean enabled=true;
		int sfactor;
		int dfactor;
		private Blending(int s, int d){
			this.sfactor = s;
			this.dfactor = d;
		}
		private Blending(){
			enabled = false;
		}
		public void execute(GL gl) {
			if ( enabled ) {
				gl.glEnable( GL.GL_BLEND );
				gl.glBlendFunc(sfactor, dfactor);
			}
			else {
				gl.glDisable( GL.GL_BLEND );
			}
		}
	}
}
