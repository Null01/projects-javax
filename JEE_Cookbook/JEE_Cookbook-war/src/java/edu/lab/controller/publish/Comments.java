/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.lab.controller.publish;

import edu.lab.modelo.Publicacion;
import edu.lab.modelo.Usuario;
import edu.lab.services.publish.PublishControllerBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author andresfelipegarciaduran
 */
public class Comments extends HttpServlet {

    PublishControllerBean publishControllerBean = lookupPublishControllerBeanBean();

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
        String comment = request.getParameter("comment-root");
        String idpublish = request.getParameter("id-publish");

        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("user-data");
        publishControllerBean.createPublication(idpublish, usuario, comment);
        List<Publicacion> obtenerPublicacionPorUsuario = publishControllerBean.obtenerPublicacionPorUsuario(usuario.getCorreo());
        session.setAttribute("publish-data", obtenerPublicacionPorUsuario);
        response.sendRedirect("user-home.jsp");
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

    private PublishControllerBean lookupPublishControllerBeanBean() {
        try {
            Context c = new InitialContext();
            return (PublishControllerBean) c.lookup("java:global/JEE_Cookbook/JEE_Cookbook-ejb/PublishControllerBean!edu.lab.services.publish.PublishControllerBean");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
