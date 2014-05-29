/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.awt.event.KeyEvent;
import static java.lang.Thread.sleep;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.swing.JTextArea;
import javax.swing.text.Document;
import javax.swing.text.Element;

/**
 *
 * @author duran
 * @version 1.0
 */
public class InterpreterExecute extends Thread {

    private JTextArea display;
    private List<String> listInterpreterExecute;
    private Map<String, String> dataExecute;
    private Analyze analyze;

    private InterpreterExecute() {
    }

    public InterpreterExecute(JTextArea display, List<String> listInterpreterExecute, Map<String, String> dataExecute) {
        this.display = display;
        this.listInterpreterExecute = listInterpreterExecute;
        this.dataExecute = dataExecute;
        analyze = new Analyze();
        analyze.initialize();
    }

    @Override
    @SuppressWarnings("empty-statement")
    public void run() {

        while (this.isAlive()) {
            try {
                if (!listInterpreterExecute.isEmpty()) {

                    String message = listInterpreterExecute.get(0);
                    String tokens[] = message.split("#");
                    String express = tokens[0];
                    String printConsole = "";
                    boolean ready = false;

                    if (express.compareTo(EExpress.ESCRITURA.getExpress()) == 0) {
                        for (int i = 1; i < tokens.length; i++) {
                            if (tokens[i].contains("@")) {
                                String values[] = tokens[i].split("@");
                                if (values.length == 3) {
                                    printConsole += (values[2].replace("\"", ""));
                                }
                                if (values.length == 2) {
                                    printConsole += dataExecute.get(values[0]).split("@")[1];
                                }
                            } else {
                                printConsole += (tokens[i].replace("\"", ""));
                            }
                        }
                        display.append(printConsole + "\n");
                    }

                    if (express.compareTo(EExpress.LECTURA.getExpress()) == 0) {
                        if (!ready) {
                            ready = !ready;
                            this.suspend();
                        }
                    }

                    if (ready) {
                        sleep(200); /* Prioridad de procesos... */

                    }
                    listInterpreterExecute.remove(0);
                } else {
                    this.interrupt();
                }

            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        super.run(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public synchronized void start() {
        display.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        resume(); /* REINICIA EL PROCESO @Deprecated */

                        Document document = display.getDocument();
                        Element rootElem = document.getDefaultRootElement();
                        int numLines = rootElem.getElementCount();
                        Element lineElem = rootElem.getElement(numLines - 1);
                        int lineStart = lineElem.getStartOffset();
                        int lineEnd = lineElem.getEndOffset();
                        String lineText = document.getText(lineStart, lineEnd - lineStart);

                        String valuesExecute[] = listInterpreterExecute.get(0).split("#");
                        String values[] = lineText.split(" ");

                        if (valuesExecute.length - 1 != values.length) {
                            display.setText("ERROR - EL NUMERO DE PARAMETROS DE ENTRADA NO COINCIDEN CON LA LOGICA DEL PROGRAMA.");
                            stop();
                        } else {
                            String vars[];
                            for (int i = 0; i < valuesExecute.length - 1; i++) {
                                vars = valuesExecute[i + 1].split("@");
                                if (vars[1].compareTo(EExpress.ENTERO.getExpress()) == 0) {
                                    if (!analyze.isEntero(values[i])) {
                                        display.setText("ERROR - EL T IPO DE DATO NO CORRESPONDE A LA LOGICA DEL PROGRAMA. [ENTERO]");
                                        stop();
                                    } else {
                                        if (dataExecute.get(vars[0]).contains("@")) {
                                            dataExecute.put(vars[0], dataExecute.get(vars[0]).split("@")[0] + "@" + values[i]);
                                        } else {
                                            dataExecute.put(vars[0], dataExecute.get(vars[0]) + "@" + values[i]);
                                        }

                                    }
                                }
                                if (vars[1].compareTo(EExpress.DECIMAL.getExpress()) == 0) {
                                    if (!analyze.isDecimal(values[i])) {
                                        display.setText("ERROR - EL TIPO DE DATO NO CORRESPONDE A LA LOGICA DEL PROGRAMA. [DECIMAL]");
                                        stop();
                                    } else {
                                        if (dataExecute.get(vars[0]).contains("@")) {
                                            dataExecute.put(vars[0], dataExecute.get(vars[0]).split("@")[0] + "@" + values[i]);
                                        } else {
                                            dataExecute.put(vars[0], dataExecute.get(vars[0]) + "@" + values[i]);
                                        }
                                    }
                                }
                                if (vars[1].compareTo(EExpress.CADENA.getExpress()) == 0) {
                                    if (dataExecute.get(vars[0]).contains("@")) {
                                        dataExecute.put(vars[0], dataExecute.get(vars[0]).split("@")[0] + "@" + values[i]);
                                    } else {
                                        dataExecute.put(vars[0], dataExecute.get(vars[0]) + "@" + values[i]);
                                    }
                                }
                            }
                        }

                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                }
            }
        });
        super.start();
    }

}
