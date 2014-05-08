/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.EstadoMascota;
import Entities.Mascota;
import Entities.Raza;
import Entities.Solicitud;
import Entities.TipoMascota;
import java.util.ArrayList;
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

    public Mascota getMascota(int idMascota) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("Mascota.findByIdMascota");
        query.setParameter("idMascota", idMascota);
        return (Mascota) query.getSingleResult();
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

    public boolean createMascota(String nombre, int edad, int tipoMasota, int raza, int estadoMascota) {
        EntityManager em = emf.createEntityManager();

        Mascota mascota_new = new Mascota();
        mascota_new.setEdad(edad);
        mascota_new.setNombre(nombre);
        mascota_new.setIdEstadoMascota(em.getReference(EstadoMascota.class, estadoMascota));
        mascota_new.setIdRaza(em.getReference(Raza.class, raza));
        mascota_new.setIdTipoMascota(em.getReference(TipoMascota.class, tipoMasota));
        mascota_new.setOtraRaza("");
        mascota_new.setOtroTipoMascota("");
        mascota_new.setSolicitudList(new ArrayList<Solicitud>());

        em.getTransaction().begin();
        em.persist(mascota_new);
        em.getTransaction().commit();

        return true;
    }
}
