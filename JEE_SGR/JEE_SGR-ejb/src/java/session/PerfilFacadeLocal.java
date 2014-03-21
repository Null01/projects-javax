/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Perfil;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author AGarcia
 */
@Local
public interface PerfilFacadeLocal {

    void create(Perfil perfil);

    void edit(Perfil perfil);

    void remove(Perfil perfil);

    Perfil find(Object id);

    List<Perfil> findAll();

    List<Perfil> findRange(int[] range);

    int count();

}
