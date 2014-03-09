package modelo;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Point;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Shape;

/**
 *
 * @author duran
 */
public class triangulo extends figura {

    private Point p1, p2, p3, orig;

    public triangulo(Point p1, Point p2, Point p3, Point orig, Color c, String id) {
        super(c, id);
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.orig = orig;
    }

    public void setOrig(Point orig) {
        this.orig = orig;
    }

    public Shape build_triangle(int angleOrig, int key_add) {
        double[] points = new double[]{
            p1.getX(), p1.getY(),
            p2.getX(), p2.getY(),
            p3.getX(), p3.getY(),
            p1.getX(), p1.getY(),};
        points = rotate_single_shape(points, key_add);
        Polyline polyline1 = new Polyline(rotate_shape_respect_origin(points, angleOrig, key_add));
        polyline1.setId(getClass().getName());
        polyline1.setLayoutX(orig.x);
        polyline1.setLayoutY(orig.y);
        polyline1.setFill(getColor());
        polyline1.setStrokeWidth(1.0);
        return build_rotation_simple_by_all_shapes(polyline1, 0);
    }
}
