/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package management;

import entities.Funcion;
import entities.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.TreeMap;
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
    private String pathForwardDialog;

    public BeanHome() {
    }

    @PostConstruct
    public void initialize() {
        HttpSession session = FacesUtil.getFacesUtil().getSession();
        Usuario usuario = (Usuario) session.getAttribute("session");
        if (usuario != null) {
            menu = buildTree(usuario.getIdPerfil().getFuncionList());
        }
    }

    private TreeNode buildTree(List<Funcion> funcionList) {
        TreeNode tree = new DefaultTreeNode("root", null);
        if (funcionList != null) {
            List<Funcion> rootList = new ArrayList<>();
            TreeMap<Integer, List<Funcion>> map = new TreeMap<>();
            for (Funcion f : funcionList) {
                if (f.getIdFuncion() == f.getIdFuncionPadre()) {
                    DefaultTreeNode defaultTreeNode = new DefaultTreeNode(f, tree); // Nodos principales del arbol.
                } else {
                    if (map.get(f.getIdFuncionPadre()) == null) {
                        map.put(f.getIdFuncionPadre(), new ArrayList<Funcion>());
                    }
                    map.get(f.getIdFuncionPadre()).add(f);
                }
            }
            Queue<TreeNode> queue = new LinkedList<>(tree.getChildren());
            while (!queue.isEmpty()) {
                TreeNode treeNode = queue.poll();
                if (treeNode != null) {
                    Funcion funcion = (Funcion) treeNode.getData();
                    if (map.get(funcion.getIdFuncion()) != null) {
                        for (Funcion f : map.get(funcion.getIdFuncion())) {
                            DefaultTreeNode defaultTreeNode = new DefaultTreeNode(f, treeNode);
                            queue.add(defaultTreeNode);
                        }
                    }
                }
            }
        }
        return tree;
    }

    public void onNodeSelect(NodeSelectEvent event) {
        Funcion funcion = (Funcion) event.getTreeNode().getData();
        if (funcion != null) {
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

    public String getPathForwardDialog() {
        return pathForwardDialog;
    }

    public void setPathForwardDialog(String pathForwardDialog) {
        this.pathForwardDialog = pathForwardDialog;
    }
}
