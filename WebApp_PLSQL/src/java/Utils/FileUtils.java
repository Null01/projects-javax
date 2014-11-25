package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.Part;

/**
 *
 * @author andresfelipegarciaduran
 */
public class FileUtils {

    public String getFileName(Part file) {
        String fileName = null;
        final String partHeader = file.getHeader("content-disposition");
        for (String content : partHeader.split(";")) {
            if (content.trim().startsWith("filename")) {
                fileName = content.substring(content.indexOf('=') + 1)
                        .trim().replace("\"", "");
            }
        }
        return fileName;
    }

    public Object[] createFile(Part file, String path) {
        File outputFile = new File(path);
        InputStream inputStream = null;

        OutputStream outputStream = null;
        byte[] buffer = null;
        try {
            inputStream = file.getInputStream();
            outputStream = new FileOutputStream(outputFile);
            buffer = new byte[1024];
            int bytesRead = 0;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            if (outputStream != null) {
                outputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Object[]{outputFile, buffer};
    }

    public File createFile(InputStream binaryStrea, String pathComplete) {
        File fileTemp = new File(pathComplete);
        System.out.println("fileTemp: " + fileTemp);
        OutputStream outputStream;
        try {
            outputStream = new FileOutputStream(fileTemp);
            byte[] buffer = new byte[1024];
            int bytesRead = 0;
            while ((bytesRead = binaryStrea.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            if (outputStream != null) {
                outputStream.close();
            }
            if (binaryStrea != null) {
                binaryStrea.close();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fileTemp;
    }

}
