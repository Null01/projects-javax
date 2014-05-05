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
import Entities.IdEstadoSolicitud;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
public class IdEstadoSolicitudJpaController implements Serializable {

    public IdEstadoSolicitudJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(IdEstadoSolicitud idEstadoSolicitud) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (idEstadoSolicitud.getSolicitudList() == null) {
            idEstadoSolicitud.setSolicitudList(new ArrayList<Solicitud>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            List<Solicitud> attachedSolicitudList = new ArrayList<Solicitud>();
            for (Solicitud solicitudListSolicitudToAttach : idEstadoSolicitud.getSolicitudList()) {
                solicitudListSolicitudToAttach = em.getReference(solicitudListSolicitudToAttach.getClass(), solicitudListSolicitudToAttach.getIdSolicitud());
                attachedSolicitudList.add(solicitudListSolicitudToAttach);
            }
            idEstadoSolicitud.setSolicitudList(attachedSolicitudList);
            em.persist(idEstadoSolicitud);
            for (Solicitud solicitudListSolicitud : idEstadoSolicitud.getSolicitudList()) {
                IdEstadoSolicitud oldIdEstadoSolicitudOfSolicitudListSolicitud = solicitudListSolicitud.getIdEstadoSolicitud();
                solicitudListSolicitud.setIdEstadoSolicitud(idEstadoSolicitud);
                solicitudListSolicitud = em.merge(solicitudListSolicitud);
                if (oldIdEstadoSolicitudOfSolicitudListSolicitud != null) {
                    oldIdEstadoSolicitudOfSolicitudListSolicitud.getSolicitudList().remove(solicitudListSolicitud);
                    oldIdEstadoSolicitudOfSolicitudListSolicitud = em.merge(oldIdEstadoSolicitudOfSolicitudListSolicitud);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findIdEstadoSolicitud(idEstadoSolicitud.getIdEstadoSolicitud()) != null) {
                throw new PreexistingEntityException("IdEstadoSolicitud " + idEstadoSolicitud + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(IdEstadoSolicitud idEstadoSolicitud) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            IdEstadoSolicitud persistentIdEstadoSolicitud = em.find(IdEstadoSolicitud.class, idEstadoSolicitud.getIdEstadoSolicitud());
            List<Solicitud> solicitudListOld = persistentIdEstadoSolicitud.getSolicitudList();
            List<Solicitud> solicitudListNew = idEstadoSolicitud.getSolicitudList();
            List<String> illegalOrphanMessages = null;
            for (Solicitud solicitudListOldSolicitud : solicitudListOld) {
                if (!solicitudListNew.contains(solicitudListOldSolicitud)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Solicitud " + solicitudListOldSolicitud + " since its idEstadoSolicitud field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Solicitud> attachedSolicitudListNew = new ArrayList<Solicitud>();
            for (Solicitud solicitudListNewSolicitudToAttach : solicitudListNew) {
                solicitudListNewSolicitudToAttach = em.getReference(solicitudListNewSolicitudToAttach.getClass(), solicitudListNewSolicitudToAttach.getIdSolicitud());
                attachedSolicitudListNew.add(solicitudListNewSolicitudToAttach);
            }
            solicitudListNew = attachedSolicitudListNew;
            idEstadoSolicitud.setSolicitudList(solicitudListNew);
            idEstadoSolicitud = em.merge(idEstadoSolicitud);
            for (Solicitud solicitudListNewSolicitud : solicitudListNew) {
                if (!solicitudListOld.contains(solicitudListNewSolicitud)) {
                    IdEstadoSolicitud oldIdEstadoSolicitudOfSolicitudListNewSolicitud = solicitudListNewSolicitud.getIdEstadoSolicitud();
                    solicitudListNewSolicitud.setIdEstadoSolicitud(idEstadoSolicitud);
                    solicitudListNewSolicitud = em.merge(solicitudListNewSolicitud);
                    if (oldIdEstadoSolicitudOfSolicitudListNewSolicitud != null && !oldIdEstadoSolicitudOfSolicitudListNewSolicitud.equals(idEstadoSolicitud)) {
                        oldIdEstadoSolicitudOfSolicitudListNewSolicitud.getSolicitudList().remove(solicitudListNewSolicitud);
                        oldIdEstadoSolicitudOfSolicitudListNewSolicitud = em.merge(oldIdEstadoSolicitudOfSolicitudListNewSolicitud);
                    }
                }
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
                Integer id = idEstadoSolicitud.getIdEstadoSolicitud();
                if (findIdEstadoSolicitud(id) == null) {
                    throw new NonexistentEntityException("The idEstadoSolicitud with id " + id + " no longer exists.");
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
            utx.begin();
            em = getEntityManager();
            IdEstadoSolicitud idEstadoSolicitud;
            try {
                idEstadoSolicitud = em.getReference(IdEstadoSolicitud.class, id);
                idEstadoSolicitud.getIdEstadoSolicitud();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The idEstadoSolicitud with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Solicitud> solicitudListOrphanCheck = idEstadoSolicitud.getSolicitudList();
            for (Solicitud solicitudListOrphanCheckSolicitud : solicitudListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This IdEstadoSolicitud (" + idEstadoSolicitud + ") cannot be destroyed since the Solicitud " + solicitudListOrphanCheckSolicitud + " in its solicitudList field has a non-nullable idEstadoSolicitud field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(idEstadoSolicitud);
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

    public List<IdEstadoSolicitud> findIdEstadoSolicitudEntities() {
        return findIdEstadoSolicitudEntities(true, -1, -1);
    }

    public List<IdEstadoSolicitud> findIdEstadoSolicitudEntities(int maxResults, int firstResult) {
        return findIdEstadoSolicitudEntities(false, maxResults, firstResult);
    }

    private List<IdEstadoSolicitud> findIdEstadoSolicitudEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(IdEstadoSolicitud.class));
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

    public IdEstadoSolicitud findIdEstadoSolicitud(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(IdEstadoSolicitud.class, id);
        } finally {
            em.close();
        }
    }

    public int getIdEstadoSolicitudCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<IdEstadoSolicitud> rt = cq.from(IdEstadoSolicitud.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
