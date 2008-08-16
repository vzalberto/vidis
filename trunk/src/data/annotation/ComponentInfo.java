package data.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Die Annotation ComponentInfo sollte vor jedem User-Implementertem Link, jeder Node und jedem Packet stehen
 * 
 * @author Christoph
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ComponentInfo {
	/**
	 * Der Name dieser Komponente
	 */
	String name();
}
