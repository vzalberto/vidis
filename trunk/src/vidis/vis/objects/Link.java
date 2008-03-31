package vidis.vis.objects;

import java.nio.FloatBuffer;
import java.util.HashMap;

import javax.media.opengl.GL;

import vidis.sim.framework.annotations.ComponentInfo;
import vidis.sim.framework.components.SimulatorComponentLink;
import vidis.vis.Scene;
import vidis.vis.objects.interfaces.Renderable;
import vidis.vis.shader.FragmentShader;
import vidis.vis.shader.Program;
import vidis.vis.shader.ShaderException;
import vidis.vis.shader.VertexShader;
import vidis.vis.shader.variable.ShaderVariable;
import vidis.vis.util.VMath;
import vidis.vis.util.Vector3d;
import vidis.vis.util.Vector4d;

public class Link extends Renderable {
	// uplink
	private SimulatorComponentLink orginal;
	// own things
	private Node node1;
	private Node node2;
	private FloatBuffer color;
	
	private HashMap<Packet, Double> packets;
	//
	// The Double goes form 0 to 1 = the position of the packet
	// 0 = Packet at node1's end
	// 1 = Packet at node2's end
	// 0.5 = Packet in the middle
	//
	public Link(SimulatorComponentLink orginal){
		this.orginal = orginal;
		// somehow call next constructor
		
		
	}
	private Packet x;
	private Packet y;
	public Link(Node node1, Node node2) {
		this.packets = new HashMap<Packet, Double>();
		this.node1 = node1;
		this.node2 = node2;	
		// testpacket
		x = new Packet(null);
		y = new Packet(null);
		Scene.getInstance().getObjects().add(x);
		Scene.getInstance().getObjects().add(y);
		this.packets.put(x, 0.5);
		this.packets.put(y, 0.9);
		new Thread(){
			double mode = 0.005;
			double mode2 = 0.008;
			@Override
			public void run() {
				while (true){
					try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
					
						e.printStackTrace();
					}
					if (packets.get(x)+mode<0) mode = 0.005;
					if (packets.get(x)+mode>1) mode = -0.005;
					packets.put(x, packets.get(x)+mode);
					if (packets.get(y)+mode2<0) mode2 *= -1;
					if (packets.get(y)+mode2>1) mode2 *= -1;
					packets.put(y, packets.get(y)+mode2);
				}
			}
		}.start();
		if (orginal!=null && orginal.getClass().getAnnotation(ComponentInfo.class)!=null)
			color = FloatBuffer.wrap(new float[]{	orginal.getClass().getAnnotation(ComponentInfo.class).color_red(),
													orginal.getClass().getAnnotation(ComponentInfo.class).color_green(),
													orginal.getClass().getAnnotation(ComponentInfo.class).color_blue()	});
		else
			color = FloatBuffer.wrap(new float[]{	0.5f, 0.5f, 0.5f	});
	}
	

	
	public void render(GL gl) {
		
		double abstand = 0.25;
		double deltax = Math.abs(node1.getPosx() - node2.getPosx());
		double deltay = Math.abs(node1.getPosy() - node2.getPosy());
		double distanz = Math.sqrt(Math.pow(deltax, 2)+Math.pow(deltay, 2));
		double xdiff = deltax / distanz * abstand;
		double ydiff = deltay / distanz * abstand;
		double x1,x2,y1,y2;
		if (node1.getPosx() > node2.getPosx()) {
			x1 = node1.getPosx() - xdiff;
			x2 = node2.getPosx() + xdiff;
		}
		else {
			x1 = node1.getPosx() + xdiff;
			x2 = node2.getPosx() - xdiff;
		}
		if (node1.getPosy() > node2.getPosy()) {
			y1 = node1.getPosy() - ydiff;
			y2 = node2.getPosy() + ydiff;
		}
		else {
			y1 = node1.getPosy() + ydiff;
			y2 = node2.getPosy() - ydiff;
		}
		
		gl.glPointSize(5);
		gl.glColor3fv(color);
		
		double dx = Math.abs(x1 - x2);
		double dy = Math.abs(y1 - y2);
		double l = Math.sqrt(Math.pow(dx, 2)+Math.pow(dy, 2));
		double r = 0.1;
		// 12
		double x12 = x2 - x1;
		double y12 = y2 - y1;
		// 12normiert
		double nn;
		if (Math.abs(x12)>Math.abs(y12)) nn = Math.abs(x12);
		else nn = Math.abs(y12);
		double x12n = x12 / nn;
		double y12n = y12 / nn;
		// normal
		double nx = y12n;
		double ny = -x12n;
		
		// getting Shader Variables
		ShaderVariable attribRadius = prog.getVariableByName("radius");
		ShaderVariable attribCenterpoint = prog.getVariableByName("centerpoint");
		
		// schleife für segmente
		int lsegmente = 50;
		Vector4d P1 = new Vector4d(x1, y1, 0d, 1d);
		Vector4d P2 = new Vector4d(x2, y2, 0d, 1d);
		
		Vector4d[] sgmpkte = new Vector4d[lsegmente+1];
		Vector4d P1P2 = VMath.minus(P2, P1);
		sgmpkte[0] = P1;
		for (int i=1; i<lsegmente; i++){
			sgmpkte[i] = VMath.add(sgmpkte[i-1], VMath.mul(1.0/lsegmente, P1P2));
		}
		sgmpkte[lsegmente] = P2;
		
		// check pakete
		
		
			
		double dl = l / (lsegmente - 1);
		gl.glPolygonMode(GL.GL_BACK, GL.GL_FILL);
		gl.glPolygonMode(GL.GL_FRONT, GL.GL_FILL);
		gl.glColor3fv(color);
		
		prog.use(gl);
		gl.glBegin(GL.GL_TRIANGLE_STRIP);
		for (int i=0; i<lsegmente; i+=1) {
			
			Vector4d vrechts = new Vector4d(nx, ny, 0, 0);
			Vector4d voben = new Vector4d(0, 0, 1, 0);

			float rr = 0.06f;
			float rr2 = rr;
			for (Double pos : packets.values()) {
				double rpos = l * pos;
				double renderpos = i * dl;
				double renderpos2 = (i+1)*dl;
				if (rpos +0.5 >= renderpos && rpos-0.5 <= renderpos) {
					double xx = rpos - renderpos;
					double xx2 = rpos - renderpos2;
					rr += (Math.cos(xx*Math.PI*2)+1)/20;
					rr2 += (Math.cos(xx2*Math.PI*2)+1)/20;
					
				}
			}
			
			
			for (int k = 0; k <= 10; k++) {
				double w = (360d/10d * k) /180d * Math.PI;
				Vector4d point = VMath.add(sgmpkte[i], VMath.add(VMath.mul(Math.sin(w), voben), VMath.mul(Math.cos(w), vrechts)));
				Vector4d point2 = VMath.add(sgmpkte[i+1], VMath.add(VMath.mul(Math.sin(w), voben), VMath.mul(Math.cos(w), vrechts)));
				Vector4d npoint = VMath.minus(point, sgmpkte[i]);
				Vector4d npoint2 = VMath.minus(point2, sgmpkte[i+1]);
				
				attribRadius.setValue(new Float(rr), gl);
				attribCenterpoint.setValue(sgmpkte[i].toVector3d(), gl);
				gl.glNormal3d(npoint.x, npoint.y, npoint.z);
				gl.glVertex3d(point.x, point.y, point.z);
				
				attribRadius.setValue(new Float(rr2), gl);
				attribCenterpoint.setValue(sgmpkte[i+1].toVector3d(), gl);
				gl.glNormal3d(npoint2.x, npoint2.y, npoint2.z);
				gl.glVertex3d(point2.x, point2.y, point2.z);
				
			}
			
			
		}
		gl.glEnd();
//		gl.glColor3f(1f, 0f, 0f);
//		gl.glBegin(GL.GL_LINE_STRIP);
//		for (int i=0; i<=lsegmente; i++) {
//			float rr = 1f;
//			for (Double pos : packets.values()) {
//				double rpos = l * pos;
//				double renderpos = i * dl;
//				if (rpos +0.5 >= renderpos && rpos-0.5 <= renderpos) {
//					double x = rpos - renderpos;
//					rr += (Math.cos(x*Math.PI*2)+1)/2;
//				}
//			}
//			gl.glVertexAttrib3d(centerpoint, sgmpkte[i].x, sgmpkte[i].y, sgmpkte[i].z);
//			gl.glVertexAttrib1d(radius, rr);
//			gl.glVertex3d(sgmpkte[i].x - r * nx, sgmpkte[i].y - r * ny, sgmpkte[i].z);
//		}
//		gl.glEnd();
		gl.glUseProgram(0);
		
		
		
		for (Packet p : packets.keySet()) {
			double pos = l * packets.get(p);
			double xdif=dx/l*pos;
			double ydif=dy/l*pos;
//			p.setPosx(x1+xdif);
//			p.setPosy(y1+ydif);
			if (x1 > x2)
				p.setPosx(x1-xdif);
			else
				p.setPosx(x1+xdif);
			if (y1 > y2)
				p.setPosy(y1-ydif);
			else
				p.setPosy(y1+ydif);
			//p.render(gl);
		}
	}

	public static Program prog;
	public static boolean init = false;
	public static void initShaders(GL gl){
		if (!init) {
			prog = new Program();
	        prog.create(gl);
	       
	        VertexShader lvshad = new VertexShader();
	        try { 
	        	lvshad.create(gl);
	        	lvshad.loadSource("shader\\LinkVertexShader.glsl", gl);
	        	lvshad.compile(gl);
	        	prog.addShader(lvshad);
	        } catch (ShaderException e) { e.printStackTrace(); }
	        FragmentShader lfshad = new FragmentShader();
	        try { 
	        	lfshad.create(gl);
	        	lfshad.loadSource("shader\\LinkFragmentShader.glsl", gl);
	        	lfshad.compile(gl);
	        	prog.addShader(lfshad);
	        } catch (ShaderException e) { e.printStackTrace(); }
	        prog.link(gl);
		}
       	init = true;
	}
	public Vector3d getPos() {
		return new Vector3d(0, 0, 0);
	}
}
