package Beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author andresfelipegarciaduran
 */
public class BeanPatentManagement implements Serializable {

    private String fieldKeywordSearch;
    private String keywordSearch;
    // lista de columnas de la tabla PATENT
    private List<SelectItem> listFieldKeywordSearch = new ArrayList<SelectItem>();

    // campos de la tabla PATENT
    private String idPatent;
    private String titlePatent;
    private Date publicDate;
    private String description;
    private UploadedFile file;

    // lista de inventores - tabla INVENTOR_PATENT
    private String inventors;
    private List<SelectItem> listInventors = new ArrayList<SelectItem>();
    // lista de clasificaciones - tabla CLASSIFICATION_PATENT
    private String classification;
    private List<SelectItem> listClassification = new ArrayList<SelectItem>();
    // lista de empresas patronicanadoras - tabla ASSIGEE_PATENT
    private String assignee;
    private List<SelectItem> listAssignee = new ArrayList<SelectItem>();

    public BeanPatentManagement() {
    }

    @PostConstruct
    public void initialize() {
        searchFields();
    }

    private void searchFields() {
        setListFieldKeywordSearch(new ArrayList<SelectItem>());
        SelectItemGroup group = new SelectItemGroup("");
        group.setSelectItems(new SelectItem[]{new SelectItem("1", "1"), new SelectItem("2", "2")});
        getListFieldKeywordSearch().add(group);
    }

    public void clearDialog() {
        this.idPatent = "";
        this.titlePatent = "";
        this.description = "";
        this.publicDate = null;
        this.file = null;
    }

    public void searchPatent(ActionEvent actionEvent) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No results found with ", "'" + this.getKeywordSearch() + "'"));
    }

    public void onClickCreatePatent(ActionEvent actionEvent) {
        if (file != null) {
            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No results found with ", "'" + this.getKeywordSearch() + "'"));
        }
        clearDialog();
    }

    public String getFieldKeywordSearch() {
        return fieldKeywordSearch;
    }

    public void setFieldKeywordSearch(String fieldKeywordSearch) {
        this.fieldKeywordSearch = fieldKeywordSearch;
    }

    public String getKeywordSearch() {
        return keywordSearch;
    }

    public void setKeywordSearch(String keywordSearch) {
        this.keywordSearch = keywordSearch;
    }

    public List<SelectItem> getListFieldKeywordSearch() {
        return listFieldKeywordSearch;
    }

    public void setListFieldKeywordSearch(List<SelectItem> listFieldKeywordSearch) {
        this.listFieldKeywordSearch = listFieldKeywordSearch;
    }

    public String getIdPatent() {
        return idPatent;
    }

    public void setIdPatent(String idPatent) {
        this.idPatent = idPatent;
    }

    public String getTitlePatent() {
        return titlePatent;
    }

    public void setTitlePatent(String titlePatent) {
        this.titlePatent = titlePatent;
    }

    public Date getPublicDate() {
        return publicDate;
    }

    public void setPublicDate(Date publicDate) {
        this.publicDate = publicDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SelectItem> getListInventors() {
        return listInventors;
    }

    public void setListInventors(List<SelectItem> listInventors) {
        this.listInventors = listInventors;
    }

    public List<SelectItem> getListClassification() {
        return listClassification;
    }

    public void setListClassification(List<SelectItem> listClassification) {
        this.listClassification = listClassification;
    }

    public List<SelectItem> getListAssignee() {
        return listAssignee;
    }

    public void setListAssignee(List<SelectItem> listAssignee) {
        this.listAssignee = listAssignee;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String getAssignee() {
        return assignee;
    }

    public String getClassification() {
        return classification;
    }

    public String getInventors() {
        return inventors;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public void setInventors(String inventors) {
        this.inventors = inventors;
    }

}
