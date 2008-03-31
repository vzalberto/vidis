package vidis.vis.container;

import java.awt.BorderLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.media.opengl.GLCanvas;
import javax.swing.JFrame;

public class FrameContainer extends JFrame {
	
	public FrameContainer(String title, GLCanvas glcanvas){
		super(title);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 200, 200);
		getContentPane().add(glcanvas, BorderLayout.CENTER);
		setVisible(true);	
	}
	
	public void fullscreen(){
		 GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		 gd.getAvailableAcceleratedMemory();
		
	}

}
