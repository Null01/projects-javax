/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author duran
 * @version 1.0
 */
public class InterpreterExecute extends Thread {

    private JTextArea display;
    private List<String> listPhrase;

    private InterpreterExecute() {
    }

    public InterpreterExecute(JTextArea display, List<String> listPhrase) {
        this.display = display;
        this.listPhrase = listPhrase;
    }

    @Override
    public void interrupt() {
        super.interrupt(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @SuppressWarnings("empty-statement")
    public void run() {
        int index = 0;
        while (listPhrase.get(++index).compareTo(EExpress.INICIO.getExpress()) == 0);
        
        while (this.isAlive()) {
            try {
                this.sleep(3000);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }
        super.run(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public synchronized void start() {
        super.start();
    }

}
