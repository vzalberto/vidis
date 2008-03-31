package vidis.vis;

import java.util.ArrayList;
import java.util.List;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

import vidis.vis.camera.Camera;
import vidis.vis.camera.DefaultCamera;
import vidis.vis.camera.SelectionCamera;
import vidis.vis.gui.input.GuiInputListener;
import vidis.vis.objects.Clicker;
import vidis.vis.objects.Grid;
import vidis.vis.objects.Link;
import vidis.vis.objects.Marker;
import vidis.vis.objects.Node;
import vidis.vis.objects.interfaces.Renderable;
import vidis.vis.shader.FragmentShader;
import vidis.vis.shader.Program;
import vidis.vis.shader.ShaderException;
import vidis.vis.shader.VertexShader;

import com.sun.opengl.util.Animator;
import com.sun.opengl.util.GLUT;

/**
 * Diese Klasse stellt die zu zeichnende Szene dar Sie rendert alle Objekte
 * 
 * @author Christoph
 * 
 */
public class Scene implements GLEventListener {
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
	/**
	 * OpenGL Canvas
	 */
	private GLCanvas canvas;
	/**
	 * all renderable Objects
	 */
	private ArrayList<Renderable> objects;
	/**
	 * The camera
	 */
	//private Camera camera;

	private List<Camera> cameras;
	
	public Marker marker = new Marker();
	private Clicker clicker = new Clicker();


	/**
	 * private constructor ( use static method getInstance() )
	 */
	private Scene() {
		cameras = new ArrayList<Camera>();
		cameras.add(new DefaultCamera());
		cameras.add(new SelectionCamera());
		
		objects = new ArrayList<Renderable>();
		objects.add(clicker);
		objects.add(marker);
		canvas = new GLCanvas();
		canvas.addGLEventListener(this);
		
		GuiInputListener l = new GuiInputListener(this);
		canvas.addKeyListener(l);
		canvas.addMouseWheelListener(l);
		canvas.addMouseMotionListener(l);
		canvas.addMouseListener(l);
		
		for (Camera c : cameras)
			l.addActionHandler(c);

		//camera = new DefaultCamera();
		new ObjectAnimator(this);
	}

	public GLCanvas getGLCanvas() {
		return canvas;
	}

	/**
	 * Used to animate the scene
	 */
	Animator animator;

	// contain the key pressed
	private boolean[] keys = new boolean[256];

	private float zOffset = -5.0f;

	/***************************************************************************
	 * The displayChanged event *
	 * ******************************************************* Called when the
	 * display mode has been changed : * CURRENTLY UNIMPLEMENTED IN JOGL *
	 **************************************************************************/
	public void displayChanged(GLAutoDrawable glDrawable, boolean modeChanged,
			boolean deviceChanged) {
	} 
	private Program prog;
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
		for (Camera c : cameras)
			c.reshape(x, y, width, height);
		
	}

	/***************************************************************************
	 * The init method *
	 * ********************************************************** Call by the
	 * GLDrawable just after the Gl-Context is * initialized. * * This method is
	 * used to : * - activate some modes like texture, light ... * - affect
	 * value to properties * - import testures, load your data ... * *
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
		Node.initShaders(gl);
		Link.initShaders(gl);
		
		//temp init reflect shader TODO global shadermanagement
		prog = new Program();
        prog.create(gl);
       
        VertexShader exvshad = new VertexShader();
        try { 
        	exvshad.create(gl);
        	exvshad.loadSource("shader\\ReflectionVertexShader.glsl", gl);
        	exvshad.compile(gl);
        	prog.addShader(exvshad);
        } catch (ShaderException e) { e.printStackTrace(); }
        
        FragmentShader exfshad = new FragmentShader();
        try {
	        exfshad.create(gl);
	        exfshad.loadSource("shader\\ReflectionFragmentShader.glsl", gl);
	        exfshad.compile(gl);
	        prog.addShader(exfshad);
        } catch (ShaderException e) { e.printStackTrace(); }
        
        
        prog.link(gl);
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
		final GL gl = glDrawable.getGL();
		final GLU glu = new GLU();
		final GLUT glut = new GLUT();

		for (Camera c : cameras) {
			// SETUP
			c.init(gl);
			// PROJECTION
			gl.glMatrixMode(GL.GL_PROJECTION);
			gl.glLoadIdentity();
			c.applyProjectionMatrix(gl);
			// MODELVIEW
			gl.glMatrixMode(GL.GL_MODELVIEW);
			gl.glLoadIdentity();
			c.applyViewMatrix(gl);
			gl.glPushMatrix();
				//gl.glScaled(1.0, 1.0, -1.0);
				
				gl.glTranslated(0.0, 0.0, 1);
				prog.use(gl);
				drawModel(glDrawable);
				gl.glUseProgram(0);
				gl.glPopMatrix();
			gl.glPushMatrix();
				Grid g = new Grid();
				gl.glTranslated(0.0, 0.0, 0.5);
				g.render(gl);
			gl.glPopMatrix();
				gl.glPushMatrix();
				gl.glTranslated(0.0, 0.0, 0);
				drawModel(glDrawable);
			gl.glPopMatrix();
		}
		
		
		/*
		// +-----------------+
		// +--- Main View ---+
		// +-----------------+
		// SETUP
			camera.init(gl);
		// PROJECTION
			gl.glMatrixMode(GL.GL_PROJECTION);
			gl.glLoadIdentity();
			camera.applyProjectionMatrix(gl);
		// MODELVIEW
			gl.glMatrixMode(GL.GL_MODELVIEW);
			gl.glLoadIdentity();
			camera.applyViewMatrix(gl);
			drawModel(glDrawable);
		// matrizen auslesen für click berechnung
			gl.glGetDoublev(GL.GL_MODELVIEW_MATRIX, model);
			gl.glGetDoublev(GL.GL_PROJECTION_MATRIX, proj);
			gl.glGetIntegerv(GL.GL_VIEWPORT, view);
		// +----------------------+
		// +--- Selection View ---+
		// +----------------------+
		// SETUP
			
		// PROJECTION
			gl.glMatrixMode(GL.GL_PROJECTION);
			gl.glLoadIdentity();

			//glu.gluLookAt(0, 0, -5, 0, 0, 0, 0, 1, 0);
		// MODELVIEW
			gl.glMatrixMode(GL.GL_MODELVIEW);
			gl.glLoadIdentity();
			for (Renderable a : this.objects)
				if (a instanceof Selectable)
					if (((Selectable)a).isSelected()) {
						gl.glTranslated(-a.getPos().x, -a.getPos().y, 0);
					}
			drawModel(glDrawable);
		
		*/

	}
	public void drawModel(GLAutoDrawable glDrawable)
	{
		final GL gl = glDrawable.getGL();
		final GLU glu = new GLU();
		final GLUT glut = new GLUT();

		// move world for the camera
			// glut.glutSolidCube(1);
			gl.glColor3f(1f, 0f, 0f);
	
			// render all the objects of the scene
			for (Renderable o : objects)
				o.render(gl);
			gl.glPushMatrix();
				gl.glLineWidth(1.0f);
				// glut.glutWireSphere(1, 12, 12);
				gl.glTranslated(-5, 0, 0);
			gl.glPopMatrix();



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
		drawModel(glDrawable);
		
	}
	public void addObject(Renderable r) {
		objects.add(r);
	}

	public void removeObject(Renderable r) {
		objects.remove(r);
	}

	public List<Camera> getCameras() {
		return cameras;
	}

	public void addCamera(Camera camera) {
		this.cameras.add(camera);
	}
	public Camera getCameraByClass(Class camclass){
		for (Camera c : cameras){
			if (c.getClass().equals(camclass))
				return c;
		}
		return null;
	}

	public ArrayList<Renderable> getObjects() {
		return objects;
	}




}
