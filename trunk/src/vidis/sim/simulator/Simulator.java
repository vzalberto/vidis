package vidis.sim.simulator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;

import vidis.sim.framework.components.SimulatorComponentLink;
import vidis.sim.framework.components.SimulatorComponentNode;
import vidis.sim.framework.components.SimulatorComponentPacket;
import vidis.sim.framework.interfaces.ComponentFactoryInt;
import vidis.sim.framework.interfaces.SimulatorComponentInt;
import vidis.sim.util.Utility;

/**
 * simulator class that holds the simulator module
 * and executes them
 * @author dominik
 *
 */
public class Simulator implements Runnable {
	
	/**
	 * step timeout that is done between each step
	 */
	public static long STEP_TIMEOUT = 0;
	
	/**
	 * moduled factory
	 */
	private ComponentFactoryInt factory;
	
	/**
	 * our nice logger
	 */
	private Logger eventLogger = new Logger();
	
	/**
	 * our worker thread
	 */
	private Thread worker;

	/**
	 * executing variable
	 */
	private boolean executing;
	
	public Simulator() {
		super();
	}
	
	/**
	 * set the module to be used by the simulator
	 * @param factory the modules component factory
	 */
	public void setModule(ComponentFactoryInt factory) {
		this.factory = factory;
	}
	
	public void waitFor() throws InterruptedException {
		this.worker.join();
	}
	
	/**
	 * starts the simulation
	 */
	public void startSimulation() {
		worker = null;
		worker = new Thread(this);
		worker.start();
		try {
			this.waitFor();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void exportSimFile(File simFile) throws FileNotFoundException, IOException {
		this.eventLogger.exportXML(new FileOutputStream(simFile));
	}
	
	public void importSimFile(File simFile) throws FileNotFoundException, IOException {
		this.eventLogger.importXML(new FileInputStream(simFile));
	}

	/**
	 * runnable function, called upon execution time
	 * 
	 * DO NOT EXECUTE THIS ON YOUR OWN! ;-)
	 */
	public void run() {
		executing = true;
		// init and reset logger
		eventLogger.reset();
		factory.register(eventLogger);
		// start initialisation
		factory.init();
		// perform simulator steps
		for(long i=0; i<factory.getSimulationTimelength(); i++) {
			Utility.STD_ERR.println("" + i + "ms {");
			if(factory.getComponents().size() == 0) {
				//System.err.println("\t=> nothing to do");
			} else {
				// execute all components
				for(int j=0; j<factory.getComponents().size(); j++) {
					SimulatorComponentInt component = factory.getComponents().get(j);
					if(component instanceof SimulatorComponentPacket) {
						// Utility.STD_ERR.println(component);
					} else if(component instanceof SimulatorComponentLink) {
						//Utility.STD_ERR.println(component);
					} else if(component instanceof SimulatorComponentNode) {
						//Utility.STD_ERR.println(component);
					}
					//Utility.STD_ERR.print("\t " + component + "\n\t\t");
					if(!component.execute()) {
						// generate error message
						Utility.STD_ERR.print("\t "+component+" => ERR");
					} else {
						// generate ok message 
						//Utility.STD_ERR.print("\n\t => DONE");
					}
					//Utility.STD_ERR.println();
				}
			}
			Utility.STD_ERR.println("}");
			if(STEP_TIMEOUT > 0) {
				try {
					Thread.sleep(STEP_TIMEOUT);
				} catch (InterruptedException e) {
					
				}
			}
			Utility.timeForward();
		}
		// print logger events
		//Utility.STD_ERR.println(eventLogger);
		/*try {
			eventLogger.exportXML(new FileOutputStream(new File("./data/simulation_"+System.currentTimeMillis()+".sim")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		executing = false;
		worker.interrupt();
	}
	
	public boolean isSimulating() {
		return executing;
	}

	public boolean isReady() {
		return !executing;
	}
}
