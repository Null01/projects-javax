/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Login;
import entities.LoginPK;
import java.util.Date;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import util.InterceptorWeb;

/**
 *
 * @author duran
 * @version 1.0
 */
@Stateless
@Interceptors(InterceptorWeb.class)
public class LoginFacade extends AbstractFacade<Login> implements LoginFacadeLocal {

    private final short MAX_TRY_LOGIN = 3;

    @PersistenceContext(unitName = "JEE_SGR-ejbPU2")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LoginFacade() {
        super(Login.class);
    }

    @Override
    public int autenticarUsuario(LoginPK loginPK) {
        Login login = em.find(Login.class, loginPK);
        if (login != null) {
            if (login.getCountTrys() >= MAX_TRY_LOGIN) {
                return 1;
            } else {
                login.setCountTrys(new Short("0"));
                login.setDateLastTry(null);
                em.merge(login);
                return 0;
            }
        } else {
            login = (Login) em.createNamedQuery("Login.findByNameUser").setParameter("nameUser", loginPK.getNameUser()).getSingleResult();
            if (login != null) {
                if (login.getCountTrys() >= MAX_TRY_LOGIN) {
                    return 1;
                } else {
                    short trys = login.getCountTrys();
                    login.setCountTrys((short) (trys + 1));
                    login.setDateLastTry(new Date());
                    em.merge(login);
                    return (login.getCountTrys() >= MAX_TRY_LOGIN) ? 1 : -1;
                }
            } else {
                return -1;
            }
        }
    }
}
