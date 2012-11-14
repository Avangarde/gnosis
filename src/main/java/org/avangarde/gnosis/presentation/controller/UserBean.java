package org.avangarde.gnosis.presentation.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import org.avangarde.gnosis.businesslogic.facade.FacadeFactory;
import org.avangarde.gnosis.vo.ProgramVo;
import org.avangarde.gnosis.vo.PublicationVo;
import org.avangarde.gnosis.vo.StudentVo;
import org.avangarde.gnosis.vo.TutorVo;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author Alexander
 */
@ManagedBean
@SessionScoped
public class UserBean implements Serializable {

    private int id;
    private TreeNode root;
    private String firstName;
    private String lastName;
    private String userName;
    private String aboutMe;
    private int programId;
    private boolean loggedIn;
    private String urlPhoto = "http://userserve-ak.last.fm/serve/_/58531987/Unknown+_user.jpg";
    private TreeNode selectedNode;
    private List<PublicationVo> publications;

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public int getProgramId() {
        return programId;
    }

    public void setProgramId(int programId) {
        this.programId = programId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getProgramName() {
        ProgramVo program = FacadeFactory.getInstance().getProgramFacade().find(programId);
        return program.getName();
    }

    public boolean isBtnDisabled() {
        TutorVo tutorVo = new TutorVo();
        tutorVo.setStudentId(id);
        tutorVo.setUserName(userName);
        return (!FacadeFactory.getInstance().getStudentFacade().isTutor(tutorVo)) ? true : false;
    }

    public String getBtnValue() {
        return isBtnDisabled() ? "No" : "Si, ir a mi perfil de tutor";
    }

    public String getTutorPage() {
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

    public void preRenderView() {
        root = new DefaultTreeNode("root", null);
        StudentVo studentVo = FacadeFactory.getInstance().getStudentFacade().find(id);
        List<PublicationVo> publications = FacadeFactory.getInstance().getPublicationFacade().getPublicationsByStudent(studentVo.getId());
        for (PublicationVo publication : publications) {
            PublicationVo vo = new PublicationVo();
            vo.setTitle(publication.getTitle());
            new DefaultTreeNode(vo, root);
        }
    }

    public List<PublicationVo> getPublications() {
        StudentVo studentVo = FacadeFactory.getInstance().getStudentFacade().find(id);
        return publications=FacadeFactory.getInstance().getPublicationFacade().getPublicationsByStudent(studentVo.getId());
    }

    public void setPublications(List<PublicationVo> publications) {
        this.publications = publications;
    }
    
    public String logOut() {
        firstName = null;
        lastName = null;
        userName = null;
        programId = 0;
        loggedIn = false;
        return "logout";
    }
}
