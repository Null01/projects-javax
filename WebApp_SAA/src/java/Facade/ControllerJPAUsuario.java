/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.TipoUsuario;
import Entities.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Yury
 */
public class ControllerJPAUsuario {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("WebApp_SAAPU");

    public Usuario getUsuario(String email) {
        try {
            EntityManager em = emf.createEntityManager();
            Query query = em.createNamedQuery("Usuario.findByEmail");
            query.setParameter("email", email);
            return (Usuario) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Usuario createUsuario(String nombres, String apellidos, String email,
            String password, String direccion, String telefono1, String telefono2) {
        EntityManager em = emf.createEntityManager();
        TipoUsuario tipoUsuario = em.getReference(TipoUsuario.class, 2);
        em.getTransaction().begin();

        Usuario usuario = new Usuario();
        usuario.setApellidos(apellidos);
        usuario.setDireccion(direccion);
        usuario.setEmail(email);
        usuario.setIdTipoUsuario(tipoUsuario);
        usuario.setNombres(nombres);
        usuario.setPassword(password);
        usuario.setTelefono1(telefono1);
        usuario.setTelefono2(telefono2);

        em.persist(usuario);
        em.getTransaction().commit();
        em.refresh(usuario);

        return usuario;
    }
}
