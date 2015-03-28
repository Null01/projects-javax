package edu.lab.controller.user;

import edu.lab.modelo.Usuario;
import edu.lab.security.SecurityEncrypt;
import edu.lab.services.user.UserControllerBean;
import edu.lab.session.SessionControllerBean;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author andresfelipegarciaduran
 */
public class UpdateUser extends HttpServlet {

    @EJB
    private SessionControllerBean sessionControllerBean;

    @EJB
    private UserControllerBean userControllerBean;

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
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String password = request.getParameter("password");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        SecurityEncrypt encrypt = new SecurityEncrypt();
        try {
            Usuario usuario = (Usuario) request.getSession().getAttribute("user-data");
            String email = usuario.getCorreo();
            password = encrypt.encryptWithMD5(password);
            newPassword = (newPassword == null || newPassword.isEmpty()) ? password : encrypt.encryptWithMD5(newPassword);
            confirmPassword = (confirmPassword == null || confirmPassword.isEmpty()) ? newPassword : encrypt.encryptWithMD5(confirmPassword);
            userControllerBean.actualizarDatosUsuario(fname, lname, email, password, newPassword, confirmPassword);
            HttpSession session = request.getSession();
            Usuario userRegistered = sessionControllerBean.isUserRegistered(email, newPassword);
            session.setAttribute("user-data", userRegistered);
            request.removeAttribute("message-error-update-user");
            response.sendRedirect("user-home.jsp");
        } catch (Exception ex) {
            System.out.println(ex);
            request.setAttribute("message-error-update-user", ex.getMessage());
            request.getRequestDispatcher("user-home.jsp").forward(request, response);
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
