package geometry;

public class Point implements Comparable<Point> {

    public double x, y;

    public Point(double x, double y) {
        this.x = (float) x;
        this.y = (float) y;
    }

    public Point() {
    }

    public double distance(Point point) {
        return Math.sqrt((this.x - point.x) * (this.x - point.x)
                + (this.y - point.y) * (this.y - point.y));
    }

    public boolean equals(Object obj) {
        Point p = (Point) obj;
        return (this.x == p.x && this.y == p.y);
    }

    public int compareTo(Point o) {
        if (o.y == this.y) {
            return (int) (o.x - this.x);
        }
        return 1;
    }

    public String signed(double r) {
        return (r < 0.0) ? "-" : "+";
    }

    @Override
    public String toString() {
        return "[x=" + x + " y=" + y + "]";
    }
}