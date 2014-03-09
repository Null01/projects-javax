/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javafx.animation.RotateTransition;
import javafx.animation.RotateTransitionBuilder;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.RotateEvent;
import javafx.scene.input.ZoomEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

/**
 *
 * @author duran
 */
public class figura {

    private String id;
    private Color color;
    private Shape postView;

    public figura() {
    }

    public figura(Color color, String id) {
        this.color = color;
        this.id = id;
    }

    public Color getColor() {
        return color;
    }

    public String getId() {
        return id;
    }

    public double[] rotate_shape_respect_origin(double[] points, double angle, int key_add) {
        if (angle == 0) {
            return points;
        }

        double newPoints[] = new double[points.length];
        int add = (key_add == 3 || key_add == 4) ? 45 : 0;
        double dist = Math.sqrt(Math.pow(points[2], 2) + Math.pow(points[3], 2));
        newPoints[2] = (int) (dist * Math.cos((angle + add) * Math.PI / 180.0));
        newPoints[3] = (int) (dist * Math.sin((angle + add) * Math.PI / 180.0));

        add = (key_add == 1) ? 45 : 90;
        dist = Math.sqrt(Math.pow(points[4], 2) + Math.pow(points[5], 2));
        newPoints[4] = (int) (dist * Math.cos((angle + add) * Math.PI / 180.0));
        newPoints[5] = (int) (dist * Math.sin((angle + add) * Math.PI / 180.0));

        if (key_add == 4) {
            dist = Math.sqrt(Math.pow(points[0], 2) + Math.pow(points[1], 2));
            newPoints[6] = newPoints[0] = (int) (dist * Math.cos(angle * Math.PI / 180.0));
            newPoints[7] = newPoints[1] = (int) (dist * Math.sin(angle * Math.PI / 180.0));
        }

        return newPoints;
    }

    public double[] rotate_single_shape(double[] points, int key) {
        double[] newPoints = points;
        switch (key) {
            case 2:
                return new double[]{0, 0, points[2], 0, 0, points[2], 0, 0};
            case 3:
                return new double[]{0, 0, points[4], points[5], 0, points[5], 0, 0};
            case 4:
                return new double[]{points[2], 0, points[4], points[5], 0, points[5], points[2], 0};
            default:
                return newPoints;
        }
    }

    public Shape build_rotation_simple_by_all_shapes(final Shape p, int param) {
        final RotateTransition rotateTransition = RotateTransitionBuilder.create()
                .node(p)
                .duration(Duration.seconds(30))
                .fromAngle(0)
                .toAngle(720)
                .cycleCount(Timeline.INDEFINITE)
                .autoReverse(true)
                .build();
        rotateTransition.play();
        MultiTouchImageView m = new MultiTouchImageView(p);
        return m.getImageView();
    }

    public class MultiTouchImageView extends StackPane {

        private Shape imageView;
        private double lastX, lastY, startScale, startRotate;

        public Shape getImageView() {
            return imageView;
        }

        public MultiTouchImageView(Shape img) {
            setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(0, 0, 0, 0.5), 8, 0, 0, 2));

            imageView = img;
            imageView.setSmooth(true);
            getChildren().add(imageView);

            setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {

                    lastX = event.getX();
                    lastY = event.getY();
                    toFront();
                    postView.toFront();
                }
            });
            setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {

                    double layoutX = getLayoutX() + (event.getX() - lastX);
                    double layoutY = getLayoutY() + (event.getY() - lastY);

                    if ((layoutX >= 0) && (layoutX <= (getParent().getLayoutBounds().getWidth()))) {
                        setLayoutX(layoutX);
                    }

                    if ((layoutY >= 0) && (layoutY <= (getParent().getLayoutBounds().getHeight()))) {
                        setLayoutY(layoutY);
                    }

                    if ((getLayoutX() + (event.getX() - lastX) <= 0)) {
                        setLayoutX(0);
                    }
                }
            });
            addEventHandler(ZoomEvent.ZOOM_STARTED, new EventHandler<ZoomEvent>() {
                @Override
                public void handle(ZoomEvent event) {
                    startScale = getScaleX();
                }
            });
            addEventHandler(ZoomEvent.ZOOM, new EventHandler<ZoomEvent>() {
                @Override
                public void handle(ZoomEvent event) {
                    setScaleX(event.getTotalZoomFactor());
                    setScaleY(event.getTotalZoomFactor());
                }
            });
            addEventHandler(RotateEvent.ROTATION_STARTED, new EventHandler<RotateEvent>() {
                @Override
                public void handle(RotateEvent event) {
                    startRotate = getRotate();
                }
            });
            addEventHandler(RotateEvent.ROTATE, new EventHandler<RotateEvent>() {
                @Override
                public void handle(RotateEvent event) {
                    setRotate(event.getTotalAngle());
                }
            });

        }
    }
}
