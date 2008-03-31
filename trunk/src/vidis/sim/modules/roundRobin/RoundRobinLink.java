package vidis.sim.modules.roundRobin;

import vidis.sim.framework.annotations.ComponentInfo;
import vidis.sim.framework.annotations.FieldInfo;
import vidis.sim.framework.components.SimulatorComponentLink;
import vidis.sim.framework.interfaces.ComponentFactoryInt;

/**
 * demo link class
 * @author dominik
 *
 */
@ComponentInfo(
		description="This Demo Link implements nothing, but shows the use of annotations",
		defaultcolor="BLUE",
		color_red=0f,
		color_green=0f,
		color_blue=1f
)
public class RoundRobinLink extends SimulatorComponentLink {
	public RoundRobinLink(ComponentFactoryInt factory, long delay) {
		super(factory, delay);
	}

	@FieldInfo(
			name = "Name",
			visible = true
	)
	public String name = "DemoLink";
	
	/**
	 * not really needed (but we keep this here to show you what
	 * could be done)
	 * @return true or false
	 */
	public boolean execute() {
		return super.execute();
		// possible further operations down here
	}
	
	public String toString() {
		//return name + super.toString() + "; Delay: " + this.getDelay();
		return name + "(" + super.toString() + ")";
	}
}
