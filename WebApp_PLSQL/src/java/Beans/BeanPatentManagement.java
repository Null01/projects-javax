package Beans;

import Controller.PatentController;
import Entities.Patent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;

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
    private Part file;

    // lista de inventores - tabla INVENTOR_PATENT
    private String inventors;
    private List<SelectItem> listInventors = new ArrayList<SelectItem>();
    // lista de clasificaciones - tabla CLASSIFICATION_PATENT
    private String classification;
    private List<SelectItem> listClassification = new ArrayList<SelectItem>();
    // lista de empresas patronicanadoras - tabla ASSIGEE_PATENT
    private String assignee;
    private List<SelectItem> listAssignee = new ArrayList<SelectItem>();

    private List<Patent> listPatentsResultSet = new ArrayList<Patent>();

    private PatentController controller = new PatentController();

    public BeanPatentManagement() {
    }

    @PostConstruct
    public void initialize() {
        searchFields();
        findValuesTableAssignee();
        findValuesTableClassification();
        findValuesTableInventor();
    }

    private void searchFields() {
        setListFieldKeywordSearch(new ArrayList<SelectItem>());
        SelectItemGroup group = new SelectItemGroup("");
        List<String> list = controller.getListFieldTablePatent();
        SelectItem items[] = new SelectItem[list.size()];
        for (int i = 0; i < list.size(); i++) {
            items[i] = new SelectItem(list.get(i), list.get(i));
        }
        group.setSelectItems(items);
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
        listPatentsResultSet.clear();
        List<Patent> list = controller.searchPatents(fieldKeywordSearch, keywordSearch);
        if (list.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No results found with ", "'" + this.getKeywordSearch() + "'"));
        } else {
            listPatentsResultSet.addAll(list);
        }
    }

    public void onClickCreatePatent(ActionEvent actionEvent) throws FileNotFoundException, IOException, SQLException {

        System.out.println(idPatent + " " + titlePatent + " " + publicDate + " " + description + " " + file);

        InputStream inputStream = null;
        OutputStream outputStream = null;
        FacesContext context = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) context
                .getExternalContext().getContext();
        String path = servletContext.getRealPath("");

        byte[] buffer = null;
        File outputFile = null;
        String fileName = "";
        if (file.getSize() != 0) {
            final String partHeader = file.getHeader("content-disposition");
            for (String content : partHeader.split(";")) {
                if (content.trim().startsWith("filename")) {
                    fileName = content.substring(content.indexOf('=') + 1)
                            .trim().replace("\"", "");
                }
            }
            outputFile = new File(path + File.separator + "WEB-INF"
                    + File.separator + fileName);

            inputStream = file.getInputStream();
            outputStream = new FileOutputStream(outputFile);
            buffer = new byte[1024];
            int bytesRead = 0;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            if (outputStream != null) {
                outputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }

        boolean created = controller.createPatent(idPatent, titlePatent, publicDate, description, inventors, classification, assignee, outputFile, buffer);
        if (created) {
            FacesMessage message = new FacesMessage("Succesful - Patent [" + idPatent + "-" + titlePatent + "] created.", "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Please, fill the fields with information valid.", "'" + this.getKeywordSearch() + "'"));
        }
        clearDialog();
    }

    public void onClickDeletePatent(Patent patent) {
        boolean deletePatent = controller.deletePatent(patent.getPatentId());
        if (deletePatent) {
            FacesMessage message = new FacesMessage("Succesful - Patent [" + idPatent + "-" + titlePatent + "] deleted.", "");
            FacesContext.getCurrentInstance().addMessage(null, message);
            listPatentsResultSet.clear();
            List<Patent> list = controller.searchPatents(fieldKeywordSearch, keywordSearch);
            if (!list.isEmpty()) {
                listPatentsResultSet.addAll(list);
            }
        }

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

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
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

    public List<Patent> getListPatentsResultSet() {
        return listPatentsResultSet;
    }

    public void setListPatentsResultSet(List<Patent> listPatentsResultSet) {
        this.listPatentsResultSet = listPatentsResultSet;
    }

    private void findValuesTableAssignee() {
        setListAssignee(new ArrayList<SelectItem>());
        SelectItemGroup group = new SelectItemGroup("");
        List<String> list = controller.getValuesTableAssignee();
        SelectItem items[] = new SelectItem[list.size()];
        for (int i = 0; i < list.size(); i++) {
            items[i] = new SelectItem(list.get(i), list.get(i));
        }
        group.setSelectItems(items);
        getListAssignee().add(group);
    }

    private void findValuesTableClassification() {
        setListClassification(new ArrayList<SelectItem>());
        SelectItemGroup group = new SelectItemGroup("");
        List<String> list = controller.getValuesTableClassification();
        SelectItem items[] = new SelectItem[list.size()];
        for (int i = 0; i < list.size(); i++) {
            items[i] = new SelectItem(list.get(i), list.get(i));
        }
        group.setSelectItems(items);
        getListClassification().add(group);
    }

    private void findValuesTableInventor() {
        setListInventors(new ArrayList<SelectItem>());
        SelectItemGroup group = new SelectItemGroup("");
        List<String> list = controller.getValuesTableInventor();
        SelectItem items[] = new SelectItem[list.size()];
        for (int i = 0; i < list.size(); i++) {
            items[i] = new SelectItem(list.get(i), list.get(i));
        }
        group.setSelectItems(items);
        getListInventors().add(group);
    }

}
