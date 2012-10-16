package org.avangarde.gnosis.presentation.controller;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import org.avangarde.gnosis.businesslogic.facade.FacadeFactory;
import org.avangarde.gnosis.vo.*;

/**
 *
 * @author Alexander
 */
@ManagedBean
@RequestScoped
public class ListsBean {

    private List<SelectItem> programs;
    private List<SelectItem> subjects;
    
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
    public List<SelectItem> getSubjects() {
        if (subjects == null) {
            subjects = new ArrayList<SelectItem>();
            List<SubjectVo> subjectsList = FacadeFactory.getInstance().getSubjectFacade().getList();
            if (subjectsList != null) {
                for (SubjectVo program : subjectsList) {
                    subjects.add(new SelectItem(program.getCode(),
                            program.getName()));
                }
            }
        }
        return subjects;
    }    
    
}
