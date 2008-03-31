package vidis.sim.modules.vectorClockAlgorithm;

import vidis.sim.framework.annotations.ComponentInfo;
import vidis.sim.framework.annotations.FieldInfo;
import vidis.sim.framework.components.SimulatorComponentLink;
import vidis.sim.framework.components.SimulatorComponentNode;
import vidis.sim.framework.components.SimulatorComponentPacket;
import vidis.sim.framework.interfaces.ComponentFactoryInt;
import vidis.sim.util.Utility;

/**
 * demo node implementation
 * @author dominik
 *
 */
@ComponentInfo(
		description="This Demo Node implements nothing, but shows the use of annotations",
		defaultcolor="RED",
		color_red=1f,
		color_green=0f,
		color_blue=0f
)
public class VectorClockAlgorithmNode extends SimulatorComponentNode {
	
	private int packetsSent = 0;

	public VectorClockAlgorithmNode(ComponentFactoryInt factory) {
		super(factory);
	}

	@FieldInfo(
			name = "Name",
			visible = true
	)
	public String name = "DemoNode";
	
	public boolean execute() {
		// "search" for nodes
		for(int i=0; i<this.getFactory().getComponents().size(); i++) {
			if(this.getFactory().getComponents().get(i) instanceof SimulatorComponentNode) {
				SimulatorComponentNode node = (SimulatorComponentNode)this.getFactory().getComponents().get(i);
				// check if we are ourselve :-)
				if(!node.equals(this)) {
					//if(packetsSent < 1) {
						// send a packet
						if(Utility.getTime() % 10 == 0) {
							this.send(this.getFactory().getNewPacket(this, node, Utility.getTime()));
							packetsSent++;
						}
					//}
				}
			}
		}
		return true;
	}
	
	public void send(SimulatorComponentPacket packet) {
		Utility.STD_ERR.println("SEND " + packet);
		super.send(packet);
	}
	
	@Override
	public void receive(SimulatorComponentLink from, SimulatorComponentPacket packet) {
		Utility.STD_ERR.println("RECEIVE " + packet);
		super.receive(from, packet);
	}
	
	public String toString() {
		return this.name + "(" + super.toString() + ")";
	}
}
