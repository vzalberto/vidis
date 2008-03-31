package vidis.sim.modules.roundRobin;

import vidis.sim.framework.annotations.ComponentInfo;
import vidis.sim.framework.annotations.FieldInfo;
import vidis.sim.framework.components.SimulatorComponentNode;
import vidis.sim.framework.components.SimulatorComponentPacket;
import vidis.sim.framework.interfaces.ComponentFactoryInt;

/**
 * demo packet implementation
 * @author dominik
 *
 */
@ComponentInfo(
		description="This Demo Packet implements nothing, but shows the use of annotations",
		defaultcolor="GREEN",
		color_red=0f,
		color_green=1f,
		color_blue=0f
)
public class RoundRobinPacket_default extends SimulatorComponentPacket {

	public RoundRobinPacket_default(ComponentFactoryInt factory, SimulatorComponentNode from, SimulatorComponentNode to, long time) {
		super(factory, from, to, time);
	}
	
	@FieldInfo(
			name = "Name",
			visible = true
	)
	public String name = "DemoPacket";

	public boolean execute() {
		// simply return true, successfully executed
		return true;
	}
	
	public String toString() {
		//return name + super.toString() + "; From: " + this.getFrom() + "; To: " + this.getTo() + "; SentAt: " + this.getTime();
		return this.name + "(" + super.toString() + ")";
	}

}
