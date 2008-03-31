package vidis.vis.util;

import java.nio.DoubleBuffer;

public class Vector3d {
	public double x;
	public double y;
	public double z;
	
	public Vector3d(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public static Vector3d fromDoubleBuffer(DoubleBuffer b) {
		return new Vector3d(b.get(0), b.get(1), b.get(2));
	}
}
