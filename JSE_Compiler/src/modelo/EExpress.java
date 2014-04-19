package modelo;

/**
 *
 * @author AGarcia
 */
public enum EExpress {

    PRINCIPAL("program"),
    DEFINICION("data_type"),
    INICIO("begin"),
    FIN("end"),
    ENTERO("int"),
    CADENA("string"),
    DECIMAL("float"),
    SI("if"),
    SI_NO("else"),
    FIN_SI("end_if"),
    ENTONCES("then"),
    LECTURA("read"),
    ESCRITURA("write"),
    MIENTRAS("while"),
    FIN_MIENTRAS("end_while");

    private final String express;

    private EExpress(String express) {
        this.express = express;
    }

    public String getExpress() {
        return express;
    }

}
