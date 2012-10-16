/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avangarde.gnosis.presentation.controller;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.avangarde.gnosis.businesslogic.facade.FacadeFactory;
import org.avangarde.gnosis.businesslogic.facade.PublicationFacade;
import org.avangarde.gnosis.vo.PublicationVo;
import org.primefaces.model.DefaultTreeNode;  
import org.primefaces.model.TreeNode;


/**
 *
 * @author andres
 */
@ManagedBean
@SessionScoped
public class ResourceBean implements Serializable{

    private TreeNode root; 
    private TreeNode selectedNode;
    private String title;
    private String topic;
    private String type;
    private String url;
    private String sharedBy;
    @ManagedProperty(value = "#{userBean}")
    private UserBean user;
    @ManagedProperty(value = "#{subjectBean}")
    private SubjectBean subject;
      
    public ResourceBean() {  
        root = new DefaultTreeNode("root", null);
        for (String topic : getTopics()){
            PublicationVo vo = new PublicationVo();
            vo.setTitle(topic);
            new DefaultTreeNode(vo, root);
        }
        
        for (TreeNode node : root.getChildren()){
            List<PublicationVo> publications = FacadeFactory.getInstance().getPublicationFacade().getPublicationsByTopic(((PublicationVo)node.getData()).getTitle());
            for (PublicationVo publication : publications){
                new DefaultTreeNode(publication, node);
            }
        }        
    }
    
    public List<String> getTopics() {
        List<String> topics = FacadeFactory.getInstance().getPublicationFacade().getTopics();
        return topics;
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
        
        return "success";
        
    }
    
    public String viewResource(){
        
        setTitle(((PublicationVo)getSelectedNode().getData()).getTitle());
        setTopic(((PublicationVo)getSelectedNode().getData()).getTopic());
        setType(((PublicationVo)getSelectedNode().getData()).getType());
        String url = ((PublicationVo)getSelectedNode().getData()).getUrl();        
        int edit = url.lastIndexOf("/");
        url = (url.substring(0, edit) + "/preview");
        setUrl(url);
        setSharedBy(((PublicationVo)getSelectedNode().getData()).getStudentName());
        
        return "success";
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

    public String getSharedBy() {
        return sharedBy;
    }

    public void setSharedBy(String sharedBy) {
        this.sharedBy = sharedBy;
    }

}
