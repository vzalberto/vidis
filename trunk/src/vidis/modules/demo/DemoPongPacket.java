package vidis.modules.demo;

import vidis.data.AUserPacket;
import vidis.data.annotation.ColorType;
import vidis.data.annotation.ComponentColor;
import vidis.data.annotation.ComponentInfo;

@ComponentInfo(name = "pong")
@ComponentColor(color = ColorType.BLUE)
public class DemoPongPacket extends AUserPacket {
}
