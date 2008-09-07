package vidis.data.mod;

import vidis.sim.exceptions.ObstructInitCallException;
import vidis.data.sim.ISimNodeCon;


public interface IUserNode extends IUserComponent {

	/**
	 * initializing function called by the API
	 * 
	 * DO NOT CALL THIS FUNCTION ON YOUR OWN!
	 * 
	 * DO NO ANNOTATE THIS METHOD
	 * @param simulatorComponent the simulator component that represents this object in the API
	 * @throws ObstructInitCallException thrown if you call this function
	 */
	public void init(ISimNodeCon simulatorComponent) throws ObstructInitCallException;

	/**
	 * receive a packet
	 * 
	 * DO NO ANNOTATE THIS METHOD
	 * @param packet the packet the user receives
	 */
	public void receive(IUserPacket packet);
}
