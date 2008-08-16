package vis.model.structure;

import javax.media.opengl.GL;

public interface ISimObject extends IVisObject, IEventHandler{

	public void render( GL gl );
	
}
