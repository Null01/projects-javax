/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geometry;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.StringTokenizer;

/**
 *
 * @author duran
 * @version 1.0
 */
public class Line {

    public static final double EPSILON = 0.00000001;
    private Point p1, p2;
    private final double ZERO = 1e-8;
    private final double INF = 1e100;

    public Line() {
    }

    public Line(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public Point intersectLine(Line l1, Line l2) {
        double m1, m2;
        // halla la tendecia de la pendiente.
        m1 = (equals(l1.p1.x, l1.p2.x)) ? INF : ((l1.p2.y - l1.p1.y) / (l1.p2.x - l1.p1.x));
        m2 = (equals(l2.p1.x, l2.p2.x)) ? INF : ((l2.p2.y - l2.p1.y) / (l2.p2.x - l2.p1.x));
        // determina si las rectas se interseptan.
        if (equals(m1, m2)) {
            // determina si la recta pertenese a la otra o existe intersepcion
            if (equals(l1.p1.y - m1 * l1.p1.x, l2.p1.y - m2 * l2.p1.x)) {
                // EXISTE INTERSEPCION.
            } else {
                // NO LA INTERSEPCION.
            }
        }

        Point out = new Point();
        // calcula el punto de intersepcion en X
        out.x = ((m1 * l1.p1.x - l1.p1.y) - (m2 * l2.p1.x - l2.p1.y)) / (m1 - m2);
        if (equals(l1.p1.x, l1.p2.x)) {
            out.x = l1.p1.x;
        }
        if (equals(l2.p1.x, l2.p2.x)) {
            out.x = l2.p1.x;
        }
        // calcula el punto de intersepacion en Y
        out.y = m1 * (out.x - l1.p1.x) + l1.p1.y;
        if (equals(l1.p1.x, l1.p2.x)) {
            out.y = m2 * (out.x - l2.p1.x) + l2.p1.y;
        }
        return out;
    }

    public String DoubleParseFraction(double Double) {
        String string_double = Double + "";
        System.out.println("--> " + string_double);
        int integer = string_double.indexOf('.');
        if (integer == -1) {
            return string_double + "/1";
        } else {
            int size_char = string_double.length() - (integer + 1);
            string_double = string_double.replace(".", "");
            BigInteger den = BigInteger.ONE;
            BigInteger num = new BigInteger(string_double);
            while (size_char-- > 0) {
                den = den.multiply(BigInteger.TEN);
            }
            if (den.compareTo(num) >= 0) {
                BigDecimal decimal_a = new BigDecimal(num);
                BigDecimal decimal_b = new BigDecimal(den);
                BigDecimal divide = decimal_b.divide(decimal_a, 0, RoundingMode.HALF_UP);
                return "1/" + divide;
            } else {
                BigInteger mcd = mcd(num, den);
                return (num.divide(mcd) + "/" + den.divide(mcd));
            }

        }
    }

    public boolean equals(double a, double b) {
        return (Math.abs((a) - (b)) < ZERO);
    }

    public BigInteger mcd(BigInteger num, BigInteger den) {
        BigInteger n = num.abs();
        BigInteger d = den.abs();
        if (d == BigInteger.ZERO) {
            return n;
        }
        BigInteger r = BigInteger.ZERO;
        while (d != BigInteger.ZERO) {
            r = n.mod(d);
            n = d;
            d = r;
        }
        return n;
    }

}
