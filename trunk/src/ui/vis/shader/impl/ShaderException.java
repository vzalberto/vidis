package ui.vis.shader.impl;

import java.io.IOException;

public class ShaderException extends Exception {

	public ShaderException(String message) {
		super(message);
	}

	public ShaderException(Exception e) {
		super(e);
	}

}
