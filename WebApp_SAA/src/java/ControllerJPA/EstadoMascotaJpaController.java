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
import Entities.EstadoMascota;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entities.Mascota;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Duran
 */
public class EstadoMascotaJpaController implements Serializable {

    public EstadoMascotaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EstadoMascota estadoMascota) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (estadoMascota.getMascotaList() == null) {
            estadoMascota.setMascotaList(new ArrayList<Mascota>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            List<Mascota> attachedMascotaList = new ArrayList<Mascota>();
            for (Mascota mascotaListMascotaToAttach : estadoMascota.getMascotaList()) {
                mascotaListMascotaToAttach = em.getReference(mascotaListMascotaToAttach.getClass(), mascotaListMascotaToAttach.getIdMascota());
                attachedMascotaList.add(mascotaListMascotaToAttach);
            }
            estadoMascota.setMascotaList(attachedMascotaList);
            em.persist(estadoMascota);
            for (Mascota mascotaListMascota : estadoMascota.getMascotaList()) {
                EstadoMascota oldIdEstadoMascotaOfMascotaListMascota = mascotaListMascota.getIdEstadoMascota();
                mascotaListMascota.setIdEstadoMascota(estadoMascota);
                mascotaListMascota = em.merge(mascotaListMascota);
                if (oldIdEstadoMascotaOfMascotaListMascota != null) {
                    oldIdEstadoMascotaOfMascotaListMascota.getMascotaList().remove(mascotaListMascota);
                    oldIdEstadoMascotaOfMascotaListMascota = em.merge(oldIdEstadoMascotaOfMascotaListMascota);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findEstadoMascota(estadoMascota.getIdEstadoMascota()) != null) {
                throw new PreexistingEntityException("EstadoMascota " + estadoMascota + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EstadoMascota estadoMascota) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            EstadoMascota persistentEstadoMascota = em.find(EstadoMascota.class, estadoMascota.getIdEstadoMascota());
            List<Mascota> mascotaListOld = persistentEstadoMascota.getMascotaList();
            List<Mascota> mascotaListNew = estadoMascota.getMascotaList();
            List<String> illegalOrphanMessages = null;
            for (Mascota mascotaListOldMascota : mascotaListOld) {
                if (!mascotaListNew.contains(mascotaListOldMascota)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Mascota " + mascotaListOldMascota + " since its idEstadoMascota field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Mascota> attachedMascotaListNew = new ArrayList<Mascota>();
            for (Mascota mascotaListNewMascotaToAttach : mascotaListNew) {
                mascotaListNewMascotaToAttach = em.getReference(mascotaListNewMascotaToAttach.getClass(), mascotaListNewMascotaToAttach.getIdMascota());
                attachedMascotaListNew.add(mascotaListNewMascotaToAttach);
            }
            mascotaListNew = attachedMascotaListNew;
            estadoMascota.setMascotaList(mascotaListNew);
            estadoMascota = em.merge(estadoMascota);
            for (Mascota mascotaListNewMascota : mascotaListNew) {
                if (!mascotaListOld.contains(mascotaListNewMascota)) {
                    EstadoMascota oldIdEstadoMascotaOfMascotaListNewMascota = mascotaListNewMascota.getIdEstadoMascota();
                    mascotaListNewMascota.setIdEstadoMascota(estadoMascota);
                    mascotaListNewMascota = em.merge(mascotaListNewMascota);
                    if (oldIdEstadoMascotaOfMascotaListNewMascota != null && !oldIdEstadoMascotaOfMascotaListNewMascota.equals(estadoMascota)) {
                        oldIdEstadoMascotaOfMascotaListNewMascota.getMascotaList().remove(mascotaListNewMascota);
                        oldIdEstadoMascotaOfMascotaListNewMascota = em.merge(oldIdEstadoMascotaOfMascotaListNewMascota);
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
                Integer id = estadoMascota.getIdEstadoMascota();
                if (findEstadoMascota(id) == null) {
                    throw new NonexistentEntityException("The estadoMascota with id " + id + " no longer exists.");
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
            EstadoMascota estadoMascota;
            try {
                estadoMascota = em.getReference(EstadoMascota.class, id);
                estadoMascota.getIdEstadoMascota();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estadoMascota with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Mascota> mascotaListOrphanCheck = estadoMascota.getMascotaList();
            for (Mascota mascotaListOrphanCheckMascota : mascotaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This EstadoMascota (" + estadoMascota + ") cannot be destroyed since the Mascota " + mascotaListOrphanCheckMascota + " in its mascotaList field has a non-nullable idEstadoMascota field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(estadoMascota);
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

    public List<EstadoMascota> findEstadoMascotaEntities() {
        return findEstadoMascotaEntities(true, -1, -1);
    }

    public List<EstadoMascota> findEstadoMascotaEntities(int maxResults, int firstResult) {
        return findEstadoMascotaEntities(false, maxResults, firstResult);
    }

    private List<EstadoMascota> findEstadoMascotaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EstadoMascota.class));
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

    public EstadoMascota findEstadoMascota(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EstadoMascota.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadoMascotaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EstadoMascota> rt = cq.from(EstadoMascota.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
