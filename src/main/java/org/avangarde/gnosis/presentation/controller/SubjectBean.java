package org.avangarde.gnosis.presentation.controller;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.avangarde.gnosis.businesslogic.facade.FacadeFactory;
import org.avangarde.gnosis.businesslogic.facade.SubjectFacade;

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
        if (FacadeFactory.getInstance().getSubjectFacade().subscribeStudent(new Integer(user.getId()), getCode())) {
            addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Te has suscribido a la materia", ""));
        } else {
            addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "No te pudiste suscribir a la materia", ""));
        }
    }
}
