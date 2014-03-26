/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author duran
 * @version 1.0
 */
public class FacesUtil {

    private static FacesUtil facesUtil;

    private FacesUtil() {
    }

    public static FacesUtil getFacesUtil() {
        if (facesUtil == null) {
            facesUtil = new FacesUtil();
        }
        return facesUtil;
    }

    public HttpSession getSession() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        return request.getSession(true);
    }

    public void redirect(String string) {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.getExternalContext().redirect(string);
        } catch (IOException ex) {
            Logger.getLogger(FacesUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addMessage(FacesMessage.Severity SEVERITY, String string, String toString) {
        FacesMessage message = new FacesMessage(SEVERITY, string, toString);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public String getContextPath() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        return request.getContextPath();
    }

    public HttpServletRequest getHttpServletRequest() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        return (HttpServletRequest) facesContext.getExternalContext().getRequest();
    }

    public void execute(String string) {
        RequestContext.getCurrentInstance().execute(string);
    }

    public void openDialog(String string, Map<String, Object> options) {
        RequestContext.getCurrentInstance().openDialog(string, options, null);
    }

    public void closeDialog(Object object) {
        RequestContext.getCurrentInstance().closeDialog(object);
    }

    public void update(String string) {
        RequestContext.getCurrentInstance().update(string);
    }
}
