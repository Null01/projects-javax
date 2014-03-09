/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.swing.JPanel;
import javax.swing.text.JTextComponent;
import model.AlgorithmShuntingYard;
import model.InterpreterExpression;
import model.Paint;

/**
 *
 * @author duran
 * @version 1.0
 */
public class PaintController {

    private JPanel display;

    /**
     *
     * @param display
     */
    public PaintController(JPanel display) {
        this.display = display;
    }

    /**
     * Controlador sobre el cual recae las acciones que se producen en el Frame.
     *
     * @param fieldExpression campo expresion de entrada
     * @param fieldPrefix campo expresion prefija de salida
     * @param fieldPostfix campo expresion posfija de salida
     */
    public void findExpressions(JTextComponent fieldExpression, JTextComponent fieldPrefix, JTextComponent fieldPostfix) {

        String string = fieldExpression.getText().toString();
        AlgorithmShuntingYard algorithm = new AlgorithmShuntingYard(string);
        
        algorithm.parseExpressionPostfix();
        fieldPostfix.setText(algorithm.getOutputPostfix().toString());
        algorithm.parseExpressionPrefix();
        fieldPrefix.setText(algorithm.getOutputPrefix().toString());

        Paint draw = new Paint(display);
        draw.clearDisplay();
        InterpreterExpression expression = new InterpreterExpression();
        expression.drawExpressionPostfix(algorithm.getOutputPostfix(), draw);
    }
}
