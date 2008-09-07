package vidis.sim.xml.modules.dataStructure;

import java.util.HashMap;

public class DocumentDataNode {
	private String id;
	private String classpath;
	private HashMap<String, String> variables;
	private DocumentDataNode() {
		variables = new HashMap<String, String>();
	}
	public static DocumentDataNode getInstance() {
		return new DocumentDataNode();
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
}