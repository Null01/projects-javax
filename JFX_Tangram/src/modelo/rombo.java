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
public class rombo extends figura {

    private Point orig, p1, p2, p3;

    public rombo(Point p1, Point p2, Point p3, Point orig, Color color, String id) {
        super(color, id);
        this.orig = orig;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    public void setOrig(Point orig) {
        this.orig = orig;
    }

    public Shape build_rhombus(int angleRot, int key_add) {
        double[] points = {0, 0, p1.x, p1.y, p2.x, p2.y, p3.x, p3.y, 0, 0};
        points = rotate_single_shape_rhombus(points, key_add);
        Polyline pl = new Polyline(rotate_shape_respect_origin_rhombus(points, angleRot, 1));
        pl.setId(getClass().getName());
        System.out.println(orig.x + "   " + orig.y);
        pl.setLayoutX(orig.x);
        pl.setLayoutY(orig.y);
        pl.setFill(getColor());
        pl.setStrokeWidth(1.0);
        return build_rotation_simple_by_all_shapes(pl, 0);
    }

    private double[] rotate_single_shape_rhombus(double[] points, int key) {
        switch (key) {
            case 2:
                int diferencia = (int) Math.abs(points[5] - points[3]);
                return new double[]{0, 0, points[5], diferencia, points[5], points[2] + diferencia, 0, points[2], 0, 0,};
            default:
                return points;
        }

    }

    private double[] rotate_shape_respect_origin_rhombus(double[] points, int angle, int key_add) {
        if (angle == 0) {
            return points;
        }
        double newPoints[] = new double[points.length];
        int add = (key_add == 2) ? 45 : 0;
        double dist = Math.sqrt(Math.pow(points[2], 2) + Math.pow(points[3], 2));
        newPoints[2] = (int) (dist * Math.cos((angle + add) * Math.PI / 180.0));
        newPoints[3] = (int) (dist * Math.sin((angle + add) * Math.PI / 180.0));

        add = (key_add == 1) ? 45 : 90;
        dist = Math.sqrt(Math.pow(points[4], 2) + Math.pow(points[5], 2));
        newPoints[4] = (int) (dist * Math.cos((angle + add) * Math.PI / 180.0));
        newPoints[5] = (int) (dist * Math.sin((angle + add) * Math.PI / 180.0));

        add = (key_add == 1) ? 135 : 90;
        dist = Math.sqrt(Math.pow(points[6], 2) + Math.pow(points[7], 2));
        newPoints[6] = (int) (dist * Math.cos((angle + add) * Math.PI / 180.0));
        newPoints[7] = (int) (dist * Math.sin((angle + add) * Math.PI / 180.0));
        return newPoints;
    }
}
