
package geometry;


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

    public boolean equals(double a, double b) {
        return (Math.abs((a) - (b)) < ZERO);
    }

}
