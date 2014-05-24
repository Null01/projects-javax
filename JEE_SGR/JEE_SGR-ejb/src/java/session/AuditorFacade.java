/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session;

import entities.Auditor;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import util.InterceptorWeb;

/**
 *
 * @author Diana Vasquez
 */
@Stateless
@Interceptors(InterceptorWeb.class)
public class AuditorFacade extends AbstractFacade<Auditor> implements AuditorFacadeLocal {
    @PersistenceContext(unitName = "JEE_SGR-ejbPU2")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AuditorFacade() {
        super(Auditor.class);
    }
    
}
