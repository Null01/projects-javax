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
import Entities.Mascota;
import Entities.TipoMascota;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Duran
 */
public class TipoMascotaJpaController implements Serializable {

    public TipoMascotaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoMascota tipoMascota) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (tipoMascota.getMascotaList() == null) {
            tipoMascota.setMascotaList(new ArrayList<Mascota>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            List<Mascota> attachedMascotaList = new ArrayList<Mascota>();
            for (Mascota mascotaListMascotaToAttach : tipoMascota.getMascotaList()) {
                mascotaListMascotaToAttach = em.getReference(mascotaListMascotaToAttach.getClass(), mascotaListMascotaToAttach.getIdMascota());
                attachedMascotaList.add(mascotaListMascotaToAttach);
            }
            tipoMascota.setMascotaList(attachedMascotaList);
            em.persist(tipoMascota);
            for (Mascota mascotaListMascota : tipoMascota.getMascotaList()) {
                TipoMascota oldIdTipoMascotaOfMascotaListMascota = mascotaListMascota.getIdTipoMascota();
                mascotaListMascota.setIdTipoMascota(tipoMascota);
                mascotaListMascota = em.merge(mascotaListMascota);
                if (oldIdTipoMascotaOfMascotaListMascota != null) {
                    oldIdTipoMascotaOfMascotaListMascota.getMascotaList().remove(mascotaListMascota);
                    oldIdTipoMascotaOfMascotaListMascota = em.merge(oldIdTipoMascotaOfMascotaListMascota);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findTipoMascota(tipoMascota.getIdTipoMascota()) != null) {
                throw new PreexistingEntityException("TipoMascota " + tipoMascota + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoMascota tipoMascota) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            TipoMascota persistentTipoMascota = em.find(TipoMascota.class, tipoMascota.getIdTipoMascota());
            List<Mascota> mascotaListOld = persistentTipoMascota.getMascotaList();
            List<Mascota> mascotaListNew = tipoMascota.getMascotaList();
            List<String> illegalOrphanMessages = null;
            for (Mascota mascotaListOldMascota : mascotaListOld) {
                if (!mascotaListNew.contains(mascotaListOldMascota)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Mascota " + mascotaListOldMascota + " since its idTipoMascota field is not nullable.");
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
            tipoMascota.setMascotaList(mascotaListNew);
            tipoMascota = em.merge(tipoMascota);
            for (Mascota mascotaListNewMascota : mascotaListNew) {
                if (!mascotaListOld.contains(mascotaListNewMascota)) {
                    TipoMascota oldIdTipoMascotaOfMascotaListNewMascota = mascotaListNewMascota.getIdTipoMascota();
                    mascotaListNewMascota.setIdTipoMascota(tipoMascota);
                    mascotaListNewMascota = em.merge(mascotaListNewMascota);
                    if (oldIdTipoMascotaOfMascotaListNewMascota != null && !oldIdTipoMascotaOfMascotaListNewMascota.equals(tipoMascota)) {
                        oldIdTipoMascotaOfMascotaListNewMascota.getMascotaList().remove(mascotaListNewMascota);
                        oldIdTipoMascotaOfMascotaListNewMascota = em.merge(oldIdTipoMascotaOfMascotaListNewMascota);
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
                Integer id = tipoMascota.getIdTipoMascota();
                if (findTipoMascota(id) == null) {
                    throw new NonexistentEntityException("The tipoMascota with id " + id + " no longer exists.");
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
            TipoMascota tipoMascota;
            try {
                tipoMascota = em.getReference(TipoMascota.class, id);
                tipoMascota.getIdTipoMascota();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoMascota with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Mascota> mascotaListOrphanCheck = tipoMascota.getMascotaList();
            for (Mascota mascotaListOrphanCheckMascota : mascotaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TipoMascota (" + tipoMascota + ") cannot be destroyed since the Mascota " + mascotaListOrphanCheckMascota + " in its mascotaList field has a non-nullable idTipoMascota field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tipoMascota);
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

    public List<TipoMascota> findTipoMascotaEntities() {
        return findTipoMascotaEntities(true, -1, -1);
    }

    public List<TipoMascota> findTipoMascotaEntities(int maxResults, int firstResult) {
        return findTipoMascotaEntities(false, maxResults, firstResult);
    }

    private List<TipoMascota> findTipoMascotaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoMascota.class));
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

    public TipoMascota findTipoMascota(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoMascota.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoMascotaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoMascota> rt = cq.from(TipoMascota.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
