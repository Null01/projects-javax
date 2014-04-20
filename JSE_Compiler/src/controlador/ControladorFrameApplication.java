/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.File;

/**
 *
 * @author duran
 * @version 1.0
 */
public class ControladorFrameApplication {

    private static File file;

    public ControladorFrameApplication() {
        file = null;
    }

    public static void setFile(File file) {
        ControladorFrameApplication.file = file;
    }

    public static File getFile() {
        return file;
    }

    public boolean existFileUsed() {
        return (file != null);
    }

}
