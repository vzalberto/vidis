package vidis.data.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Die Annotation ComponentColor ist optional und beschreibt welchen
 * grundfarbton ein element haben soll.
 * 
 * @author dominik
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ComponentColor {
	/**
	 * Die farbe dieser Komponente
	 */
	ColorType color();
}
