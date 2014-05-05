/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllerJPA;

import ControllerJPA.exceptions.IllegalOrphanException;
import ControllerJPA.exceptions.NonexistentEntityException;
import ControllerJPA.exceptions.PreexistingEntityException;
import ControllerJPA.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entities.TipoMascota;
import Entities.Raza;
import Entities.EstadoMascota;
import Entities.Mascota;
import Entities.Solicitud;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Duran
 */
public class MascotaJpaController implements Serializable {

    public MascotaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Mascota mascota) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (mascota.getSolicitudList() == null) {
            mascota.setSolicitudList(new ArrayList<Solicitud>());
        }
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();

            TipoMascota idTipoMascota = mascota.getIdTipoMascota();
            if (idTipoMascota != null) {
                idTipoMascota = em.getReference(idTipoMascota.getClass(), idTipoMascota.getIdTipoMascota());
                mascota.setIdTipoMascota(idTipoMascota);
            }

            Raza idRaza = mascota.getIdRaza();
            if (idRaza != null) {
                idRaza = em.getReference(idRaza.getClass(), idRaza.getIdRaza());
                mascota.setIdRaza(idRaza);
            }

            EstadoMascota idEstadoMascota = mascota.getIdEstadoMascota();
            if (idEstadoMascota != null) {
                idEstadoMascota = em.getReference(idEstadoMascota.getClass(), idEstadoMascota.getIdEstadoMascota());
                mascota.setIdEstadoMascota(idEstadoMascota);
            }

            List<Solicitud> attachedSolicitudList = new ArrayList<Solicitud>();
            for (Solicitud solicitudListSolicitudToAttach : mascota.getSolicitudList()) {
                solicitudListSolicitudToAttach = em.getReference(solicitudListSolicitudToAttach.getClass(), solicitudListSolicitudToAttach.getIdSolicitud());
                attachedSolicitudList.add(solicitudListSolicitudToAttach);
            }

            mascota.setSolicitudList(attachedSolicitudList);

            em.persist(mascota);

            if (idTipoMascota != null) {
                idTipoMascota.getMascotaList().add(mascota);
                idTipoMascota = em.merge(idTipoMascota);
            }

            if (idRaza != null) {
                idRaza.getMascotaList().add(mascota);
                idRaza = em.merge(idRaza);
            }

            if (idEstadoMascota != null) {
                idEstadoMascota.getMascotaList().add(mascota);
                idEstadoMascota = em.merge(idEstadoMascota);
            }

            for (Solicitud solicitudListSolicitud : mascota.getSolicitudList()) {
                Mascota oldIdMascotaOfSolicitudListSolicitud = solicitudListSolicitud.getIdMascota();
                solicitudListSolicitud.setIdMascota(mascota);
                solicitudListSolicitud = em.merge(solicitudListSolicitud);
                if (oldIdMascotaOfSolicitudListSolicitud != null) {
                    oldIdMascotaOfSolicitudListSolicitud.getSolicitudList().remove(solicitudListSolicitud);
                    oldIdMascotaOfSolicitudListSolicitud = em.merge(oldIdMascotaOfSolicitudListSolicitud);
                }
            }

            em.getTransaction().commit();
        } catch (Exception ex) {
            try {
                em.getTransaction().rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findMascota(mascota.getIdMascota()) != null) {
                throw new PreexistingEntityException("Mascota " + mascota + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Mascota mascota) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em.getTransaction().begin();
            Mascota persistentMascota = em.find(Mascota.class, mascota.getIdMascota());
            TipoMascota idTipoMascotaOld = persistentMascota.getIdTipoMascota();
            TipoMascota idTipoMascotaNew = mascota.getIdTipoMascota();
            Raza idRazaOld = persistentMascota.getIdRaza();
            Raza idRazaNew = mascota.getIdRaza();
            EstadoMascota idEstadoMascotaOld = persistentMascota.getIdEstadoMascota();
            EstadoMascota idEstadoMascotaNew = mascota.getIdEstadoMascota();
            List<Solicitud> solicitudListOld = persistentMascota.getSolicitudList();
            List<Solicitud> solicitudListNew = mascota.getSolicitudList();
            List<String> illegalOrphanMessages = null;
            for (Solicitud solicitudListOldSolicitud : solicitudListOld) {
                if (!solicitudListNew.contains(solicitudListOldSolicitud)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Solicitud " + solicitudListOldSolicitud + " since its idMascota field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idTipoMascotaNew != null) {
                idTipoMascotaNew = em.getReference(idTipoMascotaNew.getClass(), idTipoMascotaNew.getIdTipoMascota());
                mascota.setIdTipoMascota(idTipoMascotaNew);
            }
            if (idRazaNew != null) {
                idRazaNew = em.getReference(idRazaNew.getClass(), idRazaNew.getIdRaza());
                mascota.setIdRaza(idRazaNew);
            }
            if (idEstadoMascotaNew != null) {
                idEstadoMascotaNew = em.getReference(idEstadoMascotaNew.getClass(), idEstadoMascotaNew.getIdEstadoMascota());
                mascota.setIdEstadoMascota(idEstadoMascotaNew);
            }
            List<Solicitud> attachedSolicitudListNew = new ArrayList<Solicitud>();
            for (Solicitud solicitudListNewSolicitudToAttach : solicitudListNew) {
                solicitudListNewSolicitudToAttach = em.getReference(solicitudListNewSolicitudToAttach.getClass(), solicitudListNewSolicitudToAttach.getIdSolicitud());
                attachedSolicitudListNew.add(solicitudListNewSolicitudToAttach);
            }
            solicitudListNew = attachedSolicitudListNew;
            mascota.setSolicitudList(solicitudListNew);
            mascota = em.merge(mascota);
            if (idTipoMascotaOld != null && !idTipoMascotaOld.equals(idTipoMascotaNew)) {
                idTipoMascotaOld.getMascotaList().remove(mascota);
                idTipoMascotaOld = em.merge(idTipoMascotaOld);
            }
            if (idTipoMascotaNew != null && !idTipoMascotaNew.equals(idTipoMascotaOld)) {
                idTipoMascotaNew.getMascotaList().add(mascota);
                idTipoMascotaNew = em.merge(idTipoMascotaNew);
            }
            if (idRazaOld != null && !idRazaOld.equals(idRazaNew)) {
                idRazaOld.getMascotaList().remove(mascota);
                idRazaOld = em.merge(idRazaOld);
            }
            if (idRazaNew != null && !idRazaNew.equals(idRazaOld)) {
                idRazaNew.getMascotaList().add(mascota);
                idRazaNew = em.merge(idRazaNew);
            }
            if (idEstadoMascotaOld != null && !idEstadoMascotaOld.equals(idEstadoMascotaNew)) {
                idEstadoMascotaOld.getMascotaList().remove(mascota);
                idEstadoMascotaOld = em.merge(idEstadoMascotaOld);
            }
            if (idEstadoMascotaNew != null && !idEstadoMascotaNew.equals(idEstadoMascotaOld)) {
                idEstadoMascotaNew.getMascotaList().add(mascota);
                idEstadoMascotaNew = em.merge(idEstadoMascotaNew);
            }
            for (Solicitud solicitudListNewSolicitud : solicitudListNew) {
                if (!solicitudListOld.contains(solicitudListNewSolicitud)) {
                    Mascota oldIdMascotaOfSolicitudListNewSolicitud = solicitudListNewSolicitud.getIdMascota();
                    solicitudListNewSolicitud.setIdMascota(mascota);
                    solicitudListNewSolicitud = em.merge(solicitudListNewSolicitud);
                    if (oldIdMascotaOfSolicitudListNewSolicitud != null && !oldIdMascotaOfSolicitudListNewSolicitud.equals(mascota)) {
                        oldIdMascotaOfSolicitudListNewSolicitud.getSolicitudList().remove(solicitudListNewSolicitud);
                        oldIdMascotaOfSolicitudListNewSolicitud = em.merge(oldIdMascotaOfSolicitudListNewSolicitud);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            try {
                em.getTransaction().rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = mascota.getIdMascota();
                if (findMascota(id) == null) {
                    throw new NonexistentEntityException("The mascota with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em.getTransaction().begin();
            Mascota mascota;
            try {
                mascota = em.getReference(Mascota.class, id);
                mascota.getIdMascota();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mascota with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Solicitud> solicitudListOrphanCheck = mascota.getSolicitudList();
            for (Solicitud solicitudListOrphanCheckSolicitud : solicitudListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Mascota (" + mascota + ") cannot be destroyed since the Solicitud " + solicitudListOrphanCheckSolicitud + " in its solicitudList field has a non-nullable idMascota field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            TipoMascota idTipoMascota = mascota.getIdTipoMascota();
            if (idTipoMascota != null) {
                idTipoMascota.getMascotaList().remove(mascota);
                idTipoMascota = em.merge(idTipoMascota);
            }
            Raza idRaza = mascota.getIdRaza();
            if (idRaza != null) {
                idRaza.getMascotaList().remove(mascota);
                idRaza = em.merge(idRaza);
            }
            EstadoMascota idEstadoMascota = mascota.getIdEstadoMascota();
            if (idEstadoMascota != null) {
                idEstadoMascota.getMascotaList().remove(mascota);
                idEstadoMascota = em.merge(idEstadoMascota);
            }
            em.remove(mascota);
            em.getTransaction().commit();
        } catch (Exception ex) {
            try {
                em.getTransaction().rollback();
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

    public List<Mascota> findMascotaEntities() {
        return findMascotaEntities(true, -1, -1);
    }

    public List<Mascota> findMascotaEntities(int maxResults, int firstResult) {
        return findMascotaEntities(false, maxResults, firstResult);
    }

    private List<Mascota> findMascotaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Mascota.class));
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

    public Mascota findMascota(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Mascota.class, id);
        } finally {
            em.close();
        }
    }

    public int getMascotaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Mascota> rt = cq.from(Mascota.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
