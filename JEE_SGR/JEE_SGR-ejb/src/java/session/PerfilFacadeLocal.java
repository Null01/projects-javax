
package session;

import entities.Perfil;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author duran
 */
@Local
public interface PerfilFacadeLocal {

    void create(Perfil perfil);

    void edit(Perfil perfil);

    void remove(Perfil perfil);

    Perfil find(Object id);

    List<Perfil> findAll();

    List<Perfil> findRange(int[] range);

    int count();
    
}
