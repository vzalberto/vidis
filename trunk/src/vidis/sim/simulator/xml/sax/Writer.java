package vidis.sim.simulator.xml.sax;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Collections;

import vidis.sim.simulator.LoggerData;
import vidis.sim.simulator.xml.WriterInt;

public class Writer implements WriterInt {
	
	private void indent(PrintStream out, int indent, String message){
		for (int i =0; i<indent; i++){
			out.print("\t");
		}
		out.println(message);
	}
	
	public void exportXML(LoggerData data, OutputStream stream) throws IOException {
		int indent=0;
		PrintStream out = new PrintStream(stream);
		// start outputting
		indent(out, indent++, "<simulation>");
		{
			indent(out, indent, "<id>"+data.getId()+"</id>");
			indent(out, indent++, "<configuration>");
			{
				indent(out, indent++, "<instancing>");
				{
					indent(out, indent, "<objectcount>"+(data.getObjectCount())+"</objectcount>");
				}
				indent(out, --indent, "</instancing>");
				indent(out, indent++, "<timing>");
				{
					indent(out, indent, "<start>"+(data.getTimeStart())+"</start>");
					indent(out, indent, "<end>"+(data.getTimeEnd())+"</end>");
					indent(out, indent, "<length>"+(data.getTimeLength())+"</length>");
				}
				indent(out, --indent, "</timing>");
			}
			indent(out, --indent, "</configuration>");
			// start printing references objects
			indent(out, indent++, "<instances>");
			{
				for(Integer key : data.getInstances().keySet()) {
					indent(out, indent++, "<instance>");
						indent(out, indent, data.getInstances().get(key).toXML_byREF());
					indent(out, --indent, "</instance>");
				}
			}
			indent(out, --indent, "</instances>");
			// start printing time nodes
			indent(out, indent++, "<timenodes>");
			{
				for(long i=Collections.min(data.getEvents().keySet()); i<=Collections.max(data.getEvents().keySet()); i++) {
					indent(out, indent++, "<timenode>");
					{
						indent(out, indent, "<id>"+i+"</id>");
						if(data.getEvents().containsKey(i)) {
							// output all events
							if(data.getEvents().get(i).size() > 0) {
								indent(out, indent++, "<events>");
								{
									for(int j=0; j<data.getEvents().get(i).size(); j++) {
										indent(out, indent++, "<event>");
											indent(out, indent, data.getEvents().get(i).get(j).toXML());
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
}
