/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package management;

import entities.Funcion;
import entities.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import util.FacesUtil;

/**
 *
 * @author duran
 */
public class BeanHome implements Serializable {

    private TreeNode menu;
    private TreeNode selectedNode;
    private String pathForward;

    public BeanHome() {
    }

    @PostConstruct
    public void initialize() {
        HttpSession session = FacesUtil.getFacesUtil().getSession();
        Usuario usuario = (Usuario) session.getAttribute("session");
        List<Funcion> funcionList = usuario.getIdPerfil().getFuncionList();
        menu = new DefaultTreeNode("root", null);
        for (Funcion f : funcionList) {
            DefaultTreeNode defaultTreeNode = new DefaultTreeNode(f, menu);
            System.out.println(f.getNameFuncion());
        }
    }

    public void onNodeSelect(NodeSelectEvent event) {
        Funcion funcion = (Funcion) event.getTreeNode().getData();
        if (funcion != null) {
            System.out.println("-> " + funcion.getUrlFuncion());
            setPathForward(funcion.getUrlFuncion());
        }
    }

    public TreeNode getMenu() {
        return menu;
    }

    public void setMenu(TreeNode menu) {
        this.menu = menu;
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    public String getPathForward() {
        return pathForward;
    }

    public void setPathForward(String pathForward) {
        this.pathForward = pathForward;
    }

}
