/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

/**
 *
 * @author duran
 */
public class AlgorithmShuntingYard {

    private ArrayList<Character> outputPostfix;
    private ArrayList<Character> outputPrefix;
    private ArrayList<Character> input;

    public AlgorithmShuntingYard(String expression) {
        outputPostfix = new ArrayList<>();
        init(expression);
    }

    // algoritmo de shunting yard / patio de clasificacion // trabaja expresiones a posfijo
    public void parseExpressionPostfix() {
        ArrayList<Character> out = new ArrayList<>();
        Stack<Character> op = new Stack<>();
        ArrayList<Character> stack = new ArrayList<>(input);
        while (!stack.isEmpty()) {
            if (isdigit(stack.get(0))) {
                out.add(stack.get(0));
            } else {
                if (stack.get(0) == '+' || stack.get(0) == '-' || stack.get(0) == '*'
                        || stack.get(0) == '/' || stack.get(0) == '^') {
                    while (!op.isEmpty() && precedencia(stack.get(0), op.lastElement())) {
                        out.add(op.lastElement());
                        op.pop();
                    }
                    op.push(stack.get(0));
                } else {

                    if (stack.get(0) == '(') {
                        op.push(stack.get(0));
                    } else {
                        while (!op.empty() && op.lastElement() != '(') {
                            out.add(op.lastElement());
                            op.pop();
                        }
                        op.pop();
                    }
                }
            }
            stack.remove(0);
        }

        outputPostfix.addAll(out);
        Collections.reverse(op);
        outputPostfix.addAll(op);
    }

    public void parseExpressionPrefix() {
        String infijo = "(";
        for (int i = 0; i < input.size(); i++) {
            infijo += input.get(i) + "";
        }
        int longitud = infijo.length();
        Pila pilaDef = new Pila(longitud);
        Pila PilaTemp = new Pila(longitud);
        PilaTemp.push(')'); // Agregamos a la pila temporal un '('
        for (int i = longitud - 1; i > -1; i--) {
            char caracter = infijo.charAt(i);
            switch (caracter) {
                case ')':
                    PilaTemp.push(caracter);
                    break;
                case '+':
                case '-':
                case '^':
                case '*':
                case '/':
                    while (Jerarquia(caracter) > Jerarquia(PilaTemp.nextPop())) {
                        pilaDef.push(PilaTemp.pop());
                    }
                    PilaTemp.push(caracter);
                    break;
                case '(':
                    while (PilaTemp.nextPop() != ')') {
                        pilaDef.push(PilaTemp.pop());
                    }
                    PilaTemp.pop();
                    break;
                default:
                    pilaDef.push(caracter);
            }
        }
        outputPrefix = new ArrayList<>();
        for (int i = 0; i < pilaDef.items.length; i++) {
            if (pilaDef.items[i] != '\0') {
                outputPrefix.add(pilaDef.items[i]);
            } else {
                break;
            }
        }
        Collections.reverse(outputPrefix);
    }

    private boolean isdigit(Character get) {
        try {
            Integer.parseInt((char) get + "");
            return true;
        } catch (Exception e) {
            //System.out.println(e.getMessage() + "  " + Arrays.toString(e.getStackTrace()));
            if (Character.isAlphabetic(get)) {
                return true;
            }
            return false;
        } finally {
            // by other finally
        }
    }

    private void init(String expression) {
        input = new ArrayList<>();
        for (Character c : expression.toCharArray()) {
            input.add(c);
        }
    }

    private boolean precedencia(char a, char b) {
        if (a == '(' || b == '(') {
            return false;
        }
        if ((a == '*' || a == '/') && (b == '+' || b == '-')) {
            return false;
        }
        if ((a == '^' || b == '^')) {
            return false;
        }
        return true;
    }

    public int Jerarquia(char elemento) {
        int res = 0;
        switch (elemento) {
            case ')':
                res = 5;
                break;
            case '^':
                res = 4;
                break;
            case '*':
            case '/':
                res = 3;
                break;
            case '+':
            case '-':
                res = 2;
                break;
            case '(':
                res = 1;
                break;
        }
        return res;
    }

    class Pila {

        int tamaño;
        char[] items;
        int i;

        Pila(int tamaño) {
            this.tamaño = tamaño;
            this.items = new char[tamaño];
            this.i = 0;
        }

        public boolean push(char item) {
            if (i < tamaño) {
                items[i++] = item;
                return true;
            }
            return false;
        }

        public char pop() {
            if (i <= 0) {
                return 0;
            }
            return items[--i];
        }

        public char nextPop() {
            if (i <= 0) {
                return 0;
            }
            return items[i - 1];

        }
    }

    public ArrayList<Character> getOutputPostfix() {
        return outputPostfix;
    }

    public ArrayList<Character> getOutputPrefix() {
        return outputPrefix;
    }

    public ArrayList<Character> getInput() {
        return input;
    }
}
