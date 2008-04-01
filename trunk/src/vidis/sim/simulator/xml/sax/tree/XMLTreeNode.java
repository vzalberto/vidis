package vidis.sim.simulator.xml.sax.tree;

import java.util.ArrayList;

public class XMLTreeNode<T extends Object> {
	private XMLTreeNodeType type;
	private XMLTreeNodeDataType dataType;
	private T data;
	@SuppressWarnings({ "unused", "unchecked" })
	private XMLTreeNode parent = null;
	private ArrayList<XMLTreeNode<T>> childs;
	
	private XMLTreeNode() {
		childs = new ArrayList<XMLTreeNode<T>>();
	}
	public XMLTreeNode(XMLTreeNodeType type) {
		this();
		this.setType(type);
	}
	public void setType(XMLTreeNodeType type) {
		this.type = type;
	}
	public XMLTreeNodeType getType() {
		return this.type;
	}
	@SuppressWarnings("unchecked")
	public void addChild(XMLTreeNode child) {
		child.setParent(this);
		this.childs.add(child);
	}
	public String toString() {
		return "Node{" + this.getType() + "}";
	}
	public void setData(XMLTreeNodeDataType type, T data) {
		this.dataType = type;
		this.data = data;
	}
	public XMLTreeNodeDataType getDataType() {
		return this.dataType;
	}
	public T getData() {
		return data;
	}
	@SuppressWarnings("unchecked")
	public void setParent(XMLTreeNode parent) {
		this.parent = parent;
	}
	@SuppressWarnings("unchecked")
	public XMLTreeNode getParent() {
		return this.parent;
	}
}
