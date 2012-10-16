package org.avangarde.gnosis.presentation.controller;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Alexander
 */
@ManagedBean
@RequestScoped
public class TestBean implements Serializable{

    @ManagedProperty(value = "#{subjectBean}")
    private SubjectBean subject;
    
    public TestBean() {
    }

    public String subject() {
        
        getSubject().setName("Taller de Proyectos de Ingenieria");
        getSubject().setCode(2016786);
        
        return "success";
    }

    public SubjectBean getSubject() {
        return subject;
    }

    public void setSubject(SubjectBean subject) {
        this.subject = subject;
    }
    
    
    
}
