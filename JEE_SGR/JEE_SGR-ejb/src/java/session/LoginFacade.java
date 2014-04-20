/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Login;
import entities.LoginPK;
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
    public boolean autenticarUsuario(LoginPK loginPK) {
        Login find = em.find(Login.class, loginPK);
        return (find != null);
    }

}
