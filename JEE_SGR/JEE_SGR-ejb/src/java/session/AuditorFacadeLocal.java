/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session;

import entities.Auditor;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Diana Vasquez
 */
@Local
public interface AuditorFacadeLocal {

    void create(Auditor auditor);

    void edit(Auditor auditor);

    void remove(Auditor auditor);

    Auditor find(Object id);

    List<Auditor> findAll();

    List<Auditor> findRange(int[] range);

    int count();
    
}
