package ui.vis.shader;

import java.util.List;

import javax.media.opengl.GL;

import ui.vis.shader.impl.ShaderException;
import ui.vis.shader.variable.ShaderVariable;

public interface IShader {

	public abstract List<IShaderVariable> getVariables();

	public abstract void create(GL gl);
	
	public abstract void compile(GL gl) throws ShaderException;

	public abstract void loadSource(String filename, GL gl)
			throws ShaderException;

	public abstract int getShaderId();

	public abstract String getShaderInfoLog(GL gl);

}