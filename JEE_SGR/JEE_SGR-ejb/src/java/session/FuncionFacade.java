/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Funcion;
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
public class FuncionFacade extends AbstractFacade<Funcion> implements FuncionFacadeLocal {

    @PersistenceContext(unitName = "JEE_SGR-ejbPU2")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FuncionFacade() {
        super(Funcion.class);
    }

    @Override
    public Funcion findByNameFuncion(String nameFuncion) {
        Funcion funcion = (Funcion) getEntityManager().createNamedQuery("Funcion.findByNameFuncion").setParameter("nameFuncion", nameFuncion).getSingleResult();
        return funcion;
    }

}
