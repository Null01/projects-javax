
package session;

import entities.Login;
import entities.LoginPK;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author duran
 */
@Local
public interface LoginFacadeLocal {

    void create(Login login);

    void edit(Login login);

    void remove(Login login);

    Login find(Object id);

    List<Login> findAll();

    List<Login> findRange(int[] range);

    int count();

    public int autenticarUsuario(LoginPK loginPK);
    
}
