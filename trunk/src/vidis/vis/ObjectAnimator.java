package vidis.vis;

import vidis.vis.objects.interfaces.Animated;
import vidis.vis.objects.interfaces.Renderable;

public class ObjectAnimator {
	private Scene scene;
	private boolean running = true;
	private long sleeptime = 50;
	public ObjectAnimator(Scene scene) {
		this.scene = scene;
		new Thread(){
			@Override
			public void run() {
				while (running) {
					try {
						Thread.sleep(sleeptime);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					animate();
				}
			}
		}.start();
	}
	public void animate() {
		for (Renderable r : this.scene.getObjects()) {
			if (r instanceof Animated)
				((Animated)r).animate();
		}
	}
}
