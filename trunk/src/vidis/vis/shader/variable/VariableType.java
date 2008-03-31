package vidis.vis.shader.variable;

public enum VariableType {
	UNIFORM("uniform"),
	ATTRIBUTE("attribute");
	private String type;
	private VariableType(String type) {
		this.type = type;
	}
	public String getType() {
		return this.type;
	}
}
