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
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Yury
 */
public class ControllerJPASolicitud {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("WebApp_SAAPU");
    
    public Solicitud createSolicitud(int idMascota, Usuario usuario)
    {
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
}
