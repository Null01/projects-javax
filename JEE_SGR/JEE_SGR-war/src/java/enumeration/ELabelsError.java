package enumeration;

/**
 *
 * @author duran
 */
public enum ELabelsError {

    ERROR_AUTENTICACION_USUARIO_BLOQUEADO("USUARIO BLOQUEADO."),
    ERROR_AUTENTICACION("ERROR AUTENTICACION. POR FAVOR REVISE SUS DATOS."),
    ERROR_PRESTAMO_SELECCION_VACIA("SELECCIONE AL MENOS UN ELEMENTO."),
    ERROR_PRESTAMO_MAX_ARTICULOS("UDTED YA POSEE PRESTAMOS PENDIENTES."),
    ERROR_PRESTAMO_CAMPOS_INCORRECTOS("DATOS INGRESADOS - INCORRECTOS.");

    private ELabelsError(String string) {
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
