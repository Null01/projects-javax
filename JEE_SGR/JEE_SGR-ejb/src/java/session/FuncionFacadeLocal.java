/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session;

import entities.Funcion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author AGarcia
 */
@Local
public interface FuncionFacadeLocal {

    void create(Funcion funcion);

    void edit(Funcion funcion);

    void remove(Funcion funcion);

    Funcion find(Object id);

    List<Funcion> findAll();

    List<Funcion> findRange(int[] range);

    int count();
    
}
