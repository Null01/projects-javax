package controller;

import edu.lab.modelo.Usuario;
import edu.lab.security.MonitorLogs;
import java.io.IOException;
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
public class Login extends HttpServlet {

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
                    //request.getRequestDispatcher("user.jsp").forward(request, response);
                    response.sendRedirect("user-home.jsp");
                }
            } else {
                response.sendRedirect("index.jsp");
            }
        } catch (Exception ex) {
            System.out.println(ex);
            //request.getRequestDispatcher("index.jsp").forward(request, response);
            response.sendRedirect("index.jsp");
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
