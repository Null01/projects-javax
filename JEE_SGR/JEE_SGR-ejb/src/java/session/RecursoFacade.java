
package session;

import entities.Recurso;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author duran
 * @version 1.0
 */
@Stateless
public class RecursoFacade extends AbstractFacade<Recurso> implements RecursoFacadeLocal {
    @PersistenceContext(unitName = "JEE_SGR-ejbPU2")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RecursoFacade() {
        super(Recurso.class);
    }

}
