package geometry;

public class SystemEquation {
	private double m[][];

	public SystemEquation(double m[][]) {
		this.m = m;
	}

	public double[] solve_system_3_unknown() {
		double solve[] = new double[3];		
		double d = determinant_3_x_3(m[0][0], m[0][1], 1, m[1][0], m[1][1], 1,
				m[2][0], m[2][1], 1);

		double d_a = determinant_3_x_3(m[0][3], m[0][1], 1, m[1][3], m[1][1],
				1, m[2][3], m[2][1], 1);

		double d_b = determinant_3_x_3(m[0][0], m[0][3], 1, m[1][0], m[1][3],
				1, m[2][0], m[2][3], 1);

		double d_c = determinant_3_x_3(m[0][0], m[0][1], m[0][3], m[1][0],
				m[1][1], m[1][3], m[2][0], m[2][1], m[2][3]);

		solve[0] = d_a / d;
		solve[1] = d_b / d;
		solve[2] = d_c / d;
		return solve;
	}

	private double determinant_3_x_3(double a11, double a12, double a13, double a21,
			double a22, double a23, double a31, double a32, double a33) {
		return a11 * a22 * a33 + a12 * a23 * a31 + a13 * a21 * a32 - a13 * a22
				* a31 - a12 * a21 * a33 - a11 * a23 * a32;
	}
}
