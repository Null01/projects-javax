package edu.lab.security;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import javax.servlet.ServletContext;

/**
 *
 * @author andresfelipegarciaduran
 */
public class MonitorLogs {

    public static MonitorLogs onlyChannel = new MonitorLogs();

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

    public void writeLogUser(ServletContext servletContext, String[] data) throws Exception {
        String name_file = servletContext.getRealPath("/") + "log_app.txt";
        File file = new File(name_file);
        try {
            try (FileWriter fw = new FileWriter(file, true)) {
                fw.append(dateFormat.format(new Date()) + " " + Arrays.toString(data) + "\n");
            }
        } catch (IOException ex) {
            throw new Exception(ex);
        }
    }
}
