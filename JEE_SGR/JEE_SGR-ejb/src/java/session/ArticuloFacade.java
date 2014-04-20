/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Articulo;
import entities.Recurso;
import java.util.ArrayList;
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
public class ArticuloFacade extends AbstractFacade<Articulo> implements ArticuloFacadeLocal {

    @PersistenceContext(unitName = "JEE_SGR-ejbPU2")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArticuloFacade() {
        super(Articulo.class);
    }

    @Override
    public int countArticlesEnable(Recurso recurso) {
        int count = 0;
        List<Articulo> articuloList = recurso.getArticuloList();
        for (Articulo articulo : articuloList) {
            if (articulo.getDisponible() == 1) {
                ++count;
            }
        }
        return count;
    }

    @Override
    public List<Articulo> getArticlesEnable(Recurso recurso) {
        List<Articulo> articuloList = recurso.getArticuloList();
        if (articuloList != null) {
            List<Articulo> enable = new ArrayList<>();
            for (Articulo articulo : articuloList) {
                if (articulo.getDisponible() == 1) {
                    enable.add(articulo);
                }
            }
            return (!enable.isEmpty()) ? enable : null;
        }
        return null;
    }

    @Override
    public int countArticle(Recurso recurso) {
        List<Articulo> articuloList = recurso.getArticuloList();
        if (articuloList != null) {
            return articuloList.size();
        }
        return 0;
    }

}
