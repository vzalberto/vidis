package ui.vis.multipass;

import javax.media.opengl.GL;

import vis.glutils.GLOption.Blending;
import vis.glutils.GLOption.DepthFunc;
import vis.glutils.GLOption.ShadeModel;

/*
 * PASS     Blending   Lighting Shade    DepthFunc  DepthMask
 *
 * tex 0	   No	     No     FLAT      LEQUAL       True
 * tex 1      Yes[1]     No     FLAT      EQUAL        False
 * tex n      Yes[1]     No     FLAT      EQUAL        False
 * diffuse    Yes[2]     Yes    SMOOTH    EQUAL        False
 * specular   Yes[1]     Yes    SMOOTH    EQUAL        False
 *
 * Notes:
 *   [1]   glBlendFunc(GL_ONE, GL_ONE)
 *   [2]   glBlendFunc(GL_ZERO, GL_SRC_COLOR)
 */




public enum RenderPass implements IPass {
	PASS_0_TEX( Blending.NONE, false, ShadeModel.FLAT, DepthFunc.LEQUAL, true ),
	PASS_n_TEX( Blending.ONE_ONE, false, ShadeModel.FLAT, DepthFunc.EQUAL, false ),
	PASS_diffuse( Blending.ZERO_SRC_COLOR, true, ShadeModel.SMOOTH, DepthFunc.EQUAL, false ),
	PASS_specular( Blending.ONE_ONE, true, ShadeModel.SMOOTH, DepthFunc.EQUAL, false );

	
	private Blending blending;
	private boolean lighting;
	private ShadeModel shadeModel;
	private DepthFunc depthFunc;
	private boolean depthMask;
	
	private RenderPass( Blending blend, boolean lighting, ShadeModel shade, DepthFunc depthFunc, boolean depthMask  ) {
		this.blending = blend;
		this.lighting = lighting;
		this.shadeModel = shade;
		this.depthFunc = depthFunc;
		this.depthMask = depthMask;
	}
	
	
	public void setup(GL gl) {
		blending.execute(gl);
		shadeModel.execute(gl);
		if (lighting) gl.glEnable( GL.GL_LIGHTING ); else gl.glDisable(GL.GL_LIGHTING);
		depthFunc.execute(gl);
		if (depthMask) gl.glEnable( GL.GL_DEPTH_TEST ); else gl.glDisable( GL.GL_DEPTH_TEST );
	}

}
