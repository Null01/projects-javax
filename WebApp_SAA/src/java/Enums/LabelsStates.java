/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enums;

/**
 *
 * @author Duran
 */
public enum LabelsStates {

    ESTADO_ADOPTADO("ADOPTADO"),
    ESTADO_EN_PROCESO_ADOPCION("EN ADOPCION"),
    ESTADO_SIN_ADOPTAR("POR ADOPTAR");

    private String estado;

    private LabelsStates(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
