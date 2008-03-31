package vidis.vis.shader;

import java.util.ArrayList;
import java.util.List;

import javax.media.opengl.GL;

import vidis.vis.shader.variable.ShaderVariable;

public class Program {
	private List<Shader> shader;
	private List<ShaderVariable> vars = new ArrayList<ShaderVariable>();
	private int programid;
	
	public Program(){
		shader = new ArrayList<Shader>();
	}
	public void addShader(Shader s){
		shader.add(s);
		
	}
	public void create(GL gl) {
		this.programid = gl.glCreateProgram();
	}
	public void link(GL gl) {
		// adding shaders
		for (Shader s : shader)
			gl.glAttachShader(this.programid, s.getShaderId());
		// linking program
		gl.glLinkProgram(this.programid);
		// collecting variables and getting addresses
		for (Shader s : shader){
			for (ShaderVariable var : s.getVariables()) {
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
	public void use(GL gl){
		gl.glUseProgram(this.programid);
	}
	public int getProgramId() {
		return this.programid;
	}
	public ShaderVariable getVariableByName(String string) {
		for (ShaderVariable svar : vars)
			if (svar.getName().equals(string)) return svar;
		return null;
	}
}
