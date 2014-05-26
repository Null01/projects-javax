package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 *
 * @author AGarcia
 */
public class Analyze implements IAutomaton {

    protected static BufferedReader bufferedReader;
    protected BufferedWriter bufferedWriter;
    protected int numberlines;

    protected TreeMap<String, String> map;
    private ArrayList<String> operators;
    private ArrayList<String> express;

    public void initialize() {
        operators = new ArrayList<>();
        for (EOperators e : EOperators.values()) {
            operators.add(e.getOperator());
        }

        express = new ArrayList<>();
        for (EExpress e : EExpress.values()) {
            express.add(e.getExpress());
        }

        map = new TreeMap<>();
    }

    public ArrayList<String> getOperators() {
        return operators;
    }

    public ArrayList<String> getExpress() {
        return express;
    }

    public TreeMap<String, String> getMap() {
        return map;
    }

    @Override
    public boolean isEntero(String string) {
        try {
            String operator = EOperators.MAS.getOperator();
            if (string.startsWith(operator)) {
                return false;
            }
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    @Override
    public boolean isCadena(String string) {
        String dc = EOperators.COMILLA_DOBLE.getOperator();
        boolean first = dc.compareTo("" + string.charAt(0)) == 0
                || dc.compareTo("" + string.charAt(string.length() - 1)) == 0;
        return first;
    }

    @Override
    public boolean isVariable(String string, boolean doble_comilla) {
        boolean isAlphabetic = Character.isAlphabetic(string.charAt(0));
        if (!isAlphabetic && !doble_comilla) {
            return false;
        }

        if (!doble_comilla) {
            if (!isExpress(string)) {
                for (char c : string.toCharArray()) {
                    if (!Character.isAlphabetic(c) && c != '_' && !Character.isDigit(c)) {
                        return false;
                    }
                }
                return true;
            }
        } else {
            char[] cs = string.trim().toCharArray();
            if (cs != null) {
                return (cs[0] == '\"' && cs[cs.length - 1] == '\"');
            }
            return false;
        }
        return true;
    }

    @Override
    public boolean isDecimal(String string) {
        try {
            String operator = EOperators.MAS.getOperator();
            if (string.startsWith(operator)) {
                return false;
            }
            Double.parseDouble(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public boolean isExpress(String string) {
        boolean contains = getExpress().contains(string);
        return contains;
    }

    @Override
    public boolean isSymbol(String string) {
        boolean contains = getOperators().contains(string);
        return contains;
    }

    @Override
    public boolean isTypeData(String string) {
        boolean b = string.compareTo(EExpress.ENTERO.getExpress()) == 0
                || string.compareTo(EExpress.DECIMAL.getExpress()) == 0
                || string.compareTo(EExpress.CADENA.getExpress()) == 0;
        return b;
    }

    @Override
    public boolean isSymbolLogic(String string) {
        boolean b = string.compareTo(EOperators.MAYOR.getOperator()) == 0
                || string.compareTo(EOperators.MAYOR_IGUAL.getOperator()) == 0
                || string.compareTo(EOperators.MENOR.getOperator()) == 0
                || string.compareTo(EOperators.MENOR_IGUAL.getOperator()) == 0
                || string.compareTo(EOperators.IGUAL_IGUAL.getOperator()) == 0
                || string.compareTo(EOperators.MENOR_IGUAL.getOperator()) == 0
                || string.compareTo(EOperators.DIFERENTE_IGUAL.getOperator()) == 0
                || string.compareTo(EOperators.LOGIC_AND.getOperator()) == 0
                || string.compareTo(EOperators.LOGIC_OR.getOperator()) == 0;
        return b;
    }

}
