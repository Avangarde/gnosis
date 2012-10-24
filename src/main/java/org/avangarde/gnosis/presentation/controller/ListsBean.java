package org.avangarde.gnosis.presentation.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import org.avangarde.gnosis.businesslogic.facade.FacadeFactory;
import org.avangarde.gnosis.vo.ProgramVo;
import org.avangarde.gnosis.vo.SubjectVo;

/**
 *
 * @author Alexander
 */
@ManagedBean
@RequestScoped
public class ListsBean implements Serializable {

    private List<SelectItem> programs;
    private List<SelectItem> topics;
    private List<SelectItem> subjectsByProgram;
    @ManagedProperty(value = "#{userBean}")
    private UserBean user;

    public ListsBean() {
    }

    public List<SelectItem> getPrograms() {
        if (programs == null) {
            programs = new ArrayList<SelectItem>();
            List<ProgramVo> programsList = FacadeFactory.getInstance().getProgramFacade().getList();
            if (programsList != null) {
                for (ProgramVo program : programsList) {
                    programs.add(new SelectItem(program.getCode(),
                            program.getName()));
                }
            }
        }
        return programs;
    }

    public List<SelectItem> getTopics() {
        if (topics == null) {
            topics = new ArrayList<SelectItem>();
            List<String> topicList = FacadeFactory.getInstance().getPublicationFacade().getTopics();
            if (topicList != null) {
                for (String topic : topicList) {
                    topics.add(new SelectItem(topic));
                }
            }
        }
        return topics;
    }
    
    public List<SelectItem> getSubjectsByProgram() {
        if (subjectsByProgram == null) {
            subjectsByProgram = new ArrayList<SelectItem>();
            ProgramVo program = FacadeFactory.getInstance().getProgramFacade().find(user.getProgramId());
            List<SubjectVo> subjects = FacadeFactory.getInstance().getSubjectFacade().getSubjectsByProgram(program.getCode());
            if (subjects != null) {
                for (SubjectVo subject : subjects) {
                    subjectsByProgram.add(new SelectItem(subject.getCode(),
                            subject.getName()));
                }
            }
        }
        return subjectsByProgram;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }
        
}
