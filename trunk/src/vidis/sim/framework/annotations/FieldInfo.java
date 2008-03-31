package vidis.sim.framework.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FieldInfo {
	/**
	 * The name of the value
	 * This name is shown in the Visualization
	 */
	String name();
	/**
	 * if enabled this value is shown in the visualization
	 */
	boolean visible();
	
}
