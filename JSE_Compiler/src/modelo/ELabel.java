/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author AGarcia
 */
public enum ELabel {

    CAUSA("  CAUSA:"),
    MOTIVO("  MOTIVO:"),
    ERROR_LINEA("ERROR:"),
    SEPAR_DPUNTO(" : "),
    SEPAR_A(" ["),
    SEPAR_B(" ]");

    private final String message;

    private ELabel(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
