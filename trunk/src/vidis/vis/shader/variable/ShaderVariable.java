package vidis.vis.shader.variable;

import javax.media.opengl.GL;

import vidis.vis.util.Vector3d;
import vidis.vis.util.Vector3f;

public class ShaderVariable {
	protected VariableType variableType;
	protected DataType dataType;
	protected String name;
	protected int address;
	
	
	public ShaderVariable(VariableType variableType, DataType dataType, String name) {
		this.variableType = variableType;
		this.dataType = dataType;
		this.name = name;
	}

	public VariableType getVariableType() {
		return variableType;
	}

	public DataType getDataType() {
		return dataType;
	}

	public String getName() {
		return name;
	}
	public void setAddress(int address) {
		this.address = address;
	}
	public int getAddress() {
		return address;
	}
	
	public String toString() {
		return getVariableType().getType() + " " + getDataType().getType() + " " + getName() + " @ "+ getAddress();
	}
	// TODO replace with own exception
	// TODO add a case for every possible combination
	public void setValue(Object value, GL gl) throws java.lang.reflect.MalformedParameterizedTypeException {
		if (dataType.getJavaClass().equals(value.getClass())) {
			switch (variableType) {
			case UNIFORM:
				switch (dataType){
				case VEC3: 
					Vector3f v = (Vector3f) value;
					gl.glUniform3f(this.address, v.x, v.y, v.z);
				break;
				case VEC4:
				break;
				case FLOAT:
				break;
				}
			break;
			case ATTRIBUTE:
				switch (dataType){
				case VEC3: 
					Vector3d v = (Vector3d) value;
					gl.glVertexAttrib3d(this.address, v.x, v.y, v.z);
				break;
				case VEC4:
				break;
				case FLOAT:
					gl.glVertexAttrib1f(this.address, (Float) value);
				break;
				}				
			break;
			}
		}
		else throw new java.lang.reflect.MalformedParameterizedTypeException();
	}
	
	
}
