package modules.vectorClockAlgorithm;

import data.AUserPacket;
import data.annotation.ColorType;
import data.annotation.ComponentColor;

@ComponentColor(color = ColorType.LIGHT_GREY)
public class VectorClockAlgorithmPacket extends AUserPacket {
    private VectorTime time;
    public VectorClockAlgorithmPacket(VectorTime time) {
	this.time = time;
    }
    public VectorTime getTime() {
	return time;
    }
    public String getName() {
	return getTime().toString();
    }

    public String toString() {
	return getName();
    }
}