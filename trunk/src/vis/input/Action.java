package vis.input;
/**
 * Actions that can be triggered by the keyboard
 * ( ? Mouse Actions ? )
 * @author Christoph
 *
 */
public enum Action {
	SCROLL_LEFT,
	SCROLL_RIGHT,
	SCROLL_UP,
	SCROLL_DOWN,
	
	ZOOM_IN,
	ZOOM_OUT,
	
	SELECTION_CHANGED,
	
	MOUSE_SCROLL;
	
	private Object anhang = null;
	private long TTL = -1; // -1 forever
	
	public void setTTL(long ms){
		this.TTL = System.currentTimeMillis() + ms;
	}
	public long getDieTime(){
		return TTL;
	}
	public Object getAnhang() {
		return anhang;
	}

	public void setAnhang(Object anhang) {
		this.anhang = anhang;
	}
	
	
}
