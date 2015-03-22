package controller;

import edu.lab.modelo.Publicacion;
import edu.lab.modelo.Usuario;
import edu.lab.security.MonitorLogs;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import edu.lab.security.SecurityEncrypt;
import edu.lab.services.publish.PublishControllerBean;
import edu.lab.session.ITipoUsuario;
import edu.lab.session.SessionControllerBean;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author andresfelipegarciaduran
 */
public class Login extends HttpServlet {
    PublishControllerBean publishControllerBean = lookupPublishControllerBeanBean();

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

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            SecurityEncrypt security = new SecurityEncrypt();
            String encryptWithMD5 = security.encryptWithMD5(password);
            Usuario userRegistered = (Usuario) sessionControllerBean.isUserRegistered(email, encryptWithMD5);
            if (userRegistered != null) {
                HttpSession session = request.getSession(true);
                session.setAttribute("user-data", userRegistered);
                MonitorLogs.onlyChannel.writeLogUser(getServletContext(), new String[]{Login.class.getSimpleName(), userRegistered.getCorreo(), userRegistered.getApellido() + " " + userRegistered.getNombre()});
                if (userRegistered.getTipo().compareTo(ITipoUsuario.ADMIN) == 0) {
                    //request.getRequestDispatcher("admin.jsp").forward(request, response);
                    response.sendRedirect("admin-home.jsp");
                } else {
                    List<Publicacion> obtenerPublicacionPorUsuario = publishControllerBean.obtenerPublicacionPorUsuario(userRegistered.getCorreo());
                    System.out.println(obtenerPublicacionPorUsuario);
                    request.setAttribute("publish-data", obtenerPublicacionPorUsuario);
                    request.getRequestDispatcher("user-home.jsp").forward(request, response);
                }
            } else {
                response.sendRedirect("index.jsp");
            }
        } catch (Exception ex) {
            request.setAttribute("message-error-login", ex.getMessage());
            request.getRequestDispatcher("index.jsp").forward(request, response);
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
