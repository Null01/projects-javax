package util;

import Modelo.Usuario;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.InterpreterDB;

/**
 *
 * @author andresfelipegarciaduran
 */
@WebFilter(filterName = "FilterSession", urlPatterns = {"/*"})
public class FilterSession implements Filter {

    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        // Set up a simple configuration that logs on the console.
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestURI = httpRequest.getRequestURI();
        String tokens[] = requestURI.split("/");
        HttpSession session = httpRequest.getSession();

        String scheme = httpRequest.getScheme();             // http
        String serverName = httpRequest.getServerName();     // hostname
        int serverPort = httpRequest.getServerPort();        // 8080
        String contextPath = httpRequest.getContextPath();   // /mywebapp

        String path = scheme + "://" + serverName + ":" + serverPort + contextPath;
        String indexURI = "/index.jsp";

        boolean createSession = false;
        if (session != null) {
            Object object = session.getAttribute("user_data");
            if (object != null) {
                createSession = true;
            }
        }

        String jsps[] = {"index", "contact", "pop-ups", "admin", "user"};
        String servlets[] = {"Login", "Logout", "Register"};
        Set<String> setServlets = new TreeSet<String>(Arrays.asList(servlets));
        Set<String> setJsps = new TreeSet<String>(Arrays.asList(jsps));
        if (tokens[tokens.length - 1].endsWith(".jsp")) {
            // Validacion .JPS
            tokens[tokens.length - 1] = tokens[tokens.length - 1].replace(".jsp", "");
            if (createSession) {
                Usuario attribute = (Usuario) session.getAttribute("user_data");
                try {
                    boolean userIsAdmin = InterpreterDB.onlyThread.userIsAdmin(attribute.getCorreo());
                    if (userIsAdmin) {
                        if (tokens[tokens.length - 1].compareTo(jsps[4]) == 0) {
                            httpRequest.getRequestDispatcher("/admin.jsp").forward(request, response);
                        } else {
                            chain.doFilter(request, response);
                        }
                    } else {
                        if (tokens[tokens.length - 1].compareTo(jsps[3]) == 0) {
                            httpRequest.getRequestDispatcher("/user.jsp").forward(request, response);
                        } else {
                            chain.doFilter(request, response);
                        }
                    }
                } catch (Exception ex) {
                    httpRequest.getRequestDispatcher(indexURI).forward(request, response);
                }
            } else {
                if (!setJsps.contains(tokens[tokens.length - 1])) {
                    httpRequest.getRequestDispatcher(indexURI).forward(request, response);
                } else {
                    if (tokens[tokens.length - 1].compareTo(jsps[3]) == 0 || tokens[tokens.length - 1].compareTo(jsps[4]) == 0) {
                        httpRequest.getRequestDispatcher(indexURI).forward(request, response);
                    } else {
                        chain.doFilter(request, response);
                    }
                }
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }

}
