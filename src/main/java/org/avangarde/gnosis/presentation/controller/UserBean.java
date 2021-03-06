package org.avangarde.gnosis.presentation.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.avangarde.gnosis.businesslogic.facade.FacadeFactory;
import org.avangarde.gnosis.vo.ActivityVo;
import org.avangarde.gnosis.vo.ProgramVo;
import org.avangarde.gnosis.vo.PublicationVo;
import org.avangarde.gnosis.vo.StudentVo;
import org.avangarde.gnosis.vo.TutorVo;

/**
 *
 * @author Alexander
 */
@ManagedBean
@SessionScoped
public class UserBean implements Serializable {

    private int id;
    private String firstName;
    private String lastName;
    private String userName;
    private String aboutMe;
    private String btnValue;
    private String status;
    private int programId;
    private boolean loggedIn;
    private boolean active;
    private String urlPhoto;
    private List<ActivityVo> activities = new ArrayList<ActivityVo>();
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public void setBtnValue(String btnValue) {
        this.btnValue = btnValue;
    }

    public String getTutorPage() {
        return "success";
    }

    public List<ActivityVo> getActivities() {
        activities = new ArrayList<ActivityVo>();
        List<ActivityVo> vos = FacadeFactory.getInstance().getActivityFacade().getActivitiesBySubjectsOfStudent(getId());
        if (vos != null) {
            activities = vos;
        }
        return activities;
    }

    public void setActivities(List<ActivityVo> activities) {
        this.activities = activities;
    }

    public List<PublicationVo> getPublications() {
        StudentVo studentVo = FacadeFactory.getInstance().getStudentFacade().find(id);
        return publications = FacadeFactory.getInstance().getPublicationFacade().getPublicationsByStudent(studentVo.getId());
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

    public void validateAccount() {
        StudentVo user = FacadeFactory.getInstance().getStudentFacade().find(getId());
        if (user != null) {
            if (!user.isActive()) {
                setStatus("new");
                setActive(false);
                user.setActive(true);
                FacadeFactory.getInstance().getStudentFacade().update(user);
            } else {
                setStatus("active");
                setActive(true);
            }
        } else {
            setStatus("unregistred");
        }
    }

    public String getTutorID() {

        Integer TutorId = FacadeFactory.getInstance().getTutorFacade().findByUsername(getUserName());

        if (TutorId != null) {
            return TutorId.toString();
        } else {
            return null;
        }
    }
    
    public void update(){
        StudentVo student = FacadeFactory.getInstance().getStudentFacade().find(getId());
        student.setAboutMe(getAboutMe());
        student.setUrlPhoto(getUrlPhoto());
        FacadeFactory.getInstance().getStudentFacade().update(student);
    }
}
