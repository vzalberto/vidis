package vidis.vis.util;

import java.nio.DoubleBuffer;

public class Vector4d extends Vector3d{
	public double w;
	
	public Vector4d(double x, double y, double z, double w) {
		super(x, y, z);
		this.w = w;
	}
	
	public static Vector4d fromDoubleBuffer(DoubleBuffer b) {
		return new Vector4d (b.get(0), b.get(1), b.get(2), b.get(3));
	}
	public static Vector4d fromDoubleBuffer(DoubleBuffer b, double w) {
		return new Vector4d (b.get(0), b.get(1), b.get(2), w);
	}
	
	public String toString(){
		return "[ "+x+", "+y+", "+z+", "+w+" ]";
	}
	@Override
	public boolean equals(Object arg0) {
		if (arg0 instanceof Vector4d){
			Vector4d V = (Vector4d) arg0;
			if (this.x == V.x && this.y == V.y && this.z == V.z && this.w == V.w)
				return true;
			else 
				return false;
		}
		else
			return super.equals(arg0);
	}
	
	public Vector3d toVector3d(){
		return new Vector3d(this.x, this.y, this.z);
	}
	
}
