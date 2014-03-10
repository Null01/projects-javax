/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
}
