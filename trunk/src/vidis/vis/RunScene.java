package vidis.vis;

import java.io.File;
import java.util.Random;

import vidis.sim.framework.components.SimulatorComponentNode;
import vidis.sim.framework.interfaces.ComponentFactoryInt;
import vidis.sim.modules.demo.DemoFactory;
import vidis.sim.simulator.Simulator;
import vidis.vis.container.FrameContainer;
import vidis.vis.objects.Axis;
import vidis.vis.objects.Grid;
import vidis.vis.objects.Link;
import vidis.vis.objects.Node;
import vidis.vis.objects.Packet;

public class RunScene {
	public static void main(String[] args){
		Random r = new Random(System.currentTimeMillis());
		//new GraphicsInfo();
	
		// TODO del/move to some util class
		System.out.println("java.library.path = " + System.getProperty("java.library.path"));
		String libpath = "lib\\jogl\\1.1.1-pre-20080304\\windows-i586\\";
		File f = new File(libpath);
		System.out.println(f.getAbsolutePath() + " " + (f.exists()?" exists":" not found"));
		for (String s : f.list())
			System.out.println(" -> "+s);
		// ------------
		// start the simulator
		Simulator simulator = new Simulator();
		// initialize the module
		ComponentFactoryInt factory = new DemoFactory();
		// tell the simulator to use this module
		simulator.setModule(factory);
		// start the simulator
		simulator.startSimulation();
		
		Scene scene = Scene.getInstance();
		//Grid g = new Grid();
		Axis a = new Axis();
		
		//scene.addObject(g);
		scene.addObject(a);
		
		for (SimulatorComponentNode cnode :factory.getNodes()){
			Node tmp = new Node(cnode);
			tmp.setPosx(r.nextDouble()*10-5);
			tmp.setPosy(r.nextDouble()*10-5);
			scene.addObject(tmp);
		}
		Packet p=new Packet(null);
		p.setPosx(3);
		p.setPosy(3);
		Node _node1 = new Node(null);
		_node1.setPosx(2);
		_node1.setPosy(2);
		Node _node2 = new Node(null);
		_node2.setPosx(7);
		_node2.setPosy(5);
		Node _node3 = new Node(null);
		_node3.setPosx(2);
		_node3.setPosy(6);
		
		Link _l1 = new Link(_node1, _node2);
		Link _l2 = new Link(_node2, _node3);
		Link _l3 = new Link(_node3, _node1);
		
		scene.addObject(_node1);
		scene.addObject(_node2);
		scene.addObject(_node3);
		
		scene.addObject(_l1);
		scene.addObject(_l2);
		scene.addObject(_l3);
		scene.addObject(p);
		
		
		new FrameContainer("Testlauf", scene.getGLCanvas());
	}
}
