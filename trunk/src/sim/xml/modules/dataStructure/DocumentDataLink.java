package sim.xml.modules.dataStructure;

import java.util.HashMap;

public class DocumentDataLink {
	private String id;
	private String classpath;
	private HashMap<String, String> variables;
	private long delay;
	private DocumentDataLink() {
		variables = new HashMap<String, String>();
	}
	public static DocumentDataLink getInstance() {
		return new DocumentDataLink();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getClasspath() {
		return classpath;
	}
	public void setClasspath(String classpath) {
		this.classpath = classpath;
	}
	public void addVariable(String varName, String varValue) {
		getVariables().put(varName, varValue);
	}
	public HashMap<String, String> getVariables() {
		return variables;
	}
	public void setDelay(long delay) {
		this.delay = delay;
	}
	public long getDelay() {
		return delay;
	}
}