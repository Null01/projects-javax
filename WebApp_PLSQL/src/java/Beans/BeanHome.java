package Beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.event.ActionEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author andresfelipegarciaduran
 */
public class BeanHome implements Serializable {

    private TreeNode menu;
    private TreeNode selectedNode;
    private String pathForward;

    public BeanHome() {
        this.menu = buildTree();
    }

    private TreeNode buildTree() {
        TreeNode tree = new DefaultTreeNode("root", null);
        DefaultTreeNode defaultTreeNode1 = new DefaultTreeNode(new Node("Carga masiva", "/carga_masiva.xhtml"), tree);
        DefaultTreeNode defaultTreeNode2 = new DefaultTreeNode(new Node("Gestion patentes", "/gestion_patentes.xhtml"), tree);
        DefaultTreeNode defaultTreeNode3 = new DefaultTreeNode(new Node("Carga dinamica", "/carga_dinamica.xhtml"), tree);
        return tree;
    }

    public void executeLoad(ActionEvent actionEvent) {
        try {
            Thread.sleep(4000);
            System.out.println("hola mundo");
        } catch (InterruptedException ex) {
            Logger.getLogger(BeanHome.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void onNodeSelect(NodeSelectEvent event) {
        Node funcion = (Node) event.getTreeNode().getData();
        if (funcion != null) {
            setPathForward(funcion.getPathFunction());
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

    public class Node implements Serializable {

        private String nameFunction;
        private String pathFunction;

        public Node(String nameFunction, String pathFunction) {
            this.nameFunction = nameFunction;
            this.pathFunction = pathFunction;
        }

        public String getNameFunction() {
            return nameFunction;
        }

        public String getPathFunction() {
            return pathFunction;
        }

        public void setNameFunction(String nameFunction) {
            this.nameFunction = nameFunction;
        }

        public void setPathFunction(String pathFunction) {
            this.pathFunction = pathFunction;
        }

    }

}
