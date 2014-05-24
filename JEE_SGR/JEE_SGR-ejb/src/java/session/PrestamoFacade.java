package session;

import entities.Prestamo;
import entities.PrestamoPK;
import entities.Recurso;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.InterceptorWeb;

/**
 *
 * @author duran
 * @version 1.0
 */
@Stateless
@Interceptors(InterceptorWeb.class)
public class PrestamoFacade extends AbstractFacade<Prestamo> implements PrestamoFacadeLocal {

    @PersistenceContext(unitName = "JEE_SGR-ejbPU2")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PrestamoFacade() {
        super(Prestamo.class);
    }

    @Override
    public List<Recurso> getResourceEnable() {
        Query query = em.createQuery("SELECT p.prestamoPK FROM Prestamo p WHERE p.fechaDevolucion is null");
        List<PrestamoPK> list = query.getResultList();

        List<Recurso> resultList;
        String queryString = "SELECT p FROM Recurso p " + ((!list.isEmpty()) ? " WHERE " : ""); // Build query
        boolean first = false;
        for (PrestamoPK key : list) {
            if (first) {
                queryString += " and ";
            }
            first = true;
            queryString += " p.idRecurso != " + key.getIdRecurso() + " ";
        }
        resultList = em.createQuery(queryString).getResultList();
        if (resultList == null) {
            return new ArrayList<>();
        }
        return resultList;
    }

    @Override
    public int countResourceLoanByUser(String nameUser) {
        Query query = em.createQuery("SELECT COUNT(p) FROM Prestamo p WHERE p.fechaDevolucion is null and p.prestamoPK.idUsuario = :nameUser ");
        Long count = (Long) query.setParameter("nameUser", nameUser).getSingleResult();
        return count.intValue();
    }

    @Override
    public List<Recurso> getMyResourceLoan(String nameUser) {
        Query query = em.createQuery("SELECT p.prestamoPK FROM Prestamo p WHERE p.fechaDevolucion is null and p.prestamoPK.idUsuario = :nameUser ");
        List<PrestamoPK> list = query.setParameter("nameUser", nameUser).getResultList();
        List<Recurso> resultQuery = new ArrayList<>();
        for (PrestamoPK pk : list) {
            resultQuery.add(em.find(Recurso.class, pk.getIdRecurso()));
        }
        return resultQuery;
    }

    @Override
    public Prestamo getLoanByIdRecurso(Integer idRecurso, String nameUser) {
        Query query = em.createQuery("SELECT p FROM Prestamo p WHERE p.prestamoPK.idRecurso = :idRecurso  and p.prestamoPK.idUsuario = :nameUser");
        query.setParameter("idRecurso", idRecurso);
        Prestamo prestamo = (Prestamo) query.setParameter("nameUser", nameUser).getSingleResult();
        return prestamo;
    }
}
