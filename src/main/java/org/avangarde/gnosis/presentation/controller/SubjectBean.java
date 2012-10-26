package org.avangarde.gnosis.presentation.controller;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.avangarde.gnosis.businesslogic.facade.FacadeFactory;
import org.avangarde.gnosis.vo.SubjectVo;

/**
 *
 * @author Alexander
 */
@ManagedBean
@ViewScoped
public class SubjectBean implements Serializable{

    private Integer code;
    private String name;
    private String description;
    private int numGroups;

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
    
    public void preRenderView(){
        if (getCode() != null){
            SubjectVo subject = FacadeFactory.getInstance().getSubjectFacade().find(getCode());
            setName(subject.getName());
            setDescription(subject.getDescription());
            setNumGroups(subject.getNumGroups());
        }
    }
}
