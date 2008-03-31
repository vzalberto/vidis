package vidis.sim.framework.interfaces;

import vidis.sim.framework.observe.SimulatorEventProducerInt;

/**
 * the interface all simulator components implement
 * 
 * @author dominik
 *
 */
public interface SimulatorComponentInt extends SimulatorEventProducerInt {
	/**
	 * set the factory that should be used by this class
	 * a factory provides the correct classes that should be
	 * used throughout the used module
	 * @param factory factory to use
	 */
	public void setFactory(ComponentFactoryInt factory);
	
	/**
	 * get the factory used by this component
	 */
	public ComponentFactoryInt getFactory();
	
	/**
	 * this function holds the execution code for the implemented component
	 * @return true or false
	 */
	public boolean execute();
	
	/**
	 * get the unique component id for this object
	 * @return unique integer identifier
	 */
	public int getCID();
	
	/**
	 * generate xml output structure
	 * @return string of xml
	 */
	public String toXML();
	
	/**
	 * write the unique component id for this object to XML format
	 * @return string of xml (<id>this.getCID()</id>)
	 */
	public String toXML_CID();
	
	/**
	 * create a component node, but only as reference to real instance
	 * @return
	 */
	public String toXML_byREF();
}
