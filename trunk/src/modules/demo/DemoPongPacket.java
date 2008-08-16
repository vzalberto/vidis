package modules.demo;

import data.AUserPacket;
import data.annotation.ColorType;
import data.annotation.ComponentColor;
import data.annotation.ComponentInfo;

@ComponentInfo(name = "pong")
@ComponentColor(color = ColorType.BLUE)
public class DemoPongPacket extends AUserPacket {
}
