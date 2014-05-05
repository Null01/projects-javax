/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.Mascota;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Duran
 */
public class ControllerJPAMascota {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("WebApp_SAAPU");

    public List<Mascota> getListMascotas() {

        EntityManager em = emf.createEntityManager();

        // em.getTransaction().begin();
        //List<Mascota> lista = em.createNamedQuery("SELECT m FROM Mascota m").getResultList();
        // em.getTransaction().commit();
        return null;
    }

    /* public static void main(String args[]) {
     System.out.println("inicio");
     ControllerJPAMascota controllerJPAMascota = new ControllerJPAMascota();
     controllerJPAMascota.emf.createEntityManager();
     System.out.println("fin");
     }*/
}
