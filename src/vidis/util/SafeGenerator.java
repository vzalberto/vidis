package vidis.util;

import java.util.LinkedList;
import java.util.List;

import javax.vecmath.Point3d;

import vidis.data.sim.SimLink;
import vidis.data.var.AVariable;

/**
 * very safe generator even safer for unsafe operations made safe xD
 * 
 * @author dominik
 * 
 */
public class SafeGenerator {
	private static final double aMin = 0.15;
	private static final double aMax = 1.0;
	private static final double dMin = 1.0;
	private static final double dMax = 6;
	private static double a;
	private static double d;
	static {
		setNodeDensity(.2);
	}
	private static List<Point3d> points = new LinkedList<Point3d>();

	private static double spirale_rt(double t) {
		return a * t;
	}

	private static double spirale_st(double t) {
		return a / 2 * (Math.log(Math.sqrt(t + 1) + 1) + t * Math.sqrt(t * t + 1));
	}

	private static double spirale_xt(double t) {
		return spirale_rt(t) * Math.cos(t);
	}

	private static double spirale_yt(double t) {
		return 0;
	}

	private static double spirale_zt(double t) {
		return spirale_rt(t) * Math.sin(t);
	}

	/**
	 * retrieve the next point for a node
	 * 
	 * @return a point3d
	 */
	public static Point3d nextNodePoint3d() {
		Point3d tmp = new Point3d();
		double pi64 = (Math.PI / 64);
		// distance
		double t = 0.0;
		while (spirale_st(t) <= (points.size() + 1) * d) {
			t += pi64;
		}
		tmp.x = spirale_xt(t);
		tmp.y = spirale_yt(t);
		tmp.z = spirale_zt(t);
		points.add(tmp);
		return tmp;
	}

	public static void reset() {
		points.clear();
	}

	/**
	 * the smaller the value, the more "dense" all points will be
	 * 
	 * @param density
	 *          the density to set (double [0..1])
	 */
	public static void setNodeDensity(double density) {
		density = Math.max(0.0, density);
		density = Math.min(1.0, density);
		a = density * (aMax - aMin) + aMin;
		d = density * (dMax - aMin) + dMin;
	}

	public static boolean detectCollision(SimLink link1, SimLink link2) {
		// TODO .. quite buggy, don't know why not working!
		Point3d l1_start, l1_end, l2_start, l2_end;
		l1_start = (Point3d) (link1.getNodeASim().getVariableById(AVariable.COMMON_IDENTIFIERS.POSITION).getData());
		l1_end = (Point3d) (link1.getNodeBSim().getVariableById(AVariable.COMMON_IDENTIFIERS.POSITION).getData());
		l2_start = (Point3d) (link2.getNodeASim().getVariableById(AVariable.COMMON_IDENTIFIERS.POSITION).getData());
		l2_end = (Point3d) (link2.getNodeBSim().getVariableById(AVariable.COMMON_IDENTIFIERS.POSITION).getData());

		double l1_b = (l1_start.x - l1_end.x) / (l1_start.z - l1_end.z);
		double l1_c = l1_start.z / (l1_b * l1_start.x);

		double l2_b = (l2_start.x - l2_end.x) / (l2_start.z - l2_end.z);
		double l2_c = l2_start.z / (l2_b * l2_start.x);

		double tmp = l1_b - l2_b;
		if (tmp != 0) {
			double s_x = (l2_c - l1_c) / (tmp);
			double s_z = (l1_b * l2_c - l2_b * l1_c) / (tmp);
			double l1_x_min = Math.min(l1_start.x, l1_end.x);
			double l1_x_max = Math.max(l1_start.x, l1_end.x);
			double l2_x_min = Math.min(l2_start.x, l2_end.x);
			double l2_x_max = Math.max(l2_start.x, l2_end.x);
			double l1_z_min = Math.min(l1_start.z, l1_end.z);
			double l1_z_max = Math.max(l1_start.z, l1_end.z);
			double l2_z_min = Math.min(l2_start.z, l2_end.z);
			double l2_z_max = Math.max(l2_start.z, l2_end.z);
			if (s_x > l1_x_min && s_x < l1_x_max) {
				if (s_x > l2_x_min && s_x < l2_x_max) {
					if (s_z > l1_z_min && s_z < l1_z_max) {
						if (s_z > l2_z_min && s_z < l2_z_max) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
}