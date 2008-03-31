package vidis.vis.objects.interfaces;

import javax.media.opengl.GL;

import vidis.vis.util.Vector3d;

/**
 * 
 * @author Christoph
 *
 */
public abstract class Renderable {
	/**
	 * Die Methode render muss zwingend implementiert werden. Sie ist für das 'malen' des Objekts verantwortlich.
	 * @param gl
	 */
	public abstract void render(GL gl);

	public double posx;
	public double posy;
	public double posz;
	
	public Vector3d getPos() {
		return new Vector3d(posx, posy, posz);
	}
}
