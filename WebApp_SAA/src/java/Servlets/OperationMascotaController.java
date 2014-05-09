/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Entities.Mascota;
import Facade.ControllerJPAMascota;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author duran
 */
public class OperationMascotaController extends HttpServlet {

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

        ControllerJPAMascota controllerJPAMascota = new ControllerJPAMascota();

        String delete_submit = request.getParameter("delete-submit");
        if (delete_submit != null && !delete_submit.isEmpty()) {
            int id = Integer.parseInt(delete_submit);
            boolean deleteMascota = controllerJPAMascota.deleteMascota(id);
            if (deleteMascota) {
                response.sendRedirect("admin_system.jsp");
            }
        }

        String update = request.getParameter("update");
        if (update != null && !update.isEmpty()) {
            int id = Integer.parseInt(update);
            Mascota mascota = controllerJPAMascota.getMascota(id);
            request.setAttribute("update_active", mascota);
            request.getRequestDispatcher("admin_system.jsp").forward(request, response);
        }

        String update_submit = request.getParameter("update-submit");
        if (update_submit != null && !update_submit.isEmpty()) {
            int id_ = Integer.parseInt(update_submit);
            String nombre = request.getParameter("nombre");
            
            String edad = request.getParameter("edad");
            int edad_ = Integer.parseInt(edad);
            
            String raza = request.getParameter("raza");
            int raza_ = Integer.parseInt(raza);
            
            String mascota = request.getParameter("mascota");
            int mascota_ = Integer.parseInt(mascota);
            
            controllerJPAMascota.updateMascota(id_, nombre, edad_, raza_, mascota_);
            request.setAttribute("update_active", null);
            request.getRequestDispatcher("admin_system.jsp").forward(request, response);
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
