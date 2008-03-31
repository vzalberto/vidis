package vidis.sim.simulator.xml;

import java.io.InputStream;

import vidis.sim.simulator.LoggerData;

public interface ReaderInt {
	
	/**
	 * this function imports a input stream to its component instances and events
	 * @param data 
	 * @param stream the input stream
	 * @return the logger data object
	 */
	public LoggerData importXML(LoggerData data, InputStream stream);
}
