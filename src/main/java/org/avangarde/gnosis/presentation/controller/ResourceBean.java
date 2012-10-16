/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avangarde.gnosis.presentation.controller;

import java.util.GregorianCalendar;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.avangarde.gnosis.businesslogic.facade.FacadeFactory;
import org.avangarde.gnosis.businesslogic.facade.PublicationFacade;
import org.avangarde.gnosis.entity.Publication;
import org.avangarde.gnosis.vo.PublicationVo;
import org.primefaces.model.DefaultTreeNode;  
import org.primefaces.model.TreeNode;

/**
 *
 * @author andres
 */
@ManagedBean
@SessionScoped
public class ResourceBean {

    private TreeNode root; 
    private TreeNode selectedNode;  
    private TreeNode[] selectedNodes;
    private String title;
    private String topic;
    private String type;
    private String url;
    @ManagedProperty(value = "#{userBean}")
    private UserBean user;
    @ManagedProperty(value = "#{subjectBean}")
    private SubjectBean subject;
      
    public ResourceBean() {  
        root = new DefaultTreeNode("root", null);  
        TreeNode resource1 = new DefaultTreeNode(new Publication("Investigación Interactiva", "Tipos de Investigación", "Word Document"), root);
        TreeNode resource2 = new DefaultTreeNode(new Publication("Métodos para hallar puntos de función", "Puntos de Función", "Power Point Presentation"), root);
        TreeNode resource3 = new DefaultTreeNode(new Publication("Técnicas de estimación de costos", "Ingeniería de Software", "PDF Document"), root);
    }
    
    public String saveResource(){
        
        PublicationFacade facade = FacadeFactory.getInstance().getPublicationFacade();
        
        PublicationVo vo = new PublicationVo();
        vo.setTitle(getTitle());
        vo.setUrl(getUrl());
        vo.setTopic(getTopic());
        vo.setType(getType());
        vo.setDate(new GregorianCalendar().getTime());
        vo.setStudentId(getUser().getId());
        vo.setSubjectCode(getSubject().getCode());
        
        facade.create(vo);
        
        return "succes";
        
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public SubjectBean getSubject() {
        return subject;
    }

    public void setSubject(SubjectBean subject) {
        this.subject = subject;
    }
}
