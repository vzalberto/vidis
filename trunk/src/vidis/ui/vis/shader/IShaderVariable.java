package vidis.ui.vis.shader;

import javax.media.opengl.GL;

import vidis.ui.vis.shader.variable.DataType;
import vidis.ui.vis.shader.variable.VariableType;

public interface IShaderVariable {

	public abstract VariableType getVariableType();

	public abstract DataType getDataType();

	public abstract String getName();

	public abstract void setAddress(int address);

	public abstract int getAddress();

	public abstract String toString();

	// TODO replace with own exception
	// TODO add a case for every possible combination
	public abstract void setValue(Object value, GL gl)
			throws java.lang.reflect.MalformedParameterizedTypeException;

}