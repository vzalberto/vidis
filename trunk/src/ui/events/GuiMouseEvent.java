package ui.events;

import javax.vecmath.Point2d;

import org.apache.log4j.Logger;

import vis.model.IEvent;

public class GuiMouseEvent implements IVidisEvent {
	private static Logger logger = Logger.getLogger(GuiMouseEvent.class);

	public Point2d where;
	
	
	public int getID() {
		return IEvent.MouseEvent;
	}

	@Override
	public String toString() {
		return "VidisMouseEvent @ "+where;
	}

}
