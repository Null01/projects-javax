package edu.lab.session;

import edu.lab.entities.Login;
import edu.lab.entities.LoginPK;
import edu.lab.modelo.Usuario;
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
public class SessionControllerBean {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("JEE_Cookbook-ejbPU");

    public Usuario isUserRegistered(String email, String password) throws Exception {
        EntityManager em = emf.createEntityManager();
        Login find = em.find(Login.class, new LoginPK(email, password));
        if (find == null) {
            return null;
        }
        edu.lab.entities.Usuario user_find = em.find(edu.lab.entities.Usuario.class, email);
        if (user_find == null) {
            throw new Exception("ERROR - LA LLAVE " + email + " NO EXISTE EN LA TABLA " + edu.lab.entities.Usuario.class.getSimpleName());
        }
        Usuario outcome = new Usuario(user_find.getFname(), user_find.getLname(), user_find.getEmail(), find.getTypeuser());
        return outcome;
    }

    public void registerUser(String fname, String lname, String email, String passoword) throws Exception {
        EntityManager em = emf.createEntityManager();
        edu.lab.entities.Usuario usuario = new edu.lab.entities.Usuario(email);
        usuario.setFname(fname);
        usuario.setLname(lname);
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();

        edu.lab.entities.Login login = new edu.lab.entities.Login(email, passoword);
        em.getTransaction().begin();
        em.persist(login);
        em.getTransaction().commit();
    }

}
