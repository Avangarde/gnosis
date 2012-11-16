/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avangarde.gnosis.presentation.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.avangarde.gnosis.businesslogic.facade.FacadeFactory;
import org.avangarde.gnosis.vo.ProgramVo;
import org.avangarde.gnosis.vo.SubjectVo;
import org.avangarde.gnosis.vo.TutorSubjectVo;

/**
 *
 * @author Alexander
 */
@ManagedBean
@ViewScoped
public class SearchBean implements Serializable{

    private String query;
    private List<SubjectVo> subjects = new ArrayList<SubjectVo>();
    private List<TutorSubjectVo> tutors = new ArrayList<TutorSubjectVo>();
    @ManagedProperty(value = "#{userBean}")
    private UserBean user;
    @ManagedProperty(value = "#{subjectBean}")
    private SubjectBean subjectBean;

    public SearchBean() {
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

     public List<SubjectVo> getSubjects() {
        loadSubjects();        
        return subjects;
    }

    public void setSubjects(List<SubjectVo> subjects) {
        this.subjects = subjects;
    }

    public List<TutorSubjectVo> getTutors() {
        if (tutors.isEmpty()) {
            loadTutors();
        }
        return tutors;
    }

    public void setTutors(List<TutorSubjectVo> tutors) {
        this.tutors = tutors;
    }
    
    private void loadSubjects() {
        subjects = new ArrayList<SubjectVo>();
        if (query == null || query.equals("")) {
            ProgramVo program = FacadeFactory.getInstance().getProgramFacade().find(user.getProgramId());
            if (program.getSubjectList() != null || program.getSubjectList().isEmpty()) {
                for (SubjectVo subject : program.getSubjectList()) {
                    subjects.add(subject);
                }
            }
        } else {
            List<SubjectVo> searchedSubjects = FacadeFactory.getInstance().getSubjectFacade().getSubjectsByName(query);
            for (SubjectVo subject : searchedSubjects) {
                subjects.add(subject);
            }
        }
    }
    
    private void loadTutors() {
        tutors = new ArrayList<TutorSubjectVo>();
        if (query == null || query.equals("")) {
            SubjectVo subject = FacadeFactory.getInstance().getSubjectFacade().find(subjectBean.getCode());
            if (subject.getTutorSubjectList() != null || subject.getTutorSubjectList().isEmpty()) {
                for (TutorSubjectVo tutor : subject.getTutorSubjectList()) {
                    tutors.add(tutor);
                }
            }
        } else {
            List<TutorSubjectVo> searchedTutors = FacadeFactory.getInstance().getTutorSubjectFacade().getTutorsByName(query);
            for (TutorSubjectVo tutor : searchedTutors) {
               tutors.add(tutor);
            }
        }
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public SubjectBean getSubjectBean() {
        return subjectBean;
    }

    public void setSubjectBean(SubjectBean subjectBean) {
        this.subjectBean = subjectBean;
    }   
     
}
