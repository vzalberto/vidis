package ui.vis.shader.impl;

import javax.media.opengl.GL;

public class VertexShader extends Shader {

	public void create(GL gl) {
		// create shader
		this.shaderid = gl.glCreateShader(GL.GL_VERTEX_SHADER);
		
	}
}
