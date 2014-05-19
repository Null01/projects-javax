package management;

import enumeration.ELabelsMessages;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import util.FacesUtil;

/**
 *
 * @author duran
 */
public class BeanAdminDB implements Serializable {

    // vista
    private boolean hideForm;

    // valores formulario
    private String query = "";
    private List<Object> listResultSet;

    public BeanAdminDB() {
    }

    public List<String> completeTextArea(String query) {
        return new ArrayList<>();
    }

    public void onClickViewResult(ActionEvent event) {
        this.query = this.query.trim();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JEE_SGR-ejbPU2");
        EntityManager em = emf.createEntityManager();
        try {
            Query q = em.createQuery(model.QueryDB.processQuery(query));
            List resultList = q.getResultList();
            this.listResultSet = resultList;
            this.hideForm = true;
            FacesUtil.getFacesUtil().addMessage(FacesMessage.SEVERITY_INFO, ELabelsMessages.SUCCESSFULL_ACTION_QUERY.getString(), "");
        } catch (Exception e) {
            System.err.println("ERROR - " + e);
            hideForm = false;
            FacesUtil.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, ELabelsMessages.FAILURE_ACTION_QUERY.getString(), "");
        }
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public boolean isHideForm() {
        return hideForm;
    }

    public void setHideForm(boolean hideForm) {
        this.hideForm = hideForm;
    }

    public List<Object> getListResultSet() {
        return listResultSet;
    }

    public void setListResultSet(List<Object> listResultSet) {
        this.listResultSet = listResultSet;
    }

}
