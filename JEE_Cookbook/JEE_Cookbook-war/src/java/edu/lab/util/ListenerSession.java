package edu.lab.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 *
 * @author andresfelipegarciaduran
 */
public class ListenerSession implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        ServletContext contexto = se.getSession().getServletContext();
        Integer usuarioConectados = null;
        synchronized (contexto) {
            usuarioConectados = (Integer) contexto.getAttribute("usuariosConectados");
            if (usuarioConectados == null) {
                usuarioConectados = new Integer("0");
            }
            ++usuarioConectados;
            contexto.setAttribute("usuariosConectados", usuarioConectados);
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        ServletContext contexto = se.getSession().getServletContext();
        Integer usuarioConectados = null;
        synchronized (contexto) {
            usuarioConectados = (Integer) contexto.getAttribute("usuariosConectados");
            if (usuarioConectados == null) {
                usuarioConectados = new Integer("0");
            }
            --usuarioConectados;
            contexto.setAttribute("usuariosConectados", usuarioConectados);
        }
    }

}
