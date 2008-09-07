package vidis.data.var.vars;

import vidis.data.annotation.DisplayType;
import vidis.data.var.AVariable;
import vidis.data.var.IVariableChangeListener;

public class DefaultVariable extends AVariable {
	
	private Object data;

	public DefaultVariable(String id, Object value) {
		this(id, DisplayType.SHOW_SWING, value);
	}

	public DefaultVariable(String id, DisplayType type, Object value) {
		super(id, type);
		this.setData(value);
	}

	/**
	 * retrieve the class type of the data stored in this variable
	 * 
	 * @return class object
	 */
	public Class<?> getType() {
		return data.getClass();
	}

	/**
	 * retrieve the data stored in this variable
	 * 
	 * @return the data stored
	 */
	public Object getData() {
		return data;
	}

	/**
	 * set the data of this variable
	 * 
	 * @param data
	 *          the data
	 */
	private void setData(Object data) {
		this.data = data;
	}

	/**
	 * update variable data; SHOULD BE DONE ALWAYS IN FAVOR OF CREATING NEW
	 * VARIABLE!
	 * 
	 * @param data
	 *          the new value
	 */
	public void update(Object data) {
		if (data != null && !data.equals(getData())) {
			setData(data);
			// changed
			synchronized (this.getVariableChangeListeners()) {
				for (IVariableChangeListener l : this.getVariableChangeListeners()) {
					// System.err.println(l.toString() + ".variableChanged(" +
					// getIdentifier() + ")");
					l.variableChanged(getIdentifier());
				}
			}
		}
	}

	/**
	 * a common to string method
	 */
	public String toString() {
		return "(" + getType().getName() + ")" + getIdentifier() + "=" + getData().toString();
	}

	@Override
	public Class<? extends AVariable> getVariableType() {
		return this.getClass();
	}
}
