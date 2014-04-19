package vista;

import modelo.AnalyzeSyntactic;

/**
 *
 * @author AGarcia
 */
public class Main {

    public static void main(String[] args) {
        AnalyzeSyntactic as = new AnalyzeSyntactic("input");
        boolean b = as.analyzeText();
        if (!b) {
            for (String s : as.getStackError()) {
                System.err.println(s);
            }
        } else {
            System.out.println("-Ok-");
        }
    }

}
