package edu.lab.util;

import edu.lab.modelo.Usuario;
import edu.lab.session.ITipoUsuario;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
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
        String indexURI = "/index.jsp";

        boolean createSession = false;
        if (session != null) {
            Object object = session.getAttribute("user-data");
            if (object != null) {
                createSession = true;
            }
        }

        String jsps[] = {"index", "contact", "gallery", "register", "menu", "about", "admin-home", "user-home"};
        String servlets[] = {"Login", "Logout", "Register"};
        Set<String> setJsps = new TreeSet<String>(Arrays.asList(jsps));
        if (tokens[tokens.length - 1].endsWith(".jsp")) {
            // Validacion .JPS
            session.setAttribute("current-page", tokens[tokens.length - 1]);
            tokens[tokens.length - 1] = tokens[tokens.length - 1].replace(".jsp", "");
            if (createSession) {
                Usuario attribute = (Usuario) session.getAttribute("user-data");
                try {
                    boolean userIsAdmin = attribute.getTipo().compareTo(ITipoUsuario.ADMIN) == 0;
                    if (userIsAdmin) {
                        if (tokens[tokens.length - 1].compareTo(jsps[jsps.length - 1]) == 0) {
                            session.setAttribute("current-page", jsps[jsps.length - 2] + ".jsp");
                            httpRequest.getRequestDispatcher("/" + jsps[jsps.length - 2] + ".jsp").forward(request, response);
                        } else {
                            chain.doFilter(request, response);
                        }
                    } else {
                        if (tokens[tokens.length - 1].compareTo(jsps[jsps.length - 2]) == 0) {
                            session.setAttribute("current-page", jsps[jsps.length - 1] + ".jsp");
                            httpRequest.getRequestDispatcher("/" + jsps[jsps.length - 1] + ".jsp").forward(request, response);
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
                    if (tokens[tokens.length - 1].compareTo(jsps[jsps.length - 2]) == 0 || tokens[tokens.length - 1].compareTo(jsps[jsps.length - 1]) == 0) {
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
