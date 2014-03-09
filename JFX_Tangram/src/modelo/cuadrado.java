/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.awt.Point;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Shape;

/**
 *
 * @author duran
 */
public class cuadrado extends figura {

    private Point orig;
    private double l;

    public cuadrado(Point orig, double l, Color c, String id) {
        super(c, id);
        this.orig = orig;
        this.l = l;

    }

    public void setOrig(Point orig) {
        this.orig = orig;
    }

    public Shape build_square(int angle) {
        double[] points = {l, 0, l, l, 0, l, 0, 0, 0, 0};
        double[] copypoints = rotate_shape_respect_origin(points, angle, 4);

        for (int i = 2; i < points.length; i += 2) {
            points[i] = copypoints[i - 2];
            points[i + 1] = copypoints[i - 1];
        }
        points[0] = points[1] = points[8] = points[9] = 0;

        Polyline polyline1 = new Polyline(points);
        polyline1.setId(getClass().getName());
        polyline1.setLayoutX(orig.x);
        polyline1.setLayoutY(orig.y);
        polyline1.setFill(this.getColor());
        polyline1.setStrokeWidth(1.0);

        return build_rotation_simple_by_all_shapes(polyline1, 0);
    }
}
