package vidis.vis.objects;

import java.nio.FloatBuffer;

import javax.media.opengl.GL;

import vidis.sim.framework.annotations.ComponentInfo;
import vidis.sim.framework.components.SimulatorComponentNode;
import vidis.vis.objects.interfaces.Renderable;
import vidis.vis.objects.interfaces.Selectable;
import vidis.vis.shader.FragmentShader;
import vidis.vis.shader.Program;
import vidis.vis.shader.ShaderException;
import vidis.vis.shader.VertexShader;
import vidis.vis.util.GLTools;
import vidis.vis.util.Vector3d;
import vidis.vis.util.Vector4d;

import com.sun.opengl.util.GLUT;

public class Node extends Renderable implements Selectable {

	private boolean selected;
	private SimulatorComponentNode simnode;
//	private double posx;
//	private double posy;
	private double radius = 0.3;
	private int segments = 20;
	
	private FloatBuffer color;
	
	public Node(SimulatorComponentNode simnode){
		this.simnode = simnode;
		posx = -1;
		posy = -1;
//		System.out.println("Node: "+simnode);
//		if (simnode != null) {
//			//System.out.println(simnode.getClass().getAnnotations().length + " Annotations");
//			for (Annotation a : simnode.getClass().getAnnotations())
//				System.out.println("-> Annotation: " + a.toString());
//			//System.out.println(simnode.getClass().getMethods().length + " Fields");
//			for (Field f : simnode.getClass().getFields()) {
//				if (f.getAnnotations().length != 0){
//					//System.out.println(" - Field " + f.getName() + " " + f.getAnnotations().length + " Annotations");
//					for (Annotation a : f.getAnnotations())
//						System.out.println("   -> "+f.getName()+" Annotation: " + a.toString());
//				}
//			}
//		}
		if (simnode!=null && simnode.getClass().getAnnotation(ComponentInfo.class)!=null)
			color = FloatBuffer.wrap(new float[]{	simnode.getClass().getAnnotation(ComponentInfo.class).color_red(),
													simnode.getClass().getAnnotation(ComponentInfo.class).color_green(),
													simnode.getClass().getAnnotation(ComponentInfo.class).color_blue()	});
		else
			color = FloatBuffer.wrap(new float[]{	1, 0, 0	});
		
	}
	public void render(GL gl) {
		gl.glLineWidth(1.0f);
		gl.glColor3d(1.0, 0.0, 0.0);
		if (isSelected()) gl.glColor3d(1.0,1.0,0.0);
		GLUT glut = new GLUT();
		prog.use(gl);
		gl.glPolygonMode(GL.GL_FRONT, GL.GL_FILL);
		gl.glPushMatrix();
			gl.glTranslated(posx, posy, 0);
			glut.glutSolidSphere(radius, segments, segments);
		gl.glPopMatrix();
		gl.glUseProgram(0);
/*		
		// shader test
		prog.use(gl);
		int coolestcolorloc = gl.glGetUniformLocation(prog.getProgramId(), "CoolestColor");		
		int hottestcolorloc = gl.glGetUniformLocation(prog.getProgramId(), "HottestColor");		
		int coolesttemploc = gl.glGetUniformLocation(prog.getProgramId(), "CoolestTemp");
		int temprangeloc = gl.glGetUniformLocation(prog.getProgramId(), "TempRange");		
		int vertextemploc = gl.glGetAttribLocation(prog.getProgramId(), "VertexTemp");
		
		gl.glUniform3f(coolestcolorloc, 1f, 1f, 0f);
		gl.glUniform3f(hottestcolorloc, 1f, 0f, 0f);
		gl.glUniform1f(coolesttemploc, 1f);
		gl.glUniform1f(temprangeloc, 3f);
		
		// draw Circle
		gl.glColor3fv(color);
		gl.glLineWidth(2f);
		//GLTools.drawFilledCircle(posx, posy, 0, radius, segments, gl);
		gl.glPushMatrix();
		gl.glPolygonMode(GL.GL_BACK, GL.GL_FILL);
		gl.glBegin(gl.GL_POLYGON);
		for (int i=0; i<=segments; i++){
			double w = i*36d/180*Math.PI;
				
				gl.glVertexAttrib1d(vertextemploc, w);
			
				gl.glVertex3d(posx + Math.cos(w)*radius, posy + Math.sin(w)*radius, 0);
			
		}
		gl.glEnd();
		gl.glPopMatrix();
		gl.glUseProgram(0);

		// draw selection circle
		if (selected){
			gl.glColor3f(1f, 1f, 0f);
			gl.glLineWidth(1f);
			GLTools.drawFilledCircle(posx, posy, 0.1, radius + 0.05, segments, gl);
		}
*/		
	}
	
	public double getPosx() {
		return posx;
	}
	public void setPosx(double posx) {
		this.posx = posx;
	}
	public double getPosy() {
		return posy;
	}
	public void setPosy(double posy) {
		this.posy = posy;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
		
	}
	public boolean isHit(Vector4d point) {
//		System.out.print("isHit("+point+") ");
		double dx = Math.abs(posx - point.x);
		double dy = Math.abs(posy - point.y);
		double dist = Math.sqrt(Math.pow(dx, 2)+Math.pow(dy, 2));
		this.selected = dist <= radius;
//		System.out.println(this.selected);
		return this.selected;
	}
	
	public static Program prog;
	public static boolean init = false;
	public static void initShaders(GL gl){
		if (!init) {
			prog = new Program();
	        prog.create(gl);
	       
	        VertexShader exvshad = new VertexShader();
	        try { 
	        	exvshad.create(gl);
	        	exvshad.loadSource("shader\\NodeVertexShader.glsl", gl);
	        	exvshad.compile(gl);
	        	prog.addShader(exvshad);
	        } catch (ShaderException e) { e.printStackTrace(); }
	        
	        FragmentShader exfshad = new FragmentShader();
	        try {
		        exfshad.create(gl);
		        exfshad.loadSource("shader\\NodeFragmentShader.glsl", gl);
		        exfshad.compile(gl);
		        prog.addShader(exfshad);
	        } catch (ShaderException e) { e.printStackTrace(); }
	        
	        
	        prog.link(gl);
		}
       	init = true;
	}
	public Vector3d getPos() {
		return new Vector3d(this.posx, this.posy, 0);
	}
}
