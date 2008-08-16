package vis.model;

import javax.vecmath.Point2d;

public class VidisMouseEvent implements IEvent {

	public Point2d where;
	
	
	public int getID() {
		return IEvent.MouseEvent;
	}

	@Override
	public String toString() {
		return "VidisMouseEvent @ "+where;
	}
}
