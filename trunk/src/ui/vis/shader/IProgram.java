package ui.vis.shader;

import javax.media.opengl.GL;


public interface IProgram {

	public abstract void addShader(IShader s);

	public abstract void create(GL gl);

	public abstract void link(GL gl);

	public abstract void use(GL gl);

	public abstract int getProgramId();

	public abstract IShaderVariable getVariableByName(String string);

}