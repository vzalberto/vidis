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
import vidis.util.Const;

public class Reader implements ReaderInt, ContentHandler {

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
		Const.STD_ERR.print("start="+start + ", length=" + length + ", ch=");
		for(int i=start; i<start+length; i++)
			Const.STD_ERR.print(ch[i]);
		Const.STD_ERR.println();
	}

	public void startDocument() throws SAXException {
		Const.STD_ERR.println("BEGIN>>");
	}
	
	public void endDocument() throws SAXException {
		Const.STD_ERR.println("<<EOF");
	}
	public void startElement(String uri, String localName, String name, Attributes atts) throws SAXException {
		Const.STD_ERR.println("start: uri=" + uri + ", localName=" + localName + ", name=" + name);
	}

	public void endElement(String uri, String localName, String name) throws SAXException {
		Const.STD_ERR.println("end: uri=" + uri + ", localName=" + localName + ", name=" + name);
	}

	public void startPrefixMapping(String prefix, String uri) throws SAXException {
		Const.STD_ERR.println("startPrefix: uri=" + uri + ", prefix=" + prefix);
	}
	
	public void endPrefixMapping(String prefix) throws SAXException {
		Const.STD_ERR.println("endPrefix: prefix=" + prefix);
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
