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
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import org.avangarde.gnosis.businesslogic.facade.FacadeFactory;
import org.avangarde.gnosis.entity.Tutor;
import org.avangarde.gnosis.vo.StudentVo;
import org.avangarde.gnosis.vo.SubjectVo;
import org.avangarde.gnosis.vo.TutorSubjectVo;
import org.avangarde.gnosis.vo.TutorVo;
import org.primefaces.model.DualListModel;

/**
 *
 * @author andres
 */
@ManagedBean
@ViewScoped
public class SubjectListsBean implements Serializable {

    private List<TutorVo> tutors = new ArrayList<TutorVo>();
    private List<StudentVo> students = new ArrayList<StudentVo>();
    @ManagedProperty(value = "#{subjectBean}")
    private SubjectBean subjectBean;

    public List<StudentVo> getStudents() {
        loadStudents();

        return students;
    }

    public void setStudents(List<StudentVo> students) {
        this.students = students;
    }

    public SubjectBean getSubjectBean() {
        return subjectBean;
    }

    public void setSubjectBean(SubjectBean subjectBean) {
        this.subjectBean = subjectBean;
    }

    public SubjectListsBean() {
    }

    public List<TutorVo> getTutors() {

        loadTutors();

        return tutors;
    }

    public void setTutors(List<TutorVo> tutors) {
        this.tutors = tutors;
    }

    public void loadTutors() {
        List<TutorVo> availables = new ArrayList<TutorVo>();

        //Codigo para obtener todos los tutores

        SubjectVo subjectVo = FacadeFactory.getInstance().getSubjectFacade().find(subjectBean.getCode());

        ArrayList<TutorSubjectVo> tutorSubjects = (ArrayList<TutorSubjectVo>) subjectVo.getTutorSubjectList();

        for (TutorSubjectVo tutorSubject : tutorSubjects) {
            TutorVo tutorVo = new TutorVo();
            tutorVo.setUserName(tutorSubject.getUserName());
            tutorVo = FacadeFactory.getInstance().getTutorFacade().findByUsername(tutorVo);

            availables.add(tutorVo);
        }


        tutors = new ArrayList<TutorVo>(availables);
    }

    public void loadStudents() {
        List<StudentVo> availables = new ArrayList<StudentVo>();

        //Codigo para obtener todos los tutores

        SubjectVo subjectVo = FacadeFactory.getInstance().getSubjectFacade().find(subjectBean.getCode());

        availables = (ArrayList<StudentVo>) subjectVo.getStudentList();


        students = new ArrayList<StudentVo>(availables);
    }
}
