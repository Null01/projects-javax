/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.EstadoMascota;
import Entities.Mascota;
import Entities.Raza;
import Entities.TipoMascota;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Duran
 */
public class ControllerJPAMascota {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("WebApp_SAAPU");

    public List<Mascota> getListaMascota() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List resultList = em.createNamedQuery("Mascota.findAll").getResultList();
        em.getTransaction().commit();
        return resultList;
    }
    
    public List<Mascota> getListaMascotaPorEstado(int estado) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("Mascota.findByIdEstadoMascota");
        query.setParameter("idEstadoMascota", em.getReference(EstadoMascota.class, estado));
        return query.getResultList();
    }
    
    public Mascota getMascota(int idMascota)
    {
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("Mascota.findByIdMascota");
        query.setParameter("idMascota", idMascota);
        return (Mascota)query.getSingleResult();
    }

    public List<Raza> getListaRazas() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List resultList = em.createNamedQuery("Raza.findAll").getResultList();
        em.getTransaction().commit();
        return resultList;
    }

    public List<TipoMascota> getListaTipoMascota() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List resultList = em.createNamedQuery("TipoMascota.findAll").getResultList();
        em.getTransaction().commit();
        return resultList;
    }

}
