package security;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author andresfelipegarciaduran
 */
public class SecurityEncrypt {
    

    public String encryptWithMD5(String clave) throws NoSuchAlgorithmException {
        String md5Clave = MD5(MD5(MD5(clave)));
        return md5Clave;
    }

    /**
     * Algoritmo de encriptamiento - tipo hash
     *
     * @param string llave de encriptacion
     * @return informacion encriptada
     * @throws NoSuchAlgorithmException
     */
    private String MD5(String string) throws NoSuchAlgorithmException {
        String encryptMD5 = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(string.getBytes("UTF-8"), 0, string.length());
            byte[] bt = md.digest();
            BigInteger bi = new BigInteger(1, bt);
            encryptMD5 = bi.toString(16);
        } catch (UnsupportedEncodingException ex) {
            System.err.println("MD5 - ERROR " + ex);
        }
        return encryptMD5;
    }
}
