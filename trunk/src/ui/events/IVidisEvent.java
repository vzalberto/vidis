package ui.events;

public interface IVidisEvent {
	
	public int getID();
	
	public final int KeyPressedEvent = 0;
	public final int KeyReleasedEvent = 1;
	public final int KeyTypedEvent = 2;
	
	public final int MousePressedEvent = 3;
	public final int MouseReleasedEvent = 4;
	public final int MouseClickedEvent = 5;
	
	public final int Init = 6;
	public final int InitScene = 7;
	public final int InitSimulator = 9;
	public final int InitWindow = 10;
	public final int InitGui = 11;
	public final int InitCamera = 12;

	
	public final int CameraRegister = 20;
	public final int CameraUnregister = 21;
	public final int ObjectRegister = 22;
	public final int ObjectUnregister = 23;
	
	
	public final int ScrollUp = 1001;
	public final int ScrollLeft = 1002;
	public final int ScrollDown = 1003;
	public final int ScrollRight = 1004;
	
	
	
	public final int GuiResizeEvent = 2001;
	public final int GuiMouseEvent = 2002;

	public final int SimulatorPlay = 5001;
	
	
	
}