/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enumeration;

/**
 *
 * @author duran
 */
public enum ELabels {

    CREATE("CREACION "),
    DELETE("ELIMINACION "),
    UPDATE("ACTUALIZACION "),
    READ("CONSULTA "),
    SESSION("SESSION "),
    LOGIN("LOGIN "),
    LOGOUT("LOGOUT "),
    OPEN("ABRIR "),
    CLOSE("CERRAR ");

    private ELabels(String string) {
        this.string = string;
    }

    private String string;

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

}
