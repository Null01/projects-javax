package Controller;

import Beans.BeanHome;
import Utils.ServletUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;

/**
 *
 * @author andresfelipegarciaduran
 */
public class FileController implements Serializable {

    public static void downloadFile(String fileName, File outputFile) {

        String relativePath = ServletUtils.getServletContext().getRealPath("");
        System.out.println("relativePath = " + relativePath);

        // obtains ServletContext
        ServletContext context = ServletUtils.getServletContext();

        // gets MIME type of the file
        String mimeType = context.getMimeType(fileName);
        if (mimeType == null) {
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }
        System.out.println("MIME type: " + mimeType);

        // modifies response
        ServletUtils.getHttpServletResponse().setContentType(mimeType);
        ServletUtils.getHttpServletResponse().setContentLength((int) outputFile.length());

        // forces download
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", outputFile.getName());
        ServletUtils.getHttpServletResponse().setHeader(headerKey, headerValue);

        // obtains response's output stream
        OutputStream outStream;
        try {
            FileInputStream inStream = new FileInputStream(outputFile);
            outStream = ServletUtils.getHttpServletResponse().getOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead = -1;

            while ((bytesRead = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }

            inStream.close();
            outStream.close();
        } catch (IOException ex) {
            Logger.getLogger(BeanHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
