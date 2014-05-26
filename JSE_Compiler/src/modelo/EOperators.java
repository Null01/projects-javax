package modelo;

/**
 *
 * @author AGarcia
 */
public enum EOperators {

    DIFERENTE_IGUAL("!="),
    IGUAL_IGUAL("=="),
    MAYOR_IGUAL(">="),
    MENOR_IGUAL("<="),
    COMILLA_DOBLE("\""),
    IGUAL("="),
    MAS("+"),
    MENOS("-"),
    MULTIPLICACION("*"),
    DIVISION("/"),
    MODULO("%"),
    MENOR("<"),
    MAYOR(">"),
    PUNTO_COMA(";"),
    PUNTO_PUNTO(":"),
    PAR_A("("),
    PAR_B(")"),
    COMA(","),
    LOGIC_AND("&"),
    LOGIC_OR("|");

    private final String operator;

    private EOperators(String op) {
        this.operator = op;
    }

    public String getOperator() {
        return operator;
    }

}
