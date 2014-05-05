/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ControllerJPA;

import ControllerJPA.exceptions.NonexistentEntityException;
import ControllerJPA.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entities.Usuario;
import Entities.Mascota;
import Entities.IdEstadoSolicitud;
import Entities.Solicitud;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Duran
 */
public class SolicitudJpaController implements Serializable {

    public SolicitudJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Solicitud solicitud) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Usuario idUsuario = solicitud.getIdUsuario();
            if (idUsuario != null) {
                idUsuario = em.getReference(idUsuario.getClass(), idUsuario.getIdUsuario());
                solicitud.setIdUsuario(idUsuario);
            }
            Mascota idMascota = solicitud.getIdMascota();
            if (idMascota != null) {
                idMascota = em.getReference(idMascota.getClass(), idMascota.getIdMascota());
                solicitud.setIdMascota(idMascota);
            }
            IdEstadoSolicitud idEstadoSolicitud = solicitud.getIdEstadoSolicitud();
            if (idEstadoSolicitud != null) {
                idEstadoSolicitud = em.getReference(idEstadoSolicitud.getClass(), idEstadoSolicitud.getIdEstadoSolicitud());
                solicitud.setIdEstadoSolicitud(idEstadoSolicitud);
            }
            em.persist(solicitud);
            if (idUsuario != null) {
                idUsuario.getSolicitudList().add(solicitud);
                idUsuario = em.merge(idUsuario);
            }
            if (idMascota != null) {
                idMascota.getSolicitudList().add(solicitud);
                idMascota = em.merge(idMascota);
            }
            if (idEstadoSolicitud != null) {
                idEstadoSolicitud.getSolicitudList().add(solicitud);
                idEstadoSolicitud = em.merge(idEstadoSolicitud);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Solicitud solicitud) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Solicitud persistentSolicitud = em.find(Solicitud.class, solicitud.getIdSolicitud());
            Usuario idUsuarioOld = persistentSolicitud.getIdUsuario();
            Usuario idUsuarioNew = solicitud.getIdUsuario();
            Mascota idMascotaOld = persistentSolicitud.getIdMascota();
            Mascota idMascotaNew = solicitud.getIdMascota();
            IdEstadoSolicitud idEstadoSolicitudOld = persistentSolicitud.getIdEstadoSolicitud();
            IdEstadoSolicitud idEstadoSolicitudNew = solicitud.getIdEstadoSolicitud();
            if (idUsuarioNew != null) {
                idUsuarioNew = em.getReference(idUsuarioNew.getClass(), idUsuarioNew.getIdUsuario());
                solicitud.setIdUsuario(idUsuarioNew);
            }
            if (idMascotaNew != null) {
                idMascotaNew = em.getReference(idMascotaNew.getClass(), idMascotaNew.getIdMascota());
                solicitud.setIdMascota(idMascotaNew);
            }
            if (idEstadoSolicitudNew != null) {
                idEstadoSolicitudNew = em.getReference(idEstadoSolicitudNew.getClass(), idEstadoSolicitudNew.getIdEstadoSolicitud());
                solicitud.setIdEstadoSolicitud(idEstadoSolicitudNew);
            }
            solicitud = em.merge(solicitud);
            if (idUsuarioOld != null && !idUsuarioOld.equals(idUsuarioNew)) {
                idUsuarioOld.getSolicitudList().remove(solicitud);
                idUsuarioOld = em.merge(idUsuarioOld);
            }
            if (idUsuarioNew != null && !idUsuarioNew.equals(idUsuarioOld)) {
                idUsuarioNew.getSolicitudList().add(solicitud);
                idUsuarioNew = em.merge(idUsuarioNew);
            }
            if (idMascotaOld != null && !idMascotaOld.equals(idMascotaNew)) {
                idMascotaOld.getSolicitudList().remove(solicitud);
                idMascotaOld = em.merge(idMascotaOld);
            }
            if (idMascotaNew != null && !idMascotaNew.equals(idMascotaOld)) {
                idMascotaNew.getSolicitudList().add(solicitud);
                idMascotaNew = em.merge(idMascotaNew);
            }
            if (idEstadoSolicitudOld != null && !idEstadoSolicitudOld.equals(idEstadoSolicitudNew)) {
                idEstadoSolicitudOld.getSolicitudList().remove(solicitud);
                idEstadoSolicitudOld = em.merge(idEstadoSolicitudOld);
            }
            if (idEstadoSolicitudNew != null && !idEstadoSolicitudNew.equals(idEstadoSolicitudOld)) {
                idEstadoSolicitudNew.getSolicitudList().add(solicitud);
                idEstadoSolicitudNew = em.merge(idEstadoSolicitudNew);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = solicitud.getIdSolicitud();
                if (findSolicitud(id) == null) {
                    throw new NonexistentEntityException("The solicitud with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Solicitud solicitud;
            try {
                solicitud = em.getReference(Solicitud.class, id);
                solicitud.getIdSolicitud();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The solicitud with id " + id + " no longer exists.", enfe);
            }
            Usuario idUsuario = solicitud.getIdUsuario();
            if (idUsuario != null) {
                idUsuario.getSolicitudList().remove(solicitud);
                idUsuario = em.merge(idUsuario);
            }
            Mascota idMascota = solicitud.getIdMascota();
            if (idMascota != null) {
                idMascota.getSolicitudList().remove(solicitud);
                idMascota = em.merge(idMascota);
            }
            IdEstadoSolicitud idEstadoSolicitud = solicitud.getIdEstadoSolicitud();
            if (idEstadoSolicitud != null) {
                idEstadoSolicitud.getSolicitudList().remove(solicitud);
                idEstadoSolicitud = em.merge(idEstadoSolicitud);
            }
            em.remove(solicitud);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Solicitud> findSolicitudEntities() {
        return findSolicitudEntities(true, -1, -1);
    }

    public List<Solicitud> findSolicitudEntities(int maxResults, int firstResult) {
        return findSolicitudEntities(false, maxResults, firstResult);
    }

    private List<Solicitud> findSolicitudEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Solicitud.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Solicitud findSolicitud(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Solicitud.class, id);
        } finally {
            em.close();
        }
    }

    public int getSolicitudCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Solicitud> rt = cq.from(Solicitud.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
