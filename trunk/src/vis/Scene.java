package vis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

import org.apache.log4j.Logger;

import ui.gui.Gui;
import vis.camera.DefaultCamera;
import vis.camera.GuiCamera;
import vis.camera.ICamera;
import vis.input.GuiInputListener;
import vis.model.impl.BasicGuiContainer;
import vis.model.impl.TextGuiContainer;
import vis.model.structure.IGuiContainer;
import vis.model.structure.IVisObject;
import vis.multipass.RenderPass;
import vis.objects.Axis;
import vis.objects.Grid;
import vis.objects.LinkTest;

import com.sun.opengl.util.Animator;
import com.sun.opengl.util.GLUT;


/**
 * Diese Klasse stellt die zu zeichnende Szene dar Sie rendert alle Objekte
 * 
 * @author Christoph
 * 
 */
public class Scene implements GLEventListener {
	
	public static Logger logger = Logger.getLogger( Scene.class );
	
	private long framecount = 0;
	private long starttime;
	private long lasttimecount;
	
	private long start = 0;
	private long finish = 0;
	private long frames = 0;
	private double fpsvalue = 0;
	private double duration = 0;
	
	/**
	 * static field to contain the only instance of the Scene
	 */
	private static Scene scene = null;
	/**
	 * Sigleton getInstance()
	 * @return the only instance
	 */
	public static Scene getInstance() {
		if (scene == null)
			scene = new Scene();
		return scene;
	}
	
	private Gui gui;
	
	/**
	 * OpenGL Canvas
	 */
	private GLCanvas canvas;
	/**
	 * all renderable Objects
	 */
	private List<IVisObject> objects;
	/**
	 * The cameras
	 * the order of them is important because some of 
	 * them delete the background before rendering
	 */
	private List<ICamera> cameras;
	
	private BasicGuiContainer mainGuiContainer = new BasicGuiContainer();

private TextGuiContainer fps;
	/**
	 * private constructor ( use static method getInstance() )
	 */
	private Scene() {
		gui = new Gui();
		cameras = new ArrayList<ICamera>();
		cameras.add(new DefaultCamera());
		cameras.add(new GuiCamera(gui));
		
		objects = Collections.synchronizedList(new ArrayList<IVisObject>());
		objects.add(new Axis());
		objects.add(new Grid());
		objects.add(new LinkTest());
//		BasicGuiContainer container1 = new BasicGuiContainer();
//		container1.setLayout(new BasicMarginLayout(1,-1,-1,1,5,7));
//		
//		BasicGuiContainer container2 = new BasicGuiContainer();
//		container2.setLayout(new BasicMarginLayout(1,1,1,1,-1,-1));
//		
//		container1.addChild(container2);
//		
//		BasicGuiContainer rightPanel = new BasicGuiContainer();
//		rightPanel.setLayout(new PercentMarginLayout(-0.7,1,1,1,-1,-0.30));
//		
//		fps = new TextGuiContainer();
//		fps.setLayout(new PercentMarginLayout(1,1,-0.9,-0.9,-0.1,-0.1));
//		fps.setText("0fps");
//		mainGuiContainer.addChild(fps);
//		mainGuiContainer.addChild(rightPanel);
//		mainGuiContainer.addChild(container1);
//		mainGuiContainer.setOpaque( false );
//		objects.add(mainGuiContainer);
		canvas = new GLCanvas();
		canvas.addGLEventListener(this);
		
		GuiInputListener l = new GuiInputListener(this);
		canvas.addKeyListener(l);
		canvas.addMouseWheelListener(l);
		canvas.addMouseMotionListener(l);
		canvas.addMouseListener(l);
		
		for (ICamera c : cameras)
			l.addActionHandler(c);

		//camera = new DefaultCamera();
//		new ObjectAnimator(this);
	}

	public GLCanvas getGLCanvas() {
		return canvas;
	}

d

	// contain the key pressed
	private boolean[] keys = new boolean[256];

	private float in = -5.0f;

	/***************************************************************************
	 * The displayChanged event *
	 * ******************************************************* Called when the
	 * display mode has been changed : * CURRENTLY UNIMPLEMENTED IN JOGL *
	 **************************************************************************/
	public void displayChanged(GLAutoDrawable glDrawable, boolean modeChanged,
			boolean deviceChanged) {
	} 
//	private Program prog;
	/***************************************************************************
	 * The reshape events *
	 * ***************************************************************************
	 * This method is called by the GLDrawable at each resize event of the *
	 * OpenGl component. * It resize the Viewport of the scene and set the
	 * Viewing Volume * * Gl4java equivalent : public void reshape(int width,
	 * int height) *
	 **************************************************************************/
	public void reshape(GLAutoDrawable glDrawable, int x, int y, int width,
			int height)
	{
		final GL gl = glDrawable.getGL();
		final GLU glu = new GLU();

		
		//this.x = x; this.y = y; this.width = width; this.height = height;
		
		// update the camera targets
		for (ICamera c : cameras)
			c.reshape(x, y, width, height);
		
		
		
	}

	/***************************************************************************
	 * The init method *
	 * ********************************************************** Call by the
	 * GLDrawable just after the Gl-Context is 
	 * initialized. * 
	 * This method is used to :
	 * - activate some modes like texture, light ...
	 * - affect value to properties
	 * - import textures, load your data ... * *
	 * Equivalent in Gl4java : public void init() *
	 **************************************************************************/
	public void init(GLAutoDrawable glDrawable) // Jogl JSR-231
	{

		final GL gl = glDrawable.getGL();
		final GLU glu = new GLU();
		final GLUT glut = new GLUT();

		gl.glDepthRange(0.0, 1.0);
		gl.glEnable(GL.GL_DEPTH_TEST);
		
		gl.glEnable(GL.GL_DOUBLEBUFFER);
		gl.glDisable(GL.GL_LIGHTING);
		//gl.glEnable(GL.GL_LIGHT0);
		gl.glEnable(GL.GL_COLOR_MATERIAL);
		// gl.glEnable(GL.GL_ALPHA);
		gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
		gl.glEnable(GL.GL_BLEND);
		gl.glAlphaFunc(GL.GL_GREATER, 0.1f);
		gl.glEnable(GL.GL_ALPHA_TEST);
		gl.glEnable(GL.GL_CULL_FACE);
//		Node.initShaders(gl);
//		Link.initShaders(gl);
//		
//		//temp init reflect shader TODO global shadermanagement
//		prog = new Program();
//        prog.create(gl);
//       
//        VertexShader exvshad = new VertexShader();
//        try { 
//        	exvshad.create(gl);
//        	exvshad.loadSource("shader\\ReflectionVertexShader.glsl", gl);
//        	exvshad.compile(gl);
//        	prog.addShader(exvshad);
//        } catch (ShaderException e) { e.printStackTrace(); }
//        
//        FragmentShader exfshad = new FragmentShader();
//        try {
//	        exfshad.create(gl);
//	        exfshad.loadSource("shader\\ReflectionFragmentShader.glsl", gl);
//	        exfshad.compile(gl);
//	        prog.addShader(exfshad);
//        } catch (ShaderException e) { e.printStackTrace(); }
//        
//        
//        prog.link(gl);
		// ??? Light position TODO fix
//		FloatBuffer fb = FloatBuffer.allocate(3);
//		fb.put(new float[] { 0, 0, -3 });
//		gl.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, fb);

		//        
		// gl.glMatrixMode(GL.GL_PROJECTION);
		// gl.glLoadIdentity();
		// gl.glTranslatef(0.1f, 0f, 0f);
		// TODO camera setzen


		// Create the animator to call the display method in loop
		animator = new Animator(glDrawable);
		animator.start();
		starttime = System.currentTimeMillis();
	}

	/***************************************************************************
	 * The display event *
	 * ****************************************************************** Here
	 * we place all the code related to the drawing of the scene. * This method
	 * is called by the drawing loop (the display method) * * Gl4java equivalent :
	 * public void drawGlScene() *
	 **************************************************************************/
	public void display(GLAutoDrawable glDrawable)
	{
		finish = System.currentTimeMillis() ;
		duration += (double)(finish - start) / 1000d ;
		frames ++ ;
		fpsvalue = frames / duration ;
		start = System.currentTimeMillis() ;
		//this.fps.setText( String.format("%3.1ffps", fpsvalue));
		
		
		
		
		final GL gl = glDrawable.getGL();
		final GLU glu = new GLU();
		final GLUT glut = new GLUT();

		
		for ( ICamera c : cameras ) {
			// SETUP
			c.init(gl);
			
			// PROJECTION
			gl.glMatrixMode(GL.GL_PROJECTION);
			gl.glLoadIdentity();
			c.applyProjectionMatrix(gl);
			
			// VIEW
			gl.glMatrixMode(GL.GL_MODELVIEW);
			gl.glLoadIdentity();
			c.applyViewMatrix(gl);
			
			// MODEL
			if ( c instanceof GuiCamera) {
				gl.glPushMatrix();
					drawGuiModel(gl);
				gl.glPopMatrix();
			}
			else {
				for ( RenderPass p : RenderPass.values()) {
					p.setup(gl);
					// MODEL
					gl.glPushMatrix();
						drawModel(gl);
					gl.glPopMatrix();
				}
			}
		}
		
	}
	public void drawModel(GL gl)
	{
		final GLU glu = new GLU();
		final GLUT glut = new GLUT();
		// render all the objects of the scene
		synchronized ( objects ) {
			for (IVisObject o : objects) {
				if (! (o instanceof IGuiContainer))
					((IVisObject)o).render(gl);
			}
		}
	}
	public void drawGuiModel(GL gl)
	{
		final GLU glu = new GLU();
		final GLUT glut = new GLUT();

			// render all the objects of the scene
			gui.render(gl);
	}
	public void displaySelectionView(GLAutoDrawable glDrawable)
	{
		final GL gl = glDrawable.getGL();
		final GLU glu = new GLU();
		final GLUT glut = new GLUT();
	
		gl.glViewport(10, 10, 100, 100);
		gl.glMatrixMode(GL.GL_PROJECTION);
		gl.glLoadIdentity();
		
		gl.glMatrixMode(GL.GL_MODELVIEW);
		gl.glLoadIdentity();
//		drawModel(glDrawable);
		
	}
	public synchronized void addObject(IVisObject r) {
		objects.add(r);
	}

	public synchronized void removeObject(IVisObject r) {
		objects.remove(r);
	}

	public List<ICamera> getCameras() {
		return cameras;
	}

	public void addCamera(ICamera camera) {
		this.cameras.add(camera);
	}
	public ICamera getCameraByClass(Class camclass){
		for (ICamera c : cameras){
			if (c.getClass().equals(camclass))
				return c;
		}
		return null;
	}

	public synchronized List<IVisObject> getObjects() {
		return objects;
	}




}
