package management;

import entities.LoginPK;
import entities.Usuario;
import enumeration.ELabelsCommon;
import enumeration.ELabelsError;
import enumeration.ELabelsMessages;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
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

    private static final Logger LOGGER = Logger.getLogger(BeanIndex.class);

    private String user = "usuario";
    private String password = "usuario";

    private final String forward = "faces/home.xhtml";  // navegate next page

    @PostConstruct
    public void initialize() {
    }

    public void ingresar(ActionEvent event) {
        int state = loginFacade.autenticarUsuario(new LoginPK(user, password));
        if (state == 0) {
            Usuario find = usuarioFacade.find(user);
            HttpSession httpSession = FacesUtil.getFacesUtil().getSession();
            httpSession.setAttribute("session", find);
            FacesUtil.getFacesUtil().redirect(forward);
            LOGGER.info(ELabelsCommon.OPEN.getString() + ELabelsCommon.SESSION.getString() + ELabelsCommon.LOGIN.getString() + find);
            FacesUtil.getFacesUtil().addMessage(FacesMessage.SEVERITY_INFO, ELabelsMessages.SUCCESSFULL_LOGIN.getString(), "");
        } else {
            if (state == -1) {
                FacesUtil.getFacesUtil().addMessage(FacesMessage.SEVERITY_WARN, ELabelsError.ERROR_AUTENTICACION.getString(), "");
            } else {
                FacesUtil.getFacesUtil().addMessage(FacesMessage.SEVERITY_WARN, ELabelsError.ERROR_AUTENTICACION_USUARIO_BLOQUEADO.getString(), "");
            }
        }
    }

    public void salir(ActionEvent event) {
        HttpSession session = FacesUtil.getFacesUtil().getSession();
        LOGGER.info(ELabelsCommon.CLOSE.getString() + ELabelsCommon.SESSION.getString() + ELabelsCommon.LOGOUT.getString() + session.getAttribute("session"));
        session.invalidate();
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
