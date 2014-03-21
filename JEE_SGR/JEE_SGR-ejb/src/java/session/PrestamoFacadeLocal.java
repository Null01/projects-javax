/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Prestamo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author AGarcia
 */
@Local
public interface PrestamoFacadeLocal {

    void create(Prestamo prestamo);

    void edit(Prestamo prestamo);

    void remove(Prestamo prestamo);

    Prestamo find(Object id);

    List<Prestamo> findAll();

    List<Prestamo> findRange(int[] range);

    int count();

}
