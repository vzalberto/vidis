package modules.demo;

import data.AUserPacket;
import data.annotation.ColorType;
import data.annotation.ComponentColor;
import data.annotation.ComponentInfo;

@ComponentInfo(name = "ping")
@ComponentColor(color = ColorType.RED)
public class DemoPingPacket extends AUserPacket {
}
