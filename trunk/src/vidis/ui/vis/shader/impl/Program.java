package vidis.ui.vis.shader.impl;

import java.util.ArrayList;
import java.util.List;

import javax.media.opengl.GL;

import vidis.ui.vis.shader.IProgram;
import vidis.ui.vis.shader.IShader;
import vidis.ui.vis.shader.IShaderVariable;
import vidis.ui.vis.shader.variable.ShaderVariable;

public class Program implements IProgram {
	private List<IShader> shader;
	private List<IShaderVariable> vars = new ArrayList<IShaderVariable>();
	private int programid;
	
	public Program(){
		shader = new ArrayList<IShader>();
	}
	/* (non-Javadoc)
	 * @see ui.vis.shader.impl.IProgram#addShader(ui.vis.shader.impl.Shader)
	 */
	public void addShader(IShader s){
		shader.add(s);
		
	}
	/* (non-Javadoc)
	 * @see ui.vis.shader.impl.IProgram#create(javax.media.opengl.GL)
	 */
	public void create(GL gl) {
		this.programid = gl.glCreateProgram();
	}
	/* (non-Javadoc)
	 * @see ui.vis.shader.impl.IProgram#link(javax.media.opengl.GL)
	 */
	public void link(GL gl) {
		// adding shaders
		for (IShader s : shader)
			gl.glAttachShader(this.programid, s.getShaderId());
		// linking program
		gl.glLinkProgram(this.programid);
		// collecting variables and getting addresses
		for (IShader s : shader){
			for (IShaderVariable var : s.getVariables()) {
				switch (var.getVariableType()) {
				case UNIFORM:
					var.setAddress(gl.glGetUniformLocation(this.programid, var.getName()));
				break;
				case ATTRIBUTE:
					var.setAddress(gl.glGetAttribLocation(this.programid, var.getName()));
				break;
				}
				this.vars.add(var);
			}
		}
		System.out.println(vars);
	}
	/* (non-Javadoc)
	 * @see ui.vis.shader.impl.IProgram#use(javax.media.opengl.GL)
	 */
	public void use(GL gl){
		gl.glUseProgram(this.programid);
	}
	/* (non-Javadoc)
	 * @see ui.vis.shader.impl.IProgram#getProgramId()
	 */
	public int getProgramId() {
		return this.programid;
	}
	/* (non-Javadoc)
	 * @see ui.vis.shader.impl.IProgram#getVariableByName(java.lang.String)
	 */
	public IShaderVariable getVariableByName(String string) {
		for (IShaderVariable svar : vars)
			if (svar.getName().equals(string)) return svar;
		return null;
	}
}
