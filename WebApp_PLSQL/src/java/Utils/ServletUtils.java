package Utils;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author andresfelipegarciaduran
 */
public class ServletUtils {

    public static ServletContext getServletContext() {
        FacesContext context = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) context
                .getExternalContext().getContext();
        return servletContext;
    }

    public static HttpServletRequest getHttpServletRequest() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        return (HttpServletRequest) facesContext.getExternalContext().getRequest();
    }

    public static HttpServletResponse getHttpServletResponse() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        return (HttpServletResponse) facesContext.getExternalContext().getResponse();
    }
}
