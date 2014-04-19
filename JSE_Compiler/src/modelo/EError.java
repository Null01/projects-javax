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
public enum EError {

    DEF_EMPTY_OUTOFBOUNDS("TEXTO VACIO O PALABRAS CLAVES MAL_DEFINIDAS"),
    DEF_VAR_ASIG_INT("ASIGNACION INCORRECTA DEL TIPO DE DATO. EJEMPLO: int: a = 2, b;"),
    DEF_VAR_ASIG_FLOAT("ASIGNACION INCORRECTA DEL TIPO DE DATO. EJEMPLO: float: b=-2.0, a;"),
    DEF_VAR_ASIG_STRING("ASIGNACION INCORRECTA DEL TIPO DE DATO. EJEMPLO: string: a , b = \"hello\";"),
    DEF_PARAM_PRINCIPAL("NO PRESENTA LA SINTAXIS CORRECTA. EJEMPLO: program: Nombre_Programa"),
    DEF_PARAM_DEFINICION("NO PRESENTA LA SINTAXIS CORRECTA. EJEMPLO:  data_type"),
    DEF_N_PARAM_IN("NUMERO DE PARAMETROS INCONSISTENTE. EJEMPLO:\n program: Nombre_Programa\n data_type\n begin\n end\n");

    private final String message;

    private EError(String message) {
        this.message = message;

    }

    public String getMessage() {
        return message;
    }
}
