/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.Point;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

/**
 *
 * @author duran
 */
public class Main extends Application {

    public void start(Stage primaryStage) {

        Group tangrama = new Group();
        Point origen = new Point(250, 350);
        Polyline polygon;

        modelo.cuadrado c1 = new modelo.cuadrado(origen, 100, Color.YELLOW, "YELLOW");
        polygon = (Polyline) c1.build_square(270 + 45);
        tangrama.getChildren().add(polygon);

        modelo.triangulo t1 = new modelo.triangulo(new Point(0, 0), new Point(100, 0), new Point(100, 100), origen, Color.BLUE, "BLUE");
        polygon = (Polyline) t1.build_triangle(45, 2);
        tangrama.getChildren().add(polygon);

        modelo.triangulo t2 = new modelo.triangulo(new Point(0, 0), new Point(200, 0), new Point(200, 200), origen, Color.GREEN, "GREEN");
        polygon = (Polyline) t2.build_triangle(180 + 45, 2);
        tangrama.getChildren().add(polygon);

        modelo.triangulo t3 = new modelo.triangulo(new Point(0, 0), new Point(200, 0), new Point(200, 200), origen, Color.ORANGERED, "ORANGERED");
        polygon = (Polyline) t3.build_triangle(90 + 45, 2);
        tangrama.getChildren().add(polygon);

        modelo.triangulo t4 = new modelo.triangulo(new Point(0, 0), new Point(140, 0), new Point(140, 140), origen, Color.BROWN, "BROWN");
        polygon = (Polyline) t4.build_triangle(0, 4);
        tangrama.getChildren().add(polygon);

        Point translate = new Point(origen.x + 70, origen.y - 70);
        modelo.triangulo t5 = new modelo.triangulo(new Point(0, 0), new Point(100, 0), new Point(100, 100), translate, Color.RED, "RED");
        tangrama.getChildren().add(t5.build_triangle(270 + 45, 2));

        translate = new Point(origen.x - 70, origen.y + 70);
        modelo.rombo r1 = new modelo.rombo(new Point(140, 0), new Point(70, 70), new Point(-70, 70), translate, Color.AQUA, "AQUA");
        polygon = (Polyline) r1.build_rhombus(0, 1);
        tangrama.getChildren().add(polygon);


        ArrayList<Shape> shapes = new ArrayList<>();
        origen = new Point(600, 350);

        t2.setOrig(origen);
        polygon = (Polyline) t2.build_triangle(270 + 45, 3);
        shapes.add(polygon);

        t3.setOrig(origen);
        polygon = (Polyline) t3.build_triangle(0, 3);
        shapes.add(polygon);

        t4.setOrig(origen);
        polygon = (Polyline) t4.build_triangle(45, 3);
        shapes.add(polygon);

        translate = new Point(origen.x - 30, origen.y + 30);
        t1.setOrig(translate);
        polygon = (Polyline) t1.build_triangle(90 + 45, 2);
        shapes.add(polygon);

        c1.setOrig(translate);
        polygon = (Polyline) c1.build_square(180 + 45);
        shapes.add(polygon);



        translate = new Point(translate.x, translate.y - 140);
        r1.setOrig(translate);
        polygon = (Polyline) r1.build_rhombus(270, 1);
        shapes.add(polygon);

        translate = new Point(translate.x, translate.y - 140);
        t5.setOrig(translate);
        polygon = (Polyline) t5.build_triangle(90, 1);
        shapes.add(polygon);

        double points[] = {0, 0, 282, 0, 141, 141, 200, 200, 0, 200, -98.0, 98.0, -70 - 30, -70 + 30, 0 - 30, -141 + 30, 0 - 30, -150, -130, -150.0, -30, -250, 40, -180, 40, -40, 0, 0};
        modelo.tangrama tangrama_shape = new modelo.tangrama(points, origen, shapes);
        polygon = (Polyline) tangrama_shape.build_tangrama_sketch();
        tangrama.getChildren().add(polygon);




        initialy(tangrama, primaryStage);
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private void initialy(Group tangrama, Stage primaryStage) {
        Group p = new Group();
        Label labelMsg = new Label("Arrastra una figura al poligno.");
        labelMsg.setLayoutX(650);
        labelMsg.setLayoutY(320);
        labelMsg.setTextFill(Color.RED);
        p.getChildren().add(labelMsg);

        tangrama.getChildren().add(p);
        Scene scene = new Scene(tangrama, modelo.tangrama.wLayout, modelo.tangrama.hLayout);
        primaryStage.setTitle("Agente Simple");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}