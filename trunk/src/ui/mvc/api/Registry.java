package ui.mvc.api;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class Registry {
	private static Logger logger = Logger.getLogger(Registry.class);

	private static Map<String, Object> items = new HashMap<String, Object>();
	
	public static void register( String id, Object value) {
		items.put(id, value);
	}
	
	public static void unregister( String id ) {
		items.remove(id);
	}
	
	public Object get( String id ) {
		return items.get(id);
	}
}
