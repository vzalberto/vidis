package vidis.vis.util;

public class VMath {
	/**
	 * calculates A-B
	 * @param A Vector A
	 * @param B Vector B
	 * @return
	 */
	public static Vector4d minus(Vector4d A, Vector4d B) {
		return new Vector4d(	A.x - B.x,
								A.y - B.y,
								A.z - B.z,
								A.w - B.w);
		
	}
	/**
	 * calculates A x B
	 * @param A Vector A
	 * @param B Vector B
	 * @return
	 */
	public static Vector4d kreuz(Vector4d A, Vector4d B) {
		return new Vector4d(A.y*B.z - A.z*B.y,
							A.x*B.z - A.z*B.x,
							A.x*B.y - A.y*B.x,
							0); // TODO Kreuzprodukt für 4 stelligen Vektor??
	}
	/**
	 * calculates v * A
	 * @param v Skalar v
	 * @param A Vector A
	 * @return
	 */
	public static Vector4d mul(double v, Vector4d A) {
		return new Vector4d(A.x * v, A.y * v, A.z * v, A.w * v);
	}
	/**
	 * calculates A + B
	 * @param A Vector A
	 * @param B Vector B
	 * @return
	 */
	public static Vector4d add(Vector4d A, Vector4d B) {
		return new Vector4d(A.x + B.x, A.y + B.y, A.z + B.z, A.w +B.w );
	}
}
