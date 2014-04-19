/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.BasicConfigurator;

/**
 *
 * @author duran
 */
public class FilterWeb implements Filter {

    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        // Set up a simple configuration that logs on the console.
        BasicConfigurator.configure();
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
        String indexURI = "/faces/index.xhtml";

        boolean createSession = false;
        if (session != null) {
            Object object = session.getAttribute("session");
            if (object != null) {
                createSession = true;
            }
        }

        if (!createSession) {
            httpRequest.getRequestDispatcher(indexURI).forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }

}
