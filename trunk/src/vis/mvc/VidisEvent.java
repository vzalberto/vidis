package vis.mvc;

public class VidisEvent<D extends Object> {

	public static int instances = 0;
	
	public int type;
	public D data;
	
	private VidisEvent() {
		++instances;
	}
	
	public VidisEvent( int type, D data ) {
		this();
		this.data = data;
		this.type = type;
	}
	
	public VidisEvent( int type ) {
		this();
		this.type = type;
	}
}
