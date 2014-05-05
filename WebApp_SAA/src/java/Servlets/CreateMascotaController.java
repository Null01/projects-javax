/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import ControllerJPA.MascotaJpaController;
import ControllerJPA.exceptions.RollbackFailureException;
import Entities.EstadoMascota;
import Entities.Mascota;
import Entities.Raza;
import Entities.Solicitud;
import Entities.TipoMascota;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Duran
 */
public class CreateMascotaController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            String nombre = request.getParameter("nombre");
            String edad = request.getParameter("edad");
            String raza = request.getParameter("raza");
            String mascota = request.getParameter("mascota");

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("WebApp_SAAPU");
            EntityManager em = emf.createEntityManager();

            Mascota obj_Mascota = new Mascota();

            obj_Mascota.setEdad(new Integer(edad));
            obj_Mascota.setNombre(nombre);

            EstadoMascota find = em.find(EstadoMascota.class, new Integer("2"));
            obj_Mascota.setIdEstadoMascota(find);

            Raza find1 = em.find(Raza.class, new Integer(raza));
            obj_Mascota.setIdRaza(find1);

            TipoMascota find2 = em.find(TipoMascota.class, new Integer(mascota));
            obj_Mascota.setIdTipoMascota(find2);

            obj_Mascota.setOtraRaza(" ");
            obj_Mascota.setOtroTipoMascota(" ");
            obj_Mascota.setSolicitudList(new ArrayList<Solicitud>());
            System.out.println(nombre + " " + edad + " " + raza + " " + mascota);

            System.out.println("INICIA");
            MascotaJpaController controller = new MascotaJpaController(null, emf);
            try {
                controller.create(obj_Mascota);
            } catch (RollbackFailureException ex) {
                Logger.getLogger(CreateMascotaController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(CreateMascotaController.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("FINALIZA");

            response.sendRedirect("admin_system.jsp");

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
