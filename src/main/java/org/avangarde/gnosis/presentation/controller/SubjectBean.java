package org.avangarde.gnosis.presentation.controller;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.avangarde.gnosis.businesslogic.facade.FacadeFactory;
import org.avangarde.gnosis.vo.SubjectVo;

/**
 *
 * @author Alexander
 */
@ManagedBean
@SessionScoped
public class SubjectBean implements Serializable {

    private Integer code;
    private String name;
    private String description;
    private int numGroups;
    @ManagedProperty(value = "#{userBean}")
    private UserBean user;
    String buttonValue;

    public SubjectBean() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumGroups() {
        return numGroups;
    }

    public void setNumGroups(int numGroups) {
        this.numGroups = numGroups;
    }

    public void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public void subscribeStudent() {
        if ("Suscribirme a la materia".equals(buttonValue)) {
            if (FacadeFactory.getInstance().getSubjectFacade().subscribeStudent(new Integer(user.getId()), getCode())) {
                addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Te has suscrito a la materia" + getName(), ""));
            } else {
                addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "No te pudiste suscribir a la materia" + getName(), ""));
            }
        } else {
            if (FacadeFactory.getInstance().getSubjectFacade().unSubscribeStudent(new Integer(user.getId()), getCode())) {
                addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Has abandonado la materia" + getName(), ""));

            } else {
                addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "No pudiste abandonar la materia" + getName(), ""));
            }
        }
    }
    
    public void subscribeStudent(SubjectVo subject) {
        if ("Suscribirme a la materia".equals(buttonValue)) {
            if (FacadeFactory.getInstance().getSubjectFacade().subscribeStudent(new Integer(user.getId()), subject.getCode())) {
                addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Te has suscrito a la materia" + subject.getName(), ""));
            } else {
                addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "No te pudiste suscribir a la materia" + subject.getName(), ""));
            }
        } else {
            if (FacadeFactory.getInstance().getSubjectFacade().unSubscribeStudent(new Integer(user.getId()), subject.getCode())) {
                addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Has abandonado la materia" + subject.getName(), ""));

            } else {
                addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "No pudiste abandonar la materia" + subject.getName(), ""));
            }
        }
    }

    public String changeButtonValue() {
        return buttonValue = FacadeFactory.getInstance().getSubjectFacade().
                isTheStudentSubscribed(new Integer(user.getId()), getCode()) ? 
                "Abandonar" : "Suscribirme a la materia";
    }
    
    public String changeButtonValue(int subjectCode) {
        return buttonValue = FacadeFactory.getInstance().getSubjectFacade().
                isTheStudentSubscribed(new Integer(user.getId()), subjectCode) ? 
                "Abandonar" : "Suscribirme a la materia";
    }

    public void preRenderView() {
        if (getCode() != null) {
            SubjectVo subject = FacadeFactory.getInstance().getSubjectFacade().find(getCode());
            setName(subject.getName());
            setDescription(subject.getDescription());
            setNumGroups(subject.getNumGroups());
        }
    }
}
