/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Perfil;
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
public class PerfilFacade extends AbstractFacade<Perfil> implements PerfilFacadeLocal {

    @PersistenceContext(unitName = "JEE_SGR-ejbPU2")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PerfilFacade() {
        super(Perfil.class);
    }

}
