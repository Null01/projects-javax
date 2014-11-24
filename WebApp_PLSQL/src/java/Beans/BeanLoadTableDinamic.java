package Beans;

import Controller.ProcedureController;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

/**
 *
 * @author andresfelipegarciaduran
 */
public class BeanLoadTableDinamic implements Serializable {

    private String tableSelected;
    private List<SelectItem> listTables = new ArrayList<SelectItem>();

    private String fieldSelected;
    private List<SelectItem> listFields = new ArrayList<SelectItem>();

    private Integer nFields;

    private String nameNewTable;

    private ProcedureController controller = new ProcedureController();

    public BeanLoadTableDinamic() {
    }

    @PostConstruct
    public void initialize() {
        searchTables();
        tableSelected = (listTables != null && listTables.size() >= 1) ? listTables.get(0).getLabel() : "";
        searchFieldsFromTable();
    }

    public void createTableDinamic(ActionEvent actionEvent) throws SQLException {
        boolean isValid = controller.isPossibleCreateTable(nameNewTable);
        if (isValid) {
            controller.callProcedureEncrypted(nameNewTable, tableSelected, fieldSelected, nFields);
            searchTables();
            searchFieldsFromTable();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "The procedure execute sucessfull", ""));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "The name of table already exist", ""));
        }

    }

    public String getNameNewTable() {
        return nameNewTable;
    }

    public void setNameNewTable(String nameNewTable) {
        this.nameNewTable = nameNewTable;
    }

    public String getTableSelected() {
        return tableSelected;
    }

    public void setTableSelected(String tableSelected) {
        this.tableSelected = tableSelected;
    }

    public List<SelectItem> getListTables() {
        return listTables;
    }

    public void setListTables(List<SelectItem> listTables) {
        this.listTables = listTables;
    }

    public String getFieldSelected() {
        return fieldSelected;
    }

    public void setFieldSelected(String fieldSelected) {
        this.fieldSelected = fieldSelected;
    }

    public List<SelectItem> getListFields() {
        return listFields;
    }

    public void setListFields(List<SelectItem> listFields) {
        this.listFields = listFields;
    }

    public Integer getnFields() {
        return nFields;
    }

    public void setnFields(Integer nFields) {
        this.nFields = nFields;
    }

    public void loadNewsFields() {
        SelectItemGroup newGroup = new SelectItemGroup();
        for (SelectItem group : listTables) {
            SelectItemGroup g = (SelectItemGroup) group;
            SelectItem[] items = g.getSelectItems();
            setListFields(new ArrayList<SelectItem>());
            for (int j = 0; j < items.length; j++) {
                if (items[j].getLabel().compareTo(tableSelected) == 0) {
                    searchFieldsFromTable();
                    break;
                }
            }
        }

    }

    private void searchTables() {
        setListTables(new ArrayList<SelectItem>());
        SelectItemGroup group = new SelectItemGroup("");
        List<String> list = controller.getAllTables();
        SelectItem items[] = new SelectItem[list.size()];
        for (int i = 0; i < list.size(); i++) {
            items[i] = new SelectItem(list.get(i), list.get(i));
        }
        group.setSelectItems(items);
        getListTables().add(group);
    }

    private void searchFieldsFromTable() {
        setListFields(new ArrayList<SelectItem>());
        SelectItemGroup group = new SelectItemGroup("");
        List<String> list = controller.getAllFieldsForTable(tableSelected);
        if (list != null && !list.isEmpty()) {
            SelectItem items[] = new SelectItem[list.size()];
            for (int i = 0; i < list.size(); i++) {
                items[i] = new SelectItem(list.get(i), list.get(i));
            }
            group.setSelectItems(items);
            getListFields().add(group);
        }
    }

}
