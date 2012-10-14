/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avangarde.gnosis.presentation.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import java.io.Serializable;  
import javax.faces.application.FacesMessage;  
import javax.faces.context.FacesContext;  
import org.avangarde.gnosis.entity.Publication;
  
import org.primefaces.model.DefaultTreeNode;  
import org.primefaces.model.TreeNode;

/**
 *
 * @author andres
 */
@ManagedBean
@ApplicationScoped
public class ResourceBean {

    private TreeNode root; 
    private TreeNode selectedNode;  
    private TreeNode[] selectedNodes;  
      
    public ResourceBean() {  
        root = new DefaultTreeNode("root", null);  
        TreeNode resource1 = new DefaultTreeNode(new Publication("Investigación Interactiva", "Tipos de Investigación", "Word Document"), root);
        TreeNode resource2 = new DefaultTreeNode(new Publication("Métodos para hallar puntos de función", "Puntos de Función", "Power Point Presentation"), root);
        TreeNode resource3 = new DefaultTreeNode(new Publication("Técnicas de estimación de costos", "Ingeniería de Software", "PDF Document"), root);
    }  
      
    public TreeNode getRoot() {  
        return root;  
    }  
  
    public void setRoot(TreeNode root) {  
        this.root = root;  
    }  
  
    public TreeNode getSelectedNode() {  
        return selectedNode;  
    }  
  
    public void setSelectedNode(TreeNode selectedNode) {  
        this.selectedNode = selectedNode;  
    }  
  
    public TreeNode[] getSelectedNodes() {  
        return selectedNodes;  
    }  
  
    public void setSelectedNodes(TreeNode[] selectedNodes) {  
        this.selectedNodes = selectedNodes;  
    }
}
