package ui.vis.shader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.media.opengl.GL;

import ui.vis.shader.variable.DataType;
import ui.vis.shader.variable.ShaderVariable;
import ui.vis.shader.variable.VariableType;

public abstract class Shader {
	protected int shaderid;

	protected List<ShaderVariable> vars = new ArrayList<ShaderVariable>();
	
	public List<ShaderVariable> getVariables() {
		return this.vars;
	}
	
	public void compile(GL gl) throws ShaderException {
		// compile shader
		gl.glCompileShader(this.shaderid);
	    IntBuffer erg = IntBuffer.allocate(1);
	    gl.glGetShaderiv(this.shaderid, GL.GL_COMPILE_STATUS, erg);
	    
	    if (erg.get(0) != 1) {
	    	
	    	throw new ShaderException("compilation failed ("+this.getShaderInfoLog(gl)+")");
	    }
	}

	protected static String loadShaderFromFile(String filename) throws IOException{
		BufferedReader brf = new BufferedReader(new FileReader(filename));
        String shadstr = "";
        String line;
        while ((line=brf.readLine()) != null) {
          shadstr += line + "\n";
        }
        return shadstr;
	}
	
	protected static List<ShaderVariable> parseForVariables(String source) {
		ArrayList<ShaderVariable> tmp = new ArrayList<ShaderVariable>();
		StringTokenizer linetok = new StringTokenizer(source, "\n");
		while (linetok.hasMoreTokens()) {
			String line = linetok.nextToken();
			if (!line.trim().startsWith("//")) {
				StringTokenizer wordtok = new StringTokenizer(line, " ");
				while (wordtok.hasMoreTokens()){
					String word = wordtok.nextToken();
					if (word.equals("uniform")) {
						VariableType vtype = VariableType.UNIFORM;
						DataType dtype = DataType.byTypeValue(wordtok.nextToken());
						String name = wordtok.nextToken().replaceFirst(";", "");
						ShaderVariable tmpvar = new ShaderVariable(vtype, dtype, name);
						tmp.add(tmpvar);
					}
					else if (word.equals("attribute")) {
						ShaderVariable tmpvar = new ShaderVariable(VariableType.ATTRIBUTE, DataType.byTypeValue(wordtok.nextToken()), wordtok.nextToken().replaceFirst(";", ""));
						tmp.add(tmpvar);
					}
				}
			}
		}
		return tmp;
	}
	public void loadSource(String filename, GL gl) throws ShaderException{
		// load source
		try {
			String source = loadShaderFromFile(filename);
		// find & register variables
			vars.addAll(parseForVariables(source));
		// give source to OpenGL
			gl.glShaderSource(this.shaderid, 1, new String[]{source}, null);
		} catch (IOException e){
			throw new ShaderException(e);
		}	
	}

	public int getShaderId() {
		return this.shaderid;
	}

	public String getShaderInfoLog(GL gl) {
		IntBuffer loglength = IntBuffer.allocate(1);
		gl.glGetShaderiv(this.shaderid, GL.GL_INFO_LOG_LENGTH, loglength);
		if ( loglength.get(0) > 0) {
			ByteBuffer data = ByteBuffer.allocate(loglength.get(0));
			gl.glGetShaderInfoLog(this.shaderid, loglength.get(0), loglength, data);
			String ret = "";
			for (Byte b : data.array()){
				ret += (char)b.intValue();
			}
			return ret;
		}
		return "No Infolog present";
	}
}
