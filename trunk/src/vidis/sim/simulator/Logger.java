package vidis.sim.simulator;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;

import vidis.sim.framework.observe.SimulatorEventInt;
import vidis.sim.framework.observe.SimulatorEventListenerInt;
import vidis.sim.simulator.xml.ReaderInt;
import vidis.sim.simulator.xml.WriterInt;
import vidis.sim.simulator.xml.sax.Reader;
import vidis.sim.simulator.xml.sax.Writer;

/**
 * a really, really simple logger (should be enough for the moment)
 * @author dominik
 *
 */
public class Logger implements SimulatorEventListenerInt {
	/**
	 * internal xml reader
	 */
	private ReaderInt xmlReader = new Reader();
	
	/**
	 * internal xml writer
	 */
	private WriterInt xmlWriter = new Writer();
	
	/**
	 * logger data
	 */
	private LoggerData data =  new LoggerData();
	
	public void reset() {
		data.clear();
	}
	
	public String toString() {
		StringBuffer buff = new StringBuffer();
		for(long i=Collections.min(data.getEvents().keySet()); i<=Collections.max(data.getEvents().keySet()); i++) {
			buff.append("T="+i + "{");
				if(data.getEvents().containsKey(i)) {
					for(int j=0; j<data.getEvents().get(i).size(); j++) {
						buff.append("\n\t-" + data.getEvents().get(i).get(j));
					}
					if(data.getEvents().get(i).size() > 0) {
						buff.append("\n");
					}
				} else {
					buff.append("<>");
				}
			buff.append("}\n");
		}
		return buff.toString();
	}
	
	public void beInformed(SimulatorEventInt event) {
		data.registerEvent(event);
	}
	
	/**
	 * export everything to somewhere
	 * @param stream the stream to export to
	 * @throws IOException 
	 */
	public void exportXML(OutputStream stream) {
		try {
			xmlWriter.exportXML(data, stream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * import everything from somewhere; uses the SAX parser to analyze the document
	 * @param stream the stream to export from
	 */
	public void importXML(InputStream stream) throws IOException {
		// clear data
		data.clear();
		// call importer
		xmlReader.importXML(data, stream);
	}
}
