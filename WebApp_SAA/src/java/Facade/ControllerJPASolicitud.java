/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.EstadoMascota;
import Entities.IdEstadoSolicitud;
import Entities.Mascota;
import Entities.Solicitud;
import Entities.Usuario;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Yury
 */
public class ControllerJPASolicitud {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("WebApp_SAAPU");

    public List<Solicitud> getListSolicitudes() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("Solicitud.findAll");
        return query.getResultList();
    }

    public Solicitud createSolicitud(int idMascota, Usuario usuario) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Mascota mascota = em.getReference(Mascota.class, idMascota);
        mascota.setIdEstadoMascota(em.getReference(EstadoMascota.class, 1));

        Solicitud solicitud = new Solicitud();
        solicitud.setFechaSolicitud(new Date());
        solicitud.setIdEstadoSolicitud(em.getReference(IdEstadoSolicitud.class, 1));
        solicitud.setIdMascota(mascota);
        solicitud.setIdUsuario(usuario);

        em.persist(solicitud);
        em.getTransaction().commit();
        em.refresh(solicitud);

        return solicitud;
    }

    public boolean actualizarEstadoSolicitud(int idSolicitud, int idEstado) {
        int idEstadoMascota = 0;
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Solicitud solicitud = em.getReference(Solicitud.class, idSolicitud);
        solicitud.setIdEstadoSolicitud(em.getReference(IdEstadoSolicitud.class, idEstado));

        if (idEstado == 2) {
            idEstadoMascota = 3;
        } else {
            idEstadoMascota = 2;
        }

        Mascota mascota = solicitud.getIdMascota();
        mascota.setIdEstadoMascota(em.getReference(EstadoMascota.class, idEstadoMascota));

        em.persist(solicitud);
        em.persist(mascota);
        em.getTransaction().commit();

        return true;
    }
}
