/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Entities.Usuario;
import Facade.ControllerJPAUsuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Yury
 */
public class registerController extends HttpServlet {

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
        String nombres, apellidos, email, password, confirmacionPassword, direccion, telefono1, telefono2;
        nombres = request.getParameter("nombres");
        apellidos = request.getParameter("apellidos");
        email = request.getParameter("email");
        password = request.getParameter("password");
        confirmacionPassword = request.getParameter("confirmacionPassword");
        direccion = request.getParameter("direccion");
        telefono1 = request.getParameter("telefono1");
        telefono2 = request.getParameter("telefono2");

        if (password.equals(confirmacionPassword)) {
            ControllerJPAUsuario controllerJPAUsuario = new ControllerJPAUsuario();
            Usuario usuario = controllerJPAUsuario.createUsuario(nombres, apellidos, email, password,
                    direccion, telefono1, telefono2);

            if (usuario != null) {
                HttpSession session = request.getSession(true);
                session.setAttribute("Usuario", usuario);
                request.setAttribute("mensaje", "Registro procesado exitosamente");
                request.getRequestDispatcher("confirmacion.jsp").forward(request, response);
            } else {
                request.setAttribute("mensaje", "Error registrando usuario... Por favor revise los datos");
                request.getRequestDispatcher("registro.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("mensaje", "Error al validar confirmación de contraseña... Ingrese nuevamente");
            request.getRequestDispatcher("registro.jsp").forward(request, response);
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
