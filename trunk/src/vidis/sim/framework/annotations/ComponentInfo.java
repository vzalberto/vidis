package vidis.sim.framework.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ComponentInfo {
	/**
	 * The Description for a node
	 */
	String description();
	/**
	 * The default color value for a module-implemented node
	 * this value can be overwritten by the module <text>file
	 */
	String defaultcolor(); // TODO: create an enum of possible colors

	float color_red();
	float color_green();
	float color_blue();
}
