package vidis.data.mod;

import vidis.sim.exceptions.ObstructInitCallException;
import vidis.data.sim.ISimLinkCon;

public interface IUserLink extends IUserComponent {

	/**
	 * initializing function called by the API
	 * 
	 * DO NOT CALL THIS FUNCTION ON YOUR OWN!
	 * @param simulatorComponent the simulator component that represents this object in the API
	 * @throws ObstructInitCallException thrown if you call this function
	 */
	public void init(ISimLinkCon simulatorComponent) throws ObstructInitCallException;

	/**
	 * retrieve the node connected to this link
	 * @param me simply tell who you are
	 * @return the user node at the other end; if you are not on this link you get NULL
	 */
	public IUserNode getOtherNode(IUserNode me);
	
	/**
	 * retrieve delay of this link
	 * @return long
	 */
	public long getDelay();
}