package ui.vis;

import javax.vecmath.Tuple3d;
import javax.vecmath.Vector3d;

import org.apache.log4j.Logger;

public class VecUtil {
	private static Logger logger = Logger.getLogger(VecUtil.class);

	public static Vector3d cross( Tuple3d A, Tuple3d B ){
		Vector3d ret = new Vector3d();
		ret.x = A.y * B.z - A.z * B.y;
		ret.y = A.x * B.z - A.z * B.x;
		ret.z = A.x * B.y - A.y * B.x;
		return ret;
	}
}
