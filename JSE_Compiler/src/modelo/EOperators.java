package modelo;

/**
 *
 * @author AGarcia
 */
public enum EOperators {

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
    COMA(",");

    private final String operator;

    private EOperators(String op) {
        this.operator = op;
    }

    public String getOperator() {
        return operator;
    }

}
