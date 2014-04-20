/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

/**
 *
 * @author duran
 */
public enum EnumLabels {

    ERROR_LECTURA_ARCHIVO("El archivo seleccionado no cumple el formato."),
    ERROR_CREANDO_ARCHIVO_YA_EXISTENTE("Por favor. Seleccione o nombre el archivo de forma diferente"),
    INFO_GUARDAR_ARCHIVO_ACTUAL("Antes de crear uno nuevo. Especifique donde desea guardar el archivo actual"),
    ERROR_ARCHIVO_NO_EXISTE("El arhivo no existe"),
    INFO_COMPILE_FAILURE("Compilacion denegada."),
    INFO_NUMERO_LINEAS("Numero de lineas: "),
    INFO_NUMERO_PALABRAS_RESERVADA("Palabras reservadas: "),
    INFO_NUMERO_PALABRAS_TIPODATO("Palabras tipo de dato: "),
    INFO_NUMERO_PALABRAS_OPERADORES("Palabras tipo operadores: "),
    INFO_NUMERO_PALABRAS_IDENTIFICADORES("Palabras tipo identificadores: "),
    INFO_COMPILE_SUCCESSFUL("Compilacion exitosa.");

    private EnumLabels(String string) {
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
