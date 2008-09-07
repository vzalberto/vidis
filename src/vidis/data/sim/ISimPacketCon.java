package vidis.data.sim;

import vidis.data.mod.IUserLink;
import vidis.data.mod.IUserNode;

public interface ISimPacketCon extends IAComponentCon {

	public IUserNode getFrom();
	
	public IUserNode getTo();

	public IUserLink getLink();

}
