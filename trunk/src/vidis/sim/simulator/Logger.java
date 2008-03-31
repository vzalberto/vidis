package vidis.sim.simulator;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import vidis.sim.framework.interfaces.SimulatorComponentInt;
import vidis.sim.framework.observe.SimulatorEventInt;
import vidis.sim.framework.observe.SimulatorEventListenerInt;

/**
 * a really, really simple logger (should be enough for the moment)
 * @author dominik
 *
 */
public class Logger implements SimulatorEventListenerInt {
	/**
	 * store all events that occurr here
	 */
	private HashMap<Long, LinkedList<SimulatorEventInt>> events = new HashMap<Long, LinkedList<SimulatorEventInt>>();
	
	/**
	 * keep track of the simulator component instances
	 */
	private HashMap<Integer, SimulatorComponentInt> componentInstances = new HashMap<Integer, SimulatorComponentInt>();
	
	public void reset() {
		events.clear();
	}
	
	public String toString() {
		StringBuffer buff = new StringBuffer();
		for(long i=0; i<=Collections.max(events.keySet()); i++) {
			buff.append("T="+i + "{");
				if(events.containsKey(i)) {
					for(int j=0; j<events.get(i).size(); j++) {
						buff.append("\n\t-" + events.get(i).get(j));
					}
					if(events.get(i).size() > 0) {
						buff.append("\n");
					}
				} else {
					buff.append("<>");
				}
			buff.append("}\n");
		}
		return buff.toString();
	}
	
	private void registerComponentInstance(SimulatorComponentInt instance) {
		if(componentInstances.containsKey(instance.getCID())) {
			// already registered
		} else {
			// add it
			componentInstances.put(instance.getCID(), instance);
		}
	}
	
	private void registerComponentInstances(List<SimulatorComponentInt> instances) {
		for(int i=0; i<instances.size(); i++) {
			registerComponentInstance(instances.get(i));
		}
	}
	
	public void beInformed(SimulatorEventInt event) {
		if(!events.containsKey(event.getTime())) {
			// if not exists, create new node
			events.put(event.getTime(), new LinkedList<SimulatorEventInt>());
		}
		// get all involved components and add new ones
		this.registerComponentInstances(event.getInvolvedComponents());
		// add event
		events.get(event.getTime()).add(event);
	}
	
	/**
	 * export everything to somewhere
	 * @param stream the stream to export to
	 * @throws IOException 
	 */
	public void exportXML(OutputStream stream) throws IOException {
		int indent=0;
		PrintStream out = new PrintStream(stream);
		// start outputting
		indent(out, indent++, "<simulation>");
		{
			indent(out, indent, "<id>"+System.currentTimeMillis()+"</id>");
			indent(out, indent++, "<configuration>");
			{
				indent(out, indent++, "<instancing>");
				{
					indent(out, indent, "<objectcount>"+(componentInstances.size())+"</objectcount>");
				}
				indent(out, --indent, "</instancing>");
				indent(out, indent++, "<timing>");
				{
					indent(out, indent, "<start>"+(Collections.min(events.keySet()))+"</start>");
					indent(out, indent, "<end>"+(Collections.max(events.keySet()))+"</end>");
					indent(out, indent, "<length>"+(Collections.max(events.keySet())-Collections.min(events.keySet()))+"</length>");
				}
				indent(out, --indent, "</timing>");
			}
			indent(out, --indent, "</configuration>");
			// start printing references objects
			indent(out, indent++, "<instances>");
			{
				for(Integer key : componentInstances.keySet()) {
					indent(out, indent++, "<instance>");
						indent(out, indent, componentInstances.get(key).toXML_byREF());
					indent(out, --indent, "</instance>");
				}
			}
			indent(out, --indent, "</instances>");
			// start printing time nodes
			indent(out, indent++, "<timenodes>");
			{
				for(long i=Collections.min(events.keySet()); i<=Collections.max(events.keySet()); i++) {
					indent(out, indent++, "<timenode>");
					{
						indent(out, indent, "<index>"+i+"</index>");
						if(events.containsKey(i)) {
							// output all events
							if(events.get(i).size() > 0) {
								indent(out, indent++, "<events>");
								{
									for(int j=0; j<events.get(i).size(); j++) {
										indent(out, indent++, "<event>");
											indent(out, indent, events.get(i).get(j).toXML());
										indent(out, --indent, "</event>");
									}
								}
								indent(out, --indent, "</events>");
							}
						}
						indent(out, --indent, "</timenode>");
					}
				}
			}
			indent(out, --indent, "</timenodes>");
		}
		indent(out, --indent, "</simulation>");
		// eof start outputting
		out.flush();
	}
	private void indent(PrintStream out, int indent, String message){
		for (int i =0; i<indent; i++){
			out.print("\t");
		}
		out.println(message);
	}
	
	/**
	 * import everything from somewhere
	 * @param stream the stream to export from
	 */
	public void importXML(InputStream stream) throws IOException {
		Scanner in = new Scanner(stream);
		while(in.hasNextLine()) {
			in.nextLine();
		}
	}
}
