package gameClient;

class Point2D extends Object {
    private double x, y;

    Point2D(int x, int y) {
        setX(x);
        setY(y);
    }
    Point2D(float x, float y) {
        setX(x);
        setY(y);
    }
    Point2D(double x, double y) {
        setX(x);
        setY(y);
    }

    double X() { return x; }
    public void setX(double x) { this.x = x; }
    public void setX(float x) { this.x = (double) x; }
    public void setX(int x) { this.x = (double) x; }

    double Y() { return y; }
    public void setY(double y) { this.y = y; }
    public void setY(float y) { this.y = (double) y; }
    public void setY(int y) { this.y = (double) y; }
}


