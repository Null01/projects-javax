/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.JEditorPane;
import javax.swing.JPanel;

/**
 *
 * @author duran
 */
public class Paint {

    private JPanel display;
    private int RADIO_OVAL = 23;

    public Paint(JPanel display) {
        this.display = display;
    }

    public Paint(JEditorPane editor) {
    }

    /**
     * Dibuja una linea entre dos puntos
     *
     * @param pointX nodo padre, sobre el panel
     * @param pointY node hijo, sobre el panel
     */
    public void paintLine(Point pointX, Point pointY) {
        Graphics2D graphics2D = (Graphics2D) display.getGraphics();
        graphics2D.setColor(Color.BLACK);
        graphics2D.setStroke(new BasicStroke(1.5f));
        graphics2D.drawLine(pointX.x + 5, pointX.y + 5, pointY.x + 7, pointY.y - 18);
    }

    /**
     * Dibuja un puntos sobre el punto, representando un nodo expresion
     *
     * @param point punto sobre el panel
     * @param text texto anexto en el punto.
     * @param color color del ovalo que representa el punto.
     */
    public void paintVertex(Point point, String text, Color color) {
        Graphics2D graphics2D = (Graphics2D) display.getGraphics();

        graphics2D.setColor(color);
        graphics2D.setStroke(new BasicStroke(1.5f));
        graphics2D.drawOval(point.x - 7, point.y - 18, RADIO_OVAL, RADIO_OVAL);
        graphics2D.setColor(Color.BLACK);
        graphics2D.setFont(new Font("consolas", Font.BOLD, 20));
        graphics2D.drawString(text, point.x, point.y);
    }

    public JPanel getDisplay() {
        return display;
    }

    /**
     * Limpiar panel.
     */
    public void clearDisplay() {
        Graphics2D graphics2D = (Graphics2D) display.getGraphics();
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(0, 0, display.getWidth(), display.getHeight());
    }
}
