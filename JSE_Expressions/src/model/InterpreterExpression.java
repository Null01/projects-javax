/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author duran
 *
 */
public class InterpreterExpression {

    private class Node {

        public String value;
        public Point point;
        public Color color;
        private Node parent;
        private Node childrenA;
        private Node childrenB;

        public Node(String value, Point point, Color color) {
            this.value = value;
            this.point = point;
            this.color = color;
            this.childrenA = null;
            this.childrenB = null;
            this.parent = null;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public Node getChildrenA() {
            return childrenA;
        }

        public Node getChildrenB() {
            return childrenB;
        }

        public void setChildrenA(Node childrenA) {
            this.childrenA = childrenA;
        }

        public void setChildrenB(Node childrenB) {
            this.childrenB = childrenB;
        }

        public String getValue() {
            return value;
        }

        public Point getPoint() {
            return point;
        }

        public Color getColor() {
            return color;
        }

        @Override
        public String toString() {
            return "[value=" + this.value + "]";
        }
    }
    private int STEP_X = 50;
    private int STEP_X_X = 20;
    private int STEP_Y = 70;

    /**
     * Contruye el arbol de las expresiones entrantes
     *
     * @param exp conjunto de expresiones en prefijo
     * @return retorna un arbol, equivalente a las expresiones entrantes
     */
    private Node analyzeExpressionAsTree(ArrayList<Character> exp) {
        int move_x = STEP_X * 8;
        int move_y = STEP_Y;
        boolean backtring = false;
        Point temp;
        Node root = new Node(exp.get(exp.size() - 1) + "", new Point(move_x, move_y), Color.RED);
        for (int i = exp.size() - 2; i >= 0; i--) {
            if (isValid(exp.get(i))) {
                // expression
                if (root.getChildrenA() == null) {
                    temp = root.getPoint();
                    root.setChildrenA(new Node(exp.get(i) + "", new Point(temp.x - STEP_X_X, temp.y + STEP_Y), Color.BLUE));
                    root.getChildrenA().setParent(root);
                } else {
                    if (root.getChildrenB() == null) {
                        temp = root.getPoint();
                        root.setChildrenB(new Node(exp.get(i) + "", new Point(temp.x + STEP_X_X, temp.y + STEP_Y), Color.BLUE));
                        root.getChildrenB().setParent(root);
                    } else {
                        //apply backtring
                        root = root.getParent();
                        while (root.getChildrenA() != null && root.getChildrenB() != null) {
                            root = root.getParent();
                        }
                        ++i;
                    }
                }
            } else {
                // symbol arithmetic
                if (root.getChildrenA() == null) {
                    temp = root.getPoint();
                    root.setChildrenA(new Node(exp.get(i) + "", new Point(temp.x - STEP_X, temp.y + STEP_Y), Color.RED));
                    root.getChildrenA().setParent(root);
                    root = root.getChildrenA();
                } else {
                    if (root.getChildrenB() == null) {
                        temp = root.getPoint();
                        root.setChildrenB(new Node(exp.get(i) + "", new Point(temp.x + STEP_X, temp.y + STEP_Y), Color.RED));
                        root.getChildrenB().setParent(root);
                        root = root.getChildrenB();
                    } else {
                        // apply backtring
                        root = root.getParent();
                        ++i;
                    }
                }
            }
        }

        // apply backtring until node is root
        while (root.getParent() != null) {
            root = root.getParent();
        }
        return root;
    }

    /**
     * Realiza el llamado de las tareas contruccion del arbol de expresiones y
     * representacion grafica del arbol
     *
     * @param exp conjunto de expresiones
     * @param drawDisplay objeto guia, usado para pintar
     */
    public void drawExpressionPostfix(ArrayList<Character> exp, Paint drawDisplay) {
        if (drawDisplay != null) {
            Node tree = analyzeExpressionAsTree(exp);
            drawTreePreOrden(tree, drawDisplay);
        }
    }

    /**
     * Recorrido en preorden, del arbol que contiene la expresiones, para ser
     * dibujas en el panel.
     *
     * @param node nodo sobre el cual recae las
     * @param paint objeto al que se le delega la funcion de pintar.
     */
    private void drawTreePreOrden(Node node, Paint paint) {
        if (node != null) {
            paint.paintVertex(node.getPoint(), node.getValue(), node.getColor());
            if (node.getChildrenA() != null) {
                paint.paintLine(node.getPoint(), node.getChildrenA().getPoint());
            }
            if (node.getChildrenB() != null) {
                paint.paintLine(node.getPoint(), node.getChildrenB().getPoint());
            }
            drawTreePreOrden(node.getChildrenA(), paint);
            drawTreePreOrden(node.getChildrenB(), paint);
        }
    }

    /**
     * Determina si es valido la expresion entrante entre un symbolo y lexema
     *
     * @param character lexema entrante
     * @return retorna verdadero si el lexema es una expresion.
     */
    private boolean isValid(Character character) {
        try {
            if (Character.isDigit(character) || Character.isAlphabetic(character)) {
                return true;
            }
            throw new Exception();
        } catch (Exception e) {
            return false;
        }
    }
}
