
package session;

import entities.Recurso;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author duran
 */
@Local
public interface RecursoFacadeLocal {

    void create(Recurso recurso);

    void edit(Recurso recurso);

    void remove(Recurso recurso);

    Recurso find(Object id);

    List<Recurso> findAll();

    List<Recurso> findRange(int[] range);

    int count();

    public boolean resourceIsEnable(Integer idRecurso);

    
}
