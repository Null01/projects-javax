/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.awt.Point;
import java.util.ArrayList;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Shape;

/**
 *
 * @author duran
 */
public class tangrama {

    public static final int wLayout = 950, hLayout = 650;
    private ArrayList<Shape> l;
    private double points[];
    private Point orig;

    public tangrama(double[] points, Point p, ArrayList<Shape> l) {
        this.points = points;
        this.orig = p;
        this.l = l;
    }

    public Shape build_tangrama_sketch() {

        Polyline pl = new Polyline(points);
        pl.setStrokeWidth(2.5);
        pl.setLayoutX(orig.x);
        pl.setLayoutY(orig.y);
        return pl;
    }
}
