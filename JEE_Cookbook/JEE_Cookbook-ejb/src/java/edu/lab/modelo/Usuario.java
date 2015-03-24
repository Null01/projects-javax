/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.lab.modelo;

import java.io.Serializable;

/**
 *
 * @author EstebanC02
 */
public class Usuario implements Serializable {

    private String Nombre;
    private String Apellido;
    private String Correo;
    private String Tipo;

    public Usuario(String Nombre, String Apellido, String Correo, String Tipo) {
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Correo = Correo;
        this.Tipo = Tipo;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    @Override
    public String toString() {
        return Nombre + " " + Apellido + " " + Correo + " " + Tipo;
    }

}
