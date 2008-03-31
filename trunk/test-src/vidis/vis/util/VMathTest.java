package vidis.vis.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class VMathTest {

	@Test
	public void testMinus() {
		Vector4d A = new Vector4d(1.0, 1.0, 1.0, 1.0);
		Vector4d B = new Vector4d(4.0, 3.0, 2.0, 1.0);
		Vector4d C = VMath.minus(A, B);
		Vector4d C2 = new Vector4d(-3.0, -2.0, -1.0, 0.0);
		assertEquals(C2, C);
	}

	@Test
	public void testKreuz() {
		fail("TODO 4x4 matrix Kreuz");
		Vector4d A = new Vector4d(1.0, 2.0, 3.0, 1.0);
		Vector4d B = new Vector4d(4.0, 5.0, 6.0, 1.0);
		Vector4d C = VMath.kreuz(A, B);
		Vector4d C2 = new Vector4d(2*6-5*3, 1*6-4*3, 1*5-4*2, 0.0);
		assertEquals(C2, C);
		
	}

	@Test
	public void testMul() {
		Vector4d A = new Vector4d(1.0, 1.0, 1.0, 1.0);
		Vector4d C = VMath.mul(3.0, A);
		Vector4d C2 = new Vector4d(3.0, 3.0, 3.0, 3.0);
		assertEquals(C2, C);
	}

	@Test
	public void testAdd() {
		Vector4d A = new Vector4d(1.0, 1.0, 1.0, 1.0);
		Vector4d B = new Vector4d(4.0, 3.0, 2.0, 0.0);
		Vector4d C = VMath.add(A, B);
		Vector4d C2 = new Vector4d(5.0, 4.0, 3.0, 1.0);
		assertEquals(C2, C);
	}

}
