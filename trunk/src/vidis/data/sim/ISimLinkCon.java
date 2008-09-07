package vidis.data.sim;

import vidis.data.mod.IUserNode;

/**
 * this class provides all necessary functions for the user
 * to implement a nice module
 * @author dominik
 *
 */
public interface ISimLinkCon extends IAComponentCon {

	/**
	 * retrieve the node A connected at one side of the link
	 * @return the Node connected at one side
	 */
	public IUserNode getNodeA();
	
	/**
	 * retrieve the node B connected at the other side of the link
	 * @return the Node connected at the other side
	 */
	public IUserNode getNodeB();
	
	/**
	 * get delay of this link
	 * @return long
	 */
	public long getDelay();
}
