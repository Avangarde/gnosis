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

/**
 *
 * @author Alexander
 */
@ManagedBean
@ViewScoped
public class SearchBean implements Serializable{

    private String query;
    private List<SubjectVo> subjects = new ArrayList<SubjectVo>();
    @ManagedProperty(value = "#{userBean}")
    private UserBean user;

    public SearchBean() {
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<SubjectVo> getSubjects() {
        if (subjects.isEmpty()) {
            loadSubjects();
        }
        return subjects;
    }

    public void setSubjects(List<SubjectVo> subjects) {
        this.subjects = subjects;
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

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }
}
