package session;

import entities.Prestamo;
import entities.Recurso;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author duran
 */
@Local
public interface PrestamoFacadeLocal {

    void create(Prestamo prestamo);

    void edit(Prestamo prestamo);

    void remove(Prestamo prestamo);

    Prestamo find(Object id);

    List<Prestamo> findAll();

    List<Prestamo> findRange(int[] range);

    int count();

    List<Recurso> getResourceEnable();

    public int countResourceLoanByUser(String nameUser);

    public List<Recurso> getMyResourceLoan(String nameUser);

    public Prestamo getLoanByIdRecurso(Integer idRecurso, String nameUser);
}
