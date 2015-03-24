package edu.lab.session;

import edu.lab.entities.Login;
import edu.lab.modelo.Usuario;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author andresfelipegarciaduran
 */
@Stateless
@LocalBean
public class SessionControllerBean implements Serializable {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("JEE_Cookbook-ejbPU");

    public Usuario isUserRegistered(String email, String password) throws Exception {
        EntityManager em = emf.createEntityManager();
        Login find = em.find(Login.class, email);
        if (find == null) {
            throw new Exception("ERROR - EL CORREO ELECTRONICO NO ESTA REGISTRADO");
        }
        if (find.getPass().compareTo(password) != 0) {
            throw new Exception("ERROR - LA CONTRASEÑA NO COINCIDE CON EL REGISTRO");
        }
        Usuario outcome = new Usuario(find.getUsuario().getFname(), find.getUsuario().getLname(), find.getEmail(), find.getTypeuser());
        return outcome;
    }

}
