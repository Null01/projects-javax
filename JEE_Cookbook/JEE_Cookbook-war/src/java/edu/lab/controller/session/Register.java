/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.lab.controller.session;

import edu.lab.modelo.Usuario;
import edu.lab.security.MonitorLogs;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import edu.lab.security.SecurityEncrypt;
import edu.lab.session.ITipoUsuario;
import edu.lab.session.SessionControllerBean;
import javax.ejb.EJB;

/**
 *
 * @author andresfelipegarciaduran
 */
public class Register extends HttpServlet {

    @EJB
    private SessionControllerBean sessionControllerBean;

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
        String first_name = request.getParameter("fname");
        String last_name = request.getParameter("lname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        try {
            SecurityEncrypt security = new SecurityEncrypt();
            String encryptWithMD5 = security.encryptWithMD5(password);
            String encryptWithMD5Confirm = security.encryptWithMD5(confirmPassword);
            sessionControllerBean.registerUser(first_name, last_name, email, encryptWithMD5, encryptWithMD5Confirm);
            Usuario userRegistered = sessionControllerBean.isUserRegistered(email, encryptWithMD5);
            if (userRegistered != null) {
                HttpSession session = request.getSession(true);
                session.setAttribute("user-data", userRegistered);
                MonitorLogs.onlyChannel.writeLogUser(getServletContext(), new String[]{Register.class.getSimpleName(), userRegistered.getCorreo(), userRegistered.getApellido() + " " + userRegistered.getNombre()});
                if (userRegistered.getTipo().compareTo(ITipoUsuario.ADMIN) == 0) {
                    response.sendRedirect("admin-home.jsp");
                } else {
                    response.sendRedirect("user-home.jsp");
                }
            } else {
                response.sendRedirect("register.jsp");
            }
        } catch (Exception ex) {
            request.setAttribute("message-error-register", ex.getMessage());
            request.getRequestDispatcher("register.jsp").forward(request, response);
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
