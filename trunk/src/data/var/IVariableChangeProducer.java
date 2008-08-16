package data.var;

import java.util.List;

/**
 * This interface defines all methods used for handling our variable system
 * 
 * @author Christoph
 * 
 */
public interface IVariableChangeProducer {
	public void addVariableChangeListener(IVariableChangeListener l);

	public void removeVariableChangeListener(IVariableChangeListener l);
	
	public List<IVariableChangeListener> getVariableChangeListeners();
}