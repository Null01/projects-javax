/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.lab.admin.user;

import edu.lab.entities.Login;
import edu.lab.entities.Usuario;
import edu.lab.session.ITipoUsuario;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author EstebanC02
 */
@Singleton
@LocalBean
public class UserTransactionBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("JEE_Cookbook-ejbPU");

    public List<edu.lab.modelo.Usuario> obtenerUsuariosSimple() {
        List<edu.lab.modelo.Usuario> outcomeUsers = new ArrayList<>();
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT l FROM " + Login.class.getSimpleName() + " l WHERE l.typeuser = :idTypeUser").setParameter("idTypeUser", ITipoUsuario.SIMPLE);
        List list = query.getResultList();
        if (list != null) {
            for (Object objUsuario : list) {
                Usuario u = (Usuario) objUsuario;
                edu.lab.modelo.Usuario usuario = new edu.lab.modelo.Usuario(u.getFname(), u.getLname(), u.getEmail(), ITipoUsuario.SIMPLE);
                outcomeUsers.add(usuario);
            }
        }
        return outcomeUsers;
    }
}