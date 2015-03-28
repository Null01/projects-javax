package edu.lab.services.user;

import edu.lab.entities.Login;
import edu.lab.session.ITipoUsuario;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author andresfelipegarciaduran
 */
@Stateless
@LocalBean
public class UserControllerBean {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("JEE_Cookbook-ejbPU");

    public void registarDatosUsuario(String fname, String lname, String email, String password, String confirmPassword) throws Exception {

        if (password.compareTo(confirmPassword) != 0) {
            throw new Exception("ERROR - LAS CONTRASEÑAS NO COINCIDEN");
        }

        EntityManager em = emf.createEntityManager();
        Login find = em.find(Login.class, email);
        if (find != null) {
            throw new Exception("ERROR - EL CORREO ELECTRONICO YA SE ENCUENTRA INSCRITO ");
        }

        Login login = new Login(email, password, ITipoUsuario.SIMPLE, new Date());
        edu.lab.entities.Usuario usuario = new edu.lab.entities.Usuario(email);
        usuario.setFname(fname);
        usuario.setLname(lname);
        usuario.setLogin(login);
        login.setUsuario(usuario);
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();

    }

    public void actualizarDatosUsuario(String fname, String lname, String email, String oldPassword, String password, String confirmPassword) throws Exception {
        if (confirmPassword == null) {
            password = oldPassword;
        } else {
            if (password.compareTo(confirmPassword) != 0) {
                throw new Exception("ERROR - LAS CONTRASEÑAS NO COINCIDEN");
            }
        }

        EntityManager em = emf.createEntityManager();
        Login login = em.find(Login.class, email);
        if (login.getPass().compareTo(oldPassword) != 0) {
            throw new Exception("ERROR - LA CONTRASEÑA ACTUAL NO COINCIDE");
        }
        edu.lab.entities.Usuario usuario = login.getUsuario();
        usuario.setFname(fname);
        usuario.setLname(lname);
        em.getTransaction().begin();
        login.setUsuario(usuario);
        login.setPass(password);
        em.getTransaction().commit();
    }
}
