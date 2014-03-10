/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import controller.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.Funcion;
import entities.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author duran
 * @version 1.0
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (usuario.getFuncionList() == null) {
            usuario.setFuncionList(new ArrayList<Funcion>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            List<Funcion> attachedFuncionList = new ArrayList<Funcion>();
            for (Funcion funcionListFuncionToAttach : usuario.getFuncionList()) {
                funcionListFuncionToAttach = em.getReference(funcionListFuncionToAttach.getClass(), funcionListFuncionToAttach.getIdFuncion());
                attachedFuncionList.add(funcionListFuncionToAttach);
            }
            usuario.setFuncionList(attachedFuncionList);
            em.persist(usuario);
            for (Funcion funcionListFuncion : usuario.getFuncionList()) {
                funcionListFuncion.getUsuarioList().add(usuario);
                funcionListFuncion = em.merge(funcionListFuncion);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findUsuario(usuario.getNameUser()) != null) {
                throw new PreexistingEntityException("Usuario " + usuario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getNameUser());
            List<Funcion> funcionListOld = persistentUsuario.getFuncionList();
            List<Funcion> funcionListNew = usuario.getFuncionList();
            List<Funcion> attachedFuncionListNew = new ArrayList<Funcion>();
            for (Funcion funcionListNewFuncionToAttach : funcionListNew) {
                funcionListNewFuncionToAttach = em.getReference(funcionListNewFuncionToAttach.getClass(), funcionListNewFuncionToAttach.getIdFuncion());
                attachedFuncionListNew.add(funcionListNewFuncionToAttach);
            }
            funcionListNew = attachedFuncionListNew;
            usuario.setFuncionList(funcionListNew);
            usuario = em.merge(usuario);
            for (Funcion funcionListOldFuncion : funcionListOld) {
                if (!funcionListNew.contains(funcionListOldFuncion)) {
                    funcionListOldFuncion.getUsuarioList().remove(usuario);
                    funcionListOldFuncion = em.merge(funcionListOldFuncion);
                }
            }
            for (Funcion funcionListNewFuncion : funcionListNew) {
                if (!funcionListOld.contains(funcionListNewFuncion)) {
                    funcionListNewFuncion.getUsuarioList().add(usuario);
                    funcionListNewFuncion = em.merge(funcionListNewFuncion);
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
                String id = usuario.getNameUser();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getNameUser();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<Funcion> funcionList = usuario.getFuncionList();
            for (Funcion funcionListFuncion : funcionList) {
                funcionListFuncion.getUsuarioList().remove(usuario);
                funcionListFuncion = em.merge(funcionListFuncion);
            }
            em.remove(usuario);
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

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
