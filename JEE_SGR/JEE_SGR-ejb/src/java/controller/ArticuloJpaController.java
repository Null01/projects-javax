/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import controller.exceptions.RollbackFailureException;
import entities.Articulo;
import entities.ArticuloPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.Recurso;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author duran
 * @version 1.0
 */
public class ArticuloJpaController implements Serializable {

    public ArticuloJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Articulo articulo) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (articulo.getArticuloPK() == null) {
            articulo.setArticuloPK(new ArticuloPK());
        }
        articulo.getArticuloPK().setIdRecurso(articulo.getRecurso().getIdRecurso());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Recurso recurso = articulo.getRecurso();
            if (recurso != null) {
                recurso = em.getReference(recurso.getClass(), recurso.getIdRecurso());
                articulo.setRecurso(recurso);
            }
            em.persist(articulo);
            if (recurso != null) {
                recurso.getArticuloList().add(articulo);
                recurso = em.merge(recurso);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findArticulo(articulo.getArticuloPK()) != null) {
                throw new PreexistingEntityException("Articulo " + articulo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Articulo articulo) throws NonexistentEntityException, RollbackFailureException, Exception {
        articulo.getArticuloPK().setIdRecurso(articulo.getRecurso().getIdRecurso());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Articulo persistentArticulo = em.find(Articulo.class, articulo.getArticuloPK());
            Recurso recursoOld = persistentArticulo.getRecurso();
            Recurso recursoNew = articulo.getRecurso();
            if (recursoNew != null) {
                recursoNew = em.getReference(recursoNew.getClass(), recursoNew.getIdRecurso());
                articulo.setRecurso(recursoNew);
            }
            articulo = em.merge(articulo);
            if (recursoOld != null && !recursoOld.equals(recursoNew)) {
                recursoOld.getArticuloList().remove(articulo);
                recursoOld = em.merge(recursoOld);
            }
            if (recursoNew != null && !recursoNew.equals(recursoOld)) {
                recursoNew.getArticuloList().add(articulo);
                recursoNew = em.merge(recursoNew);
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
                ArticuloPK id = articulo.getArticuloPK();
                if (findArticulo(id) == null) {
                    throw new NonexistentEntityException("The articulo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ArticuloPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Articulo articulo;
            try {
                articulo = em.getReference(Articulo.class, id);
                articulo.getArticuloPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The articulo with id " + id + " no longer exists.", enfe);
            }
            Recurso recurso = articulo.getRecurso();
            if (recurso != null) {
                recurso.getArticuloList().remove(articulo);
                recurso = em.merge(recurso);
            }
            em.remove(articulo);
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

    public List<Articulo> findArticuloEntities() {
        return findArticuloEntities(true, -1, -1);
    }

    public List<Articulo> findArticuloEntities(int maxResults, int firstResult) {
        return findArticuloEntities(false, maxResults, firstResult);
    }

    private List<Articulo> findArticuloEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Articulo.class));
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

    public Articulo findArticulo(ArticuloPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Articulo.class, id);
        } finally {
            em.close();
        }
    }

    public int getArticuloCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Articulo> rt = cq.from(Articulo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
