package data.sim;

import data.var.IVariableContainer;

/**
 * this is the basic interface for all components that may be executable by the
 * simulator
 * 
 * @author dominik
 * 
 */
public interface IComponent extends IVariableContainer {

	/**
	 * a default component of the simulator implements this function so that the
	 * simulator can execute it
	 */
	public void execute();

	public void kill();
}
