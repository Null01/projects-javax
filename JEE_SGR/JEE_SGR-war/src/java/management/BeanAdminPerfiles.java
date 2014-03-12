/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package management;

import entities.Articulo;
import entities.Recurso;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

/**
 *
 * @author duran
 */
public class BeanAdminPerfiles {

    private List<Recurso> listaPerfiles;
    private List<Articulo> listaArticulos;

    public BeanAdminPerfiles() {
        listaArticulos = new ArrayList<>();
        listaPerfiles = new ArrayList<>();
    }

    @PostConstruct
    public void initialize() {

    }

}
