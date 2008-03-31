package vidis.vis.gui.input;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Dies Klasse verbindet eine Taste mit einer oder mehreren Aktionen
 * @author Christoph
 *
 */
public class KeyMapping {
	private HashMap<Key, List<Action>> mapping = new HashMap<Key, List<Action>>();
	public KeyMapping(){
		defaultMapping();
	}
	
	public void addMapping(Key key, Action action){
		if (mapping.containsKey(key)){
			if (mapping.get(key).contains(action)){
				// already in list
			}
			else {
				mapping.get(key).add(action);
			}
		}
		else {
			List<Action> tmp = new LinkedList<Action>();
			tmp.add(action);
			mapping.put(key, tmp);
		}
	}
	public void removeMapping(Key key, Action action){
		if (mapping.containsKey(key)){
			if (mapping.get(key).contains(action)){
				mapping.get(key).remove(action);
				if (mapping.get(key).size()==0){
					mapping.remove(key);
				}
			}
		}
	}
	
	
	
	public void saveMappingToFile(File f){
		// TODO saveMappingToFile
	}
	public void loadMappingFromFile(File f){
		// TODO loadMappingFromFile
	}
	public void defaultMapping(){
		// default mappings for scrolling
		addMapping(Key.KEY_W, Action.SCROLL_UP);
		addMapping(Key.KEY_A, Action.SCROLL_LEFT);
		addMapping(Key.KEY_S, Action.SCROLL_DOWN);
		addMapping(Key.KEY_D, Action.SCROLL_RIGHT);
		// default mappings for zoom
		addMapping(Key.KEY_PAGE_UP, Action.ZOOM_IN);
		addMapping(Key.KEY_PAGE_DOWN, Action.ZOOM_OUT); 
	}
	
	public List<Action> getActions(Key k){
		return mapping.get(k);
	}
}
