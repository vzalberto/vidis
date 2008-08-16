package data.var;


/**
 * the Variable Change Listener Interface
 * @author Christoph
 *
 */
public interface IVariableChangeListener {

	public void variableChanged(String id);
	public void variableAdded(String id);
	public void variableRemoved(String id);
	
}
