package jfx_lab03_hilos;

import javafx.animation.FadeTransition;
import javafx.animation.FadeTransitionBuilder;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

/**
 *
 * @author andresfelipegarciaduran
 */
public class Main extends Application {

    public static FadeTransition fadeTransitionRedLight;
    public static FadeTransition fadeTransitionGreenLight;
    public static TextArea output;

    private void initComponents(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, 600, 170);

        Rectangle trafficLight = new Rectangle(35, 25, 50, 120);
        trafficLight.setFill(Color.BLACK);
        trafficLight.setArcHeight(25);
        trafficLight.setArcWidth(25);
        root.getChildren().add(trafficLight);

        Circle redLight = new Circle(60, 60, 20, Color.RED);
        Circle redLightBorder = new Circle(60, 60, 20, Color.RED);
        redLightBorder.setStroke(Color.RED);
        redLightBorder.setStrokeWidth(1.5);
        redLightBorder.setFill(null);
        fadeTransitionRedLight = FadeTransitionBuilder.create()
                .node(redLight)
                .fromValue(0.2)
                .toValue(1)
                .cycleCount(Timeline.INDEFINITE)
                .autoReverse(true)
                .build();
        root.getChildren().add(redLight);
        root.getChildren().add(redLightBorder);

        Circle greenLight = new Circle(60, 110, 20, Color.GREEN);
        Circle greenLightBorder = new Circle(60, 110, 20, Color.GREEN);
        greenLightBorder.setStroke(Color.GREEN);
        greenLightBorder.setStrokeWidth(1.5);
        greenLightBorder.setFill(null);
        fadeTransitionGreenLight = FadeTransitionBuilder.create()
                .node(greenLight)
                .fromValue(0.2)
                .toValue(1)
                .cycleCount(Timeline.INDEFINITE)
                .autoReverse(true)
                .build();
        root.getChildren().add(greenLight);
        root.getChildren().add(greenLightBorder);

        output = new TextArea();
        output.setLayoutX(120);
        output.setLayoutY(30);
        output.setMaxSize(400, 100);
        output.setEditable(false);
        root.getChildren().add(output);

        primaryStage.setScene(scene);
    }

    @Override
    public void start(Stage primaryStage) {
        initComponents(primaryStage);
        primaryStage.setTitle("Lab03 - Starting practice threads interface JavaFX");
        primaryStage.setResizable(false);
        primaryStage.show();
        executeProccess();

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                System.exit(0);
            }

        });
    }

    private void executeProccess() {
        ProccessAccess proccess = new ProccessAccess(new Thread[]{
            new Thread("Hilo_Rojo 0"), new Thread("Hilo_Verde 0")
        });
        proccess.start();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
