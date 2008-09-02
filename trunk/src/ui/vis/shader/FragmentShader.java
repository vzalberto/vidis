package ui.vis.shader;

import javax.media.opengl.GL;

public class FragmentShader extends Shader {

	public void create(GL gl) {
		// create shader
		this.shaderid = gl.glCreateShader(GL.GL_FRAGMENT_SHADER);
	}

}
