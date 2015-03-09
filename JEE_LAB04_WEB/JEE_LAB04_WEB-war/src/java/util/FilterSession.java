package util;

import java.io.IOException;
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
        HttpSession session = httpRequest.getSession(false);

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
        System.out.println(createSession);
        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }

}
