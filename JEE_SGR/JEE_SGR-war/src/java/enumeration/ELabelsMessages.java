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
public enum ELabelsMessages {

    SUCCESSFULL_LOGIN("Bienvenido.");

    private String string;

    private ELabelsMessages(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

}
