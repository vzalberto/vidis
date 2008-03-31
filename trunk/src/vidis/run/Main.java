package vidis.run;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import vidis.sim.framework.interfaces.ComponentFactoryInt;
import vidis.sim.modules.demo.DemoFactory;
import vidis.sim.modules.roundRobin.RoundRobinDiscovery;
import vidis.sim.simulator.Simulator;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// start the simulator
		Simulator simulator = new Simulator();
		// initialize the module
		ComponentFactoryInt factory = new DemoFactory();
		// tell the simulator to use this module
		simulator.setModule(factory);
		
		// test import of xml data
		try {
			simulator.importSimFile(new File("./data/demo.sim"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// eof test import of xml data
		System.exit(0);
		
		// start the simulator
		System.err.println("before simulation 1");
		simulator.startSimulation();
		System.err.println("after simulation 1");
		while(simulator.isSimulating()) {
			System.err.println("waiting simulation 1");
			try {
				//simulator.waitFor();
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			simulator.exportSimFile(new File("./data/demo.sim"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.err.println("5 seconds timeout between simulations");
		try {
			Thread.sleep(5000);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		// init another module
		ComponentFactoryInt roundRobin = new RoundRobinDiscovery(3);
		simulator.setModule(roundRobin);
		System.err.println("before simulation 2");
		simulator.startSimulation();
		System.err.println("after simulation 2");
		while(simulator.isSimulating()) {
			System.err.println("waiting simulation 2");
			try {
				//simulator.waitFor();
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			simulator.exportSimFile(new File("./data/roundRobin.sim"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
