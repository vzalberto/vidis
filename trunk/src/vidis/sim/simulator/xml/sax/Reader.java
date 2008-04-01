package vidis.sim.simulator.xml.sax;

import java.io.IOException;
import java.io.InputStream;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import vidis.sim.simulator.LoggerData;
import vidis.sim.simulator.xml.ReaderInt;
import vidis.sim.simulator.xml.sax.tree.XMLTreeNode;
import vidis.sim.simulator.xml.sax.tree.XMLTreeNodeDataType;
import vidis.sim.simulator.xml.sax.tree.XMLTreeNodeType;
import vidis.util.Const;

public class Reader implements ReaderInt, ContentHandler {
	@SuppressWarnings("unchecked")
	private XMLTreeNode root = new XMLTreeNode(XMLTreeNodeType.ROOT);
	@SuppressWarnings("unchecked")
	private XMLTreeNode current = root;
	public LoggerData importXML(LoggerData data, InputStream stream) {
		try {
			XMLReader parser = XMLReaderFactory.createXMLReader();
			parser.setContentHandler(this);
			parser.parse(new InputSource(stream));
			System.out.println("Source is well-formed.");
			// all fine, start parsing
			
		} catch (SAXException e) {
			System.out.println("Source is not well-formed.");
		} catch (IOException e) {
			System.out
					.println("Due to an IOException, the parser could not check input source ");
		}
		return null;
	}

	public void characters(char[] ch, int start, int length) throws SAXException {
		/*
		Const.STD_ERR.print("start="+start + ", length=" + length + ", ch=");
		for(int i=start; i<start+length; i++)
			Const.STD_ERR.print(ch[i]);
		Const.STD_ERR.println();*/
		if(getCurrentNode().getType().equals(XMLTreeNodeType.ID)) {
			XMLTreeNode<Long> child = new XMLTreeNode<Long>(XMLTreeNodeType.DATA);
			String tmp = "";
			for(int i=start; i<start+length; i++)
				tmp += (ch[i]);
			try {
				Long data = Long.parseLong(tmp);
				child.setData(XMLTreeNodeDataType.DOUBLE, data);
				getCurrentNode().addChild(child);
				Const.STD_ERR.println("NOTICE: set data double{"+data+"} at " + getCurrentNode());
			} catch (Exception e) {
				Const.STD_ERR.println("WARN: " + e.getMessage() + " at " + getCurrentNode());
			}
		} else if(getCurrentNode().getType().equals(XMLTreeNodeType.TIMING_START)
				|| getCurrentNode().getType().equals(XMLTreeNodeType.TIMING_END)
				|| getCurrentNode().getType().equals(XMLTreeNodeType.TIMING_LENGTH)) {
			XMLTreeNode<Long> child = new XMLTreeNode<Long>(XMLTreeNodeType.DATA);
			String tmp = "";
			for(int i=start; i<start+length; i++)
				tmp += (ch[i]);
			try {
				Long data = Long.parseLong(tmp);
				child.setData(XMLTreeNodeDataType.DOUBLE, data);
				getCurrentNode().addChild(child);
				Const.STD_ERR.println("NOTICE: set data double{"+data+"} at " + getCurrentNode());
			} catch (Exception e) {
				Const.STD_ERR.println("WARN: " + e.getMessage() + " at " + getCurrentNode());
			}
		} else {
			//Const.STD_ERR.println("WARN: xml document is malformed; did not expect data at object '"+getCurrentNode()+"'");
		}
	}

	public void startDocument() throws SAXException {
		Const.STD_ERR.println("BEGIN>>");
	}
	
	public void endDocument() throws SAXException {
		Const.STD_ERR.println("<<EOF");
	}
	@SuppressWarnings("unchecked")
	public void startElement(String uri, String localName, String name, Attributes atts) throws SAXException {
		if(getCurrentNode().getType().equals(XMLTreeNodeType.ROOT)) {
			// wenn in root
			// wenn simulation daherkommt
			String expect = "simulation";
			if(name.equalsIgnoreCase(expect)) {
				// mache neue simulation node
				XMLTreeNode child = new XMLTreeNode(XMLTreeNodeType.SIMULATION);
				getCurrentNode().addChild(child);
				setCurrentNode(child);
			} else {
				Const.STD_ERR.println("CRITICAL: xml document is malformed; expected '"+expect+"', found '"+name+"'");
			}
		} else if(getCurrentNode().getType().equals(XMLTreeNodeType.SIMULATION)) {
			String expect1 = "id";
			String expect2 = "configuration";
			String expect3 = "instances";
			String expect4 = "timenodes";
			if(name.equalsIgnoreCase(expect1)) {
				// ok, got valid id
				XMLTreeNode child = new XMLTreeNode(XMLTreeNodeType.ID);
				getCurrentNode().addChild(child);
				setCurrentNode(child);
			} else if(name.equalsIgnoreCase(expect2)) {
				// ok, got valid id
				XMLTreeNode child = new XMLTreeNode(XMLTreeNodeType.CONFIGURATION);
				getCurrentNode().addChild(child);
				setCurrentNode(child);
			} else if(name.equalsIgnoreCase(expect3)) {
				// ok, got valid id
				XMLTreeNode child = new XMLTreeNode(XMLTreeNodeType.INSTANCES);
				getCurrentNode().addChild(child);
				setCurrentNode(child);
			} else if(name.equalsIgnoreCase(expect4)) {
				// ok, got valid id
				XMLTreeNode child = new XMLTreeNode(XMLTreeNodeType.TIMENODES);
				getCurrentNode().addChild(child);
				setCurrentNode(child);
			} else {
				Const.STD_ERR.println("CRITICAL: xml document is malformed; expected '"+expect1+"', found '"+name+"'");
			}
			//Const.STD_ERR.println("start: uri=" + uri + ", localName=" + localName + ", name=" + name);
			// wenn in simulation
				// hole id, mache neue simulation
		} else if(getCurrentNode().getType().equals(XMLTreeNodeType.CONFIGURATION)) {
			String expect = "timing";
			if(name.equalsIgnoreCase(expect)) {
				// mache neue simulation node
				XMLTreeNode child = new XMLTreeNode(XMLTreeNodeType.TIMING);
				getCurrentNode().addChild(child);
				setCurrentNode(child);
			} else {
				Const.STD_ERR.println("CRITICAL: xml document is malformed; expected '"+expect+"', found '"+name+"'");
			}
		} else if(getCurrentNode().getType().equals(XMLTreeNodeType.TIMING)) {
			String expect = "start";
			String expect2 = "end";
			String expect3 = "length";
			if(name.equalsIgnoreCase(expect)) {
				// mache neue simulation node
				XMLTreeNode child = new XMLTreeNode(XMLTreeNodeType.TIMING_START);
				getCurrentNode().addChild(child);
				setCurrentNode(child);
			} else if(name.equalsIgnoreCase(expect2)) {
				// mache neue simulation node
				XMLTreeNode child = new XMLTreeNode(XMLTreeNodeType.TIMING_END);
				getCurrentNode().addChild(child);
				setCurrentNode(child);
			} else if(name.equalsIgnoreCase(expect3)) {
				// mache neue simulation node
				XMLTreeNode child = new XMLTreeNode(XMLTreeNodeType.TIMING_LENGTH);
				getCurrentNode().addChild(child);
				setCurrentNode(child);
			} else {
				Const.STD_ERR.println("CRITICAL: xml document is malformed; expected '"+expect+"', found '"+name+"'");
			}
		} else if(getCurrentNode().getType().equals(XMLTreeNodeType.TIMENODES)) {
			String expect = "timenode";
			if(name.equalsIgnoreCase(expect)) {
				// mache neue simulation node
				XMLTreeNode child = new XMLTreeNode(XMLTreeNodeType.TIMENODE);
				getCurrentNode().addChild(child);
				setCurrentNode(child);
			} else {
				Const.STD_ERR.println("CRITICAL: xml document is malformed; expected '"+expect+"', found '"+name+"'");
			}
		} else if(getCurrentNode().getType().equals(XMLTreeNodeType.TIMENODE)) {
			String expect1 = "id";
			String expect2 = "events";
			if(name.equalsIgnoreCase(expect1)) {
				// mache neue simulation node
				XMLTreeNode child = new XMLTreeNode(XMLTreeNodeType.ID);
				getCurrentNode().addChild(child);
				setCurrentNode(child);
			} else if(name.equalsIgnoreCase(expect2)) {
				// mache neue simulation node
				XMLTreeNode child = new XMLTreeNode(XMLTreeNodeType.EVENTS);
				getCurrentNode().addChild(child);
				setCurrentNode(child);
			} else {
				Const.STD_ERR.println("CRITICAL: xml document is malformed; expected '"+expect1+"', found '"+name+"'");
			}
		} else if(getCurrentNode().getType().equals(XMLTreeNodeType.EVENTS)) {
			String expect = "event";
			if(name.equalsIgnoreCase(expect)) {
				// mache neue simulation node
				XMLTreeNode child = new XMLTreeNode(XMLTreeNodeType.EVENT);
				getCurrentNode().addChild(child);
				setCurrentNode(child);
			} else {
				Const.STD_ERR.println("CRITICAL: xml document is malformed; expected '"+expect+"', found '"+name+"'");
			}
		} else if(getCurrentNode().getType().equals(XMLTreeNodeType.INSTANCES)) {
			String expect = "instance";
			if(name.equalsIgnoreCase(expect)) {
				// mache neue simulation node
				XMLTreeNode child = new XMLTreeNode(XMLTreeNodeType.INSTANCE);
				getCurrentNode().addChild(child);
				setCurrentNode(child);
			} else {
				Const.STD_ERR.println("CRITICAL: xml document is malformed; expected '"+expect+"', found '"+name+"'");
			}
		} else if(getCurrentNode().getType().equals(XMLTreeNodeType.INSTANCE)) {
			String expect1 = "packet";
			String expect2 = "link";
			String expect3 = "node";
			if(name.equalsIgnoreCase(expect1)) {
				// mache neue simulation node
				XMLTreeNode child = new XMLTreeNode(XMLTreeNodeType.PACKET);
				getCurrentNode().addChild(child);
				setCurrentNode(child);
			} else if(name.equalsIgnoreCase(expect2)) {
				// mache neue simulation node
				XMLTreeNode child = new XMLTreeNode(XMLTreeNodeType.LINK);
				getCurrentNode().addChild(child);
				setCurrentNode(child);
			} else if(name.equalsIgnoreCase(expect3)) {
				// mache neue simulation node
				XMLTreeNode child = new XMLTreeNode(XMLTreeNodeType.NODE);
				getCurrentNode().addChild(child);
				setCurrentNode(child);
			} else {
				Const.STD_ERR.println("CRITICAL: xml document is malformed; not expected but found '"+name+"'");
			}
		} else {
			Const.STD_ERR.println("WARN: not implemented <"+name+"> at node " + getCurrentNode());
		}
				// wenn in instances
					// wenn in instance
						// wenn link
							// hole id
							// generiere objekt
						// wenn node
							// hole id
							// generiere objekt
						// wenn packet
							// hole id
							// generiere objekt
				// wenn timenodes
					// wenn timenode
						// hole index (zeitpunkt)
						// wenn events
							// wenn event
								// wenn join
								// wenn connect
								// wenn disconnect
								// wenn send
								// wenn receive
	}
	
	public void endElement(String uri, String localName, String name) throws SAXException {
		//Const.STD_ERR.println("end: uri=" + uri + ", localName=" + localName + ", name=" + name);
		if(getCurrentNode().getType().equals(XMLTreeNodeType.ROOT)) {
			Const.STD_ERR.println("ERROR: already reached EOF; did not expect </"+name+">");
		} else if(getCurrentNode().getType().equals(XMLTreeNodeType.SIMULATION)) {
			String expect = "simulation";
			if(name.equalsIgnoreCase(expect)) {
				setCurrentNode(getCurrentNode().getParent());
			} else {
				Const.STD_ERR.println("ERROR: expected </"+expect+"> but got </" + name + ">");
			}
		} else if(getCurrentNode().getType().equals(XMLTreeNodeType.ID)) {
			String expect = "id";
			if(name.equalsIgnoreCase(expect)) {
				setCurrentNode(getCurrentNode().getParent());
			} else {
				Const.STD_ERR.println("ERROR: expected </"+expect+"> but got </" + name + ">");
			}
		} else if(getCurrentNode().getType().equals(XMLTreeNodeType.CONFIGURATION)) {
			String expect = "configuration";
			if(name.equalsIgnoreCase(expect)) {
				setCurrentNode(getCurrentNode().getParent());
			} else {
				Const.STD_ERR.println("ERROR: expected </"+expect+"> but got </" + name + ">");
			}
		} else if(getCurrentNode().getType().equals(XMLTreeNodeType.TIMING)) {
			String expect = "timing";
			if(name.equalsIgnoreCase(expect)) {
				setCurrentNode(getCurrentNode().getParent());
			} else {
				Const.STD_ERR.println("ERROR: expected </"+expect+"> but got </" + name + ">");
			}
		} else if(getCurrentNode().getType().equals(XMLTreeNodeType.TIMING_START)
				|| getCurrentNode().getType().equals(XMLTreeNodeType.TIMING_END)
				|| getCurrentNode().getType().equals(XMLTreeNodeType.TIMING_LENGTH)) {
			String expect1 = "start";
			String expect2 = "end";
			String expect3 = "length";
			if(name.equalsIgnoreCase(expect1)) {
				setCurrentNode(getCurrentNode().getParent());
			} else if(name.equalsIgnoreCase(expect2)) {
				setCurrentNode(getCurrentNode().getParent());
			} else if(name.equalsIgnoreCase(expect3)) {
				setCurrentNode(getCurrentNode().getParent());
			} else {
				Const.STD_ERR.println("ERROR: expected </"+expect1+"> but got </" + name + ">");
			}
		} else if(getCurrentNode().getType().equals(XMLTreeNodeType.INSTANCES)) {
			String expect = "instances";
			if(name.equalsIgnoreCase(expect)) {
				setCurrentNode(getCurrentNode().getParent());
			} else {
				Const.STD_ERR.println("ERROR: expected </"+expect+"> but got </" + name + ">");
			}
		} else if(getCurrentNode().getType().equals(XMLTreeNodeType.TIMENODES)) {
			String expect = "timenodes";
			if(name.equalsIgnoreCase(expect)) {
				setCurrentNode(getCurrentNode().getParent());
			} else {
				Const.STD_ERR.println("ERROR: expected </"+expect+"> but got </" + name + ">");
			}
		} else if(getCurrentNode().getType().equals(XMLTreeNodeType.TIMENODE)) {
			String expect = "timenode";
			if(name.equalsIgnoreCase(expect)) {
				setCurrentNode(getCurrentNode().getParent());
			} else {
				Const.STD_ERR.println("ERROR: expected </"+expect+"> but got </" + name + ">");
			}
		} else if(getCurrentNode().getType().equals(XMLTreeNodeType.EVENTS)) {
			String expect = "events";
			if(name.equalsIgnoreCase(expect)) {
				setCurrentNode(getCurrentNode().getParent());
			} else {
				Const.STD_ERR.println("ERROR: expected </"+expect+"> but got </" + name + ">");
			}
		} else if(getCurrentNode().getType().equals(XMLTreeNodeType.EVENT)) {
			String expect = "event";
			if(name.equalsIgnoreCase(expect)) {
				setCurrentNode(getCurrentNode().getParent());
			} else {
				Const.STD_ERR.println("ERROR: expected </"+expect+"> but got </" + name + ">");
			}
		}  else if(getCurrentNode().getType().equals(XMLTreeNodeType.INSTANCE)) {
			String expect = "instance";
			if(name.equalsIgnoreCase(expect)) {
				setCurrentNode(getCurrentNode().getParent());
			} else {
				Const.STD_ERR.println("ERROR: expected </"+expect+"> but got </" + name + ">");
			}
		} else if(getCurrentNode().getType().equals(XMLTreeNodeType.PACKET)
				|| getCurrentNode().getType().equals(XMLTreeNodeType.LINK)
				|| getCurrentNode().getType().equals(XMLTreeNodeType.NODE)) {
			String expect1 = "packet";
			String expect2 = "link";
			String expect3 = "node";
			if(name.equalsIgnoreCase(expect1)) {
				setCurrentNode(getCurrentNode().getParent());
			} else if(name.equalsIgnoreCase(expect2)) {
				setCurrentNode(getCurrentNode().getParent());
			} else if(name.equalsIgnoreCase(expect3)) {
				setCurrentNode(getCurrentNode().getParent());
			} else {
				Const.STD_ERR.println("CRITICAL: xml document is malformed; expected found '"+name+"'");
			}
		} else {
			Const.STD_ERR.println("WARN: not implemented </"+name+"> at node " + getCurrentNode());
		}
	}

	@SuppressWarnings("unchecked")
	public void setCurrentNode(XMLTreeNode child) {
		this.current = child;
	}

	@SuppressWarnings("unchecked")
	public XMLTreeNode getRootNode() {
		return root;
	}
	@SuppressWarnings("unchecked")
	private XMLTreeNode getCurrentNode() {
		return current;
	}

	public void startPrefixMapping(String prefix, String uri) throws SAXException {
		//Const.STD_ERR.println("startPrefix: uri=" + uri + ", prefix=" + prefix);
	}
	
	public void endPrefixMapping(String prefix) throws SAXException {
		//Const.STD_ERR.println("endPrefix: prefix=" + prefix);
	}

	public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub
	}

	public void processingInstruction(String target, String data) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	public void setDocumentLocator(Locator locator) {
		// TODO Auto-generated method stub
		
	}

	public void skippedEntity(String name) throws SAXException {
		// TODO Auto-generated method stub
		
	}

}
