package session;

import Modelo.Usuario;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * .
 * @author andresfelipegarciaduran
 */
public class InterpreterDB {

    private static final String name_file_users_id = "users_id.properties";

    public static InterpreterDB onlyThread = new InterpreterDB();

    private Properties loadFile(String name_file) throws Exception {
        Properties properties = new Properties();
        try {
            File file = new File(name_file);
            InputStream is = new FileInputStream(file);
            properties.load(is);
        } catch (Exception e) {
            try {
                InputStream is
                        = getClass().getClassLoader().getResourceAsStream(name_file);
                properties.load(is);
            } catch (Exception ex) {
                throw new Exception(ex);
            }
        }
        return properties;
    }

    
    public void writeInFileUsersId(String id, String pass) throws Exception {
        try {
            Properties loadFile = loadFile(name_file_users_id);
            if (!loadFile.containsKey(id)) {
                throw new Exception("ID=" + id + " No se encuentra registrado.");
            }
            loadFile.setProperty(id, pass);
            File f = new File(name_file_users_id);
            OutputStream out = new FileOutputStream(f);
            loadFile.store(out, null);
        } catch (FileNotFoundException ex) {
            throw new Exception(ex);
        }
    }

    public void readInFileUsersId(String id, String passEncry, String pass) throws Exception {
        try {
            Properties loadFile = loadFile(name_file_users_id);
            if (!loadFile.containsKey(id)) {
                throw new Exception("ID=" + id + " No se encuentra registrado.");
            }
            String property = loadFile.getProperty(id);
            if (property.compareTo(passEncry) != 0) {
                throw new Exception("PASS=" + pass + " No coincide con el registro.");
            }
        } catch (FileNotFoundException ex) {
            throw new Exception(ex);
        }
    }

    public boolean existUser(String id) throws Exception {
        try {
            Properties loadFile = loadFile(name_file_users_id);
            if (!loadFile.containsKey(id)) {
                return false;
            }
        } catch (FileNotFoundException ex) {
            return false;
        }
        return true;
    }

    public boolean userIsAdmin(String id) throws Exception {
        try {
            Properties loadFile = loadFile(name_file_users_id);
            if (!loadFile.containsKey(id)) {
                return false;
            }
            String param = (String) loadFile.get(ITipoUsuario.ADMIN);
            if (param.compareTo(id) == 0) {
                return true;
            }
        } catch (FileNotFoundException ex) {
            return false;
        }
        return false;
    }

    public void writeFileDataUserId(String first_name, String last_name, String email, String passwordEncrypt) throws Exception {

        try {
            Properties loadFileId = loadFile(name_file_users_id);

            loadFileId.setProperty(email, passwordEncrypt);
            File f = new File(name_file_users_id);
            OutputStream out = new FileOutputStream(f);
            loadFileId.store(out, null);

            String nameFile = email + ".properties";
            Properties loadFile = new Properties();
            loadFile.setProperty("id", email);
            loadFile.setProperty("firstName", first_name);
            loadFile.setProperty("lastName", last_name);
            File f1 = new File(nameFile);
            OutputStream out1 = new FileOutputStream(f1);
            loadFile.store(out1, null);
        } catch (FileNotFoundException ex) {
            throw new Exception(ex);
        }
    }

    public Object readFileDataUserId(String email) throws Exception {
        Properties loadFile = loadFile(email + ".properties");
        boolean userIsAdmin = userIsAdmin(email);
        String type = (userIsAdmin) ? ITipoUsuario.ADMIN : ITipoUsuario.SIMPLE;
        return new Usuario(loadFile.getProperty("firstName"), loadFile.getProperty("lastName"), loadFile.getProperty("id"), type);
    }
}
