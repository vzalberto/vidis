package data.sim;

import data.mod.IUserLink;
import data.mod.IUserNode;

public interface ISimPacketCon extends IAComponentCon {

	public IUserNode getFrom();
	
	public IUserNode getTo();

	public IUserLink getLink();

}
