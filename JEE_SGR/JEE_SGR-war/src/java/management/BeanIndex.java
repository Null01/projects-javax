package management;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import entities.LoginPK;
import entities.Usuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.servlet.http.HttpSession;
import session.LoginFacadeLocal;
import session.UsuarioFacadeLocal;
import util.FacesUtil;

/**
 *
 * @author duran
 */
public class BeanIndex implements Serializable {

    @EJB
    private UsuarioFacadeLocal usuarioFacade;

    @EJB
    private LoginFacadeLocal loginFacade;

    private String user = "admin";
    private String password = "admin";

    @PostConstruct
    public void initialize() {
    }

    public void ingresar() {
        boolean state = loginFacade.autenticarUsuario(new LoginPK(user, password));
        if (state) {
            Usuario find = usuarioFacade.find(user);
            HttpSession httpSession = FacesUtil.getFacesUtil().getSession();
            httpSession.setAttribute("session", find);
            FacesUtil.getFacesUtil().redirect("faces/home.xhtml");
        } else {
            FacesUtil.getFacesUtil().addMessage(FacesMessage.SEVERITY_WARN, "Error!", "Intentelo de nuevo.");
        }
    }

    public void salir() {
        HttpSession httpSession = FacesUtil.getFacesUtil().getSession();
        httpSession.invalidate();
        FacesUtil.getFacesUtil().redirect(FacesUtil.getFacesUtil().getContextPath());
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
