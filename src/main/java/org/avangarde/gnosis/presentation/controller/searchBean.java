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
import javax.faces.model.SelectItem;
import org.avangarde.gnosis.businesslogic.facade.FacadeFactory;
import org.avangarde.gnosis.vo.ProgramVo;
import org.avangarde.gnosis.vo.SubjectVo;

/**
 *
 * @author Alexander
 */
@ManagedBean
@ViewScoped
public class searchBean implements Serializable{

    private String query;
    private List<SelectItem> subjects = new ArrayList<SelectItem>();
    @ManagedProperty(value = "#{userBean}")
    private UserBean user;

    public searchBean() {
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<SelectItem> getSubjects() {
        if (subjects.isEmpty()) {
            loadSubjects();
        }
        return subjects;
    }

    public void setSubjects(List<SelectItem> subjects) {
        this.subjects = subjects;
    }

    private void loadSubjects() {
        subjects = new ArrayList<SelectItem>();
        if (query == null || query.equals("")) {
            ProgramVo program = FacadeFactory.getInstance().getProgramFacade().find(user.getProgramId());
            if (program.getSubjectList() != null || program.getSubjectList().isEmpty()) {
                for (SubjectVo subject : program.getSubjectList()) {
                    subjects.add(new SelectItem(subject.getCode(), subject.getName()));
                }
            }
        } else {
            List<SubjectVo> searchedSubjects = FacadeFactory.getInstance().getSubjectFacade().getSubjectsByName(query);
            for (SubjectVo subject : searchedSubjects) {
                subjects.add(new SelectItem(subject.getCode(), subject.getName()));
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
