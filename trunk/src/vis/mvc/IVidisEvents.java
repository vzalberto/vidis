package vis.mvc;

public interface IVidisEvents {
	
	public int getId();

	
	public static final int Init		= 0;
	
	public static final int InitWindow			= 1;
	public static final int InitScene			= 2;
	public static final int InitSimulation		= 3;

	public static final int SwitchToFullscreen	= 10;
	public static final int SwitchToWindow		= 11;

	
	// ...
	
}
