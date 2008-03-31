package vidis.sim.simulator.xml;

import java.io.IOException;
import java.io.OutputStream;

import vidis.sim.simulator.LoggerData;

/**
 * this interface must be implemented by all XML writers
 * @author dominik
 *
 */
public interface WriterInt {
	/**
	 * function that exports a data structure to xml
	 * @param data
	 * @param stream
	 * @throws IOException
	 */
	public void exportXML(LoggerData data, OutputStream stream) throws IOException;
}
