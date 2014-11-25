package Beans;

import Controller.FileController;
import Controller.PatentController;
import Entities.Patent;
import Utils.FileUtils;
import Utils.ServletUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

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

    private Patent patentUpdate = new Patent();

    private FileUtils fileUtils = new FileUtils();

    public BeanPatentManagement() {
    }

    @PostConstruct
    public void initialize() {
        searchFields();
        //findValuesTableAssignee();
        //findValuesTableClassification();
        //findValuesTableInventor();
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

    public void searchPatent(ActionEvent actionEvent) throws SQLException {
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

        File outputFile = null;
        byte[] bufferOutputFile = null;
        String contentType = null;

        if (file.getSize() != 0) {
            FileUtils utils = new FileUtils();
            String path = ServletUtils.getServletContext().getRealPath("");
            String fileName = utils.getFileName(file);
            contentType = ServletUtils.getServletContext().getMimeType(fileName);
            Object[] targets = utils.createFile(file, path + File.separator + "WEB-INF" + File.separator + fileName);
            outputFile = (File) targets[0];
            bufferOutputFile = (byte[]) targets[1];
        }

        boolean created = controller.createPatent(idPatent, titlePatent, publicDate, description, inventors, classification, assignee, contentType, outputFile, bufferOutputFile);
        if (created) {
            FacesMessage message = new FacesMessage("Succesful - Patent [" + idPatent + "-" + titlePatent + "] created.", "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Please, fill the fields with information valid.", "'" + this.getKeywordSearch() + "'"));
        }
        clearDialog();
    }

    public void onClickDeletePatent(Patent patent) throws SQLException {
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

    public void onClickDownload(Patent patent) throws FileNotFoundException {
        if (patent.getDocument() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No existe ningun adjunto para la patente [" + patent.getPatentId() + "-" + patent.getPatentTitle() + "]", ""));
        } else {
            SerialBlob blob = (SerialBlob) patent.getDocument();
            try {
                int index = patent.getDocumentExt().indexOf("/");
                String contentType = patent.getDocumentExt().substring(index + 1, patent.getDocumentExt().length());
                String nameFile = ("DOWNLOAD-" + patent.getPatentId()).toLowerCase() + "." + contentType;
                String pathComplete = ServletUtils.getServletContext().getRealPath("") + File.separator + "WEB-INF" + File.separator + nameFile;
                File fileCreated = fileUtils.createFile(blob.getBinaryStream(), pathComplete);
                FileController.downloadFile(nameFile, fileCreated);
            } catch (SerialException ex) {
                Logger.getLogger(BeanPatentManagement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void onClickEditAccept(RowEditEvent event) {
        Patent patent = (Patent) event.getObject();
        patentUpdate.setPatentId(patent.getPatentId());
        patentUpdate.setPatentTitle(patent.getPatentTitle());
        patentUpdate.setDescription(patent.getDescription());
        patentUpdate.setPublicDate(patent.getPublicDate());
        patentUpdate.setAssigneeList(patent.getAssigneeList());
        patentUpdate.setClassificationList(patent.getClassificationList());
        patentUpdate.setInventorList(patent.getInventorList());
        controller.updatePatent(patentUpdate);
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

    public Patent getPatentUpdate() {
        return patentUpdate;
    }

    public void setPatentUpdate(Patent patentUpdate) {
        this.patentUpdate = patentUpdate;
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
