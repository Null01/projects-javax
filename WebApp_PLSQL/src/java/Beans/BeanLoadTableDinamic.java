package Beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
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

    public BeanLoadTableDinamic() {
    }

    @PostConstruct
    public void initialize() {
        searchTables();
        searchFieldsFromTable();
    }

    public void createTableDinamic(ActionEvent actionEvent) {
        System.out.println("asdasd");
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
                    if (j == 0) {
                        newGroup.setSelectItems(new SelectItem[]{new SelectItem("Field1", "Field1"), new SelectItem("Field2", "Field2")});
                    }
                    if (j == 1) {
                        newGroup.setSelectItems(new SelectItem[]{new SelectItem("Field3", "Field3"), new SelectItem("Field4", "Field4")});
                    }
                    getListFields().add(newGroup);
                }
            }
        }

    }

    private void searchTables() {
        setListTables(new ArrayList<SelectItem>());
        SelectItemGroup group = new SelectItemGroup("");
        group.setSelectItems(new SelectItem[]{new SelectItem("Tabla1", "Tabla1"), new SelectItem("Tabla2", "Tabla2")});
        getListTables().add(group);
    }

    private void searchFieldsFromTable() {
        setListFields(new ArrayList<SelectItem>());
        SelectItemGroup group = new SelectItemGroup("");
        group.setSelectItems(new SelectItem[]{new SelectItem("Field1", "Field1"), new SelectItem("Field2", "Field2")});
        getListFields().add(group);
    }

}
