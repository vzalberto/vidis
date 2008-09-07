package vidis.sim.xml.modules.dataStructure;

public class DocumentDataConnection {

	public static DocumentDataConnection getInstance() {
		return new DocumentDataConnection();
	}

	private DocumentDataNode a;
	private DocumentDataNode b;
	private DocumentDataLink link;

	public void setNodeA(DocumentDataNode from) {
		this.a = from;
	}

	public void setNodeB(DocumentDataNode to) {
		this.b = to;
	}

	public void setLink(DocumentDataLink link) {
		this.link = link;
	}

	public DocumentDataNode getNodeA() {
		return a;
	}
	
	public DocumentDataNode getNodeB() {
		return b;
	}
	
	public DocumentDataLink getLink() {
		return link;
	}

}
