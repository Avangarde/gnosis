/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avangarde.gnosis.presentation.controller;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import org.avangarde.gnosis.businesslogic.facade.FacadeFactory;
import org.avangarde.gnosis.vo.CommentVo;
import org.avangarde.gnosis.vo.StudentVo;
import org.avangarde.gnosis.vo.TutorSubjectVo;

/**
 *
 * @author zergio
 */
@ManagedBean
@ViewScoped
public class TutorSubjectBean {

    public Integer subjectCode;
    public Integer tutorId;
    @ManagedProperty(value = "#{subjectBean}")
    private SubjectBean subject;
    @ManagedProperty(value = "#{tutorBean}")
    private TutorBean tutor;
    private int id;
    private List<Integer> studentList;
    private List<StudentVo> students;
    private List<CommentVo> commentList = new ArrayList<CommentVo>();

    public List<StudentVo> getStudents() {
        return students;
    }

    public void setStudents(List<StudentVo> students) {
        this.students = students;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Integer> studentList) {
        this.studentList = studentList;
    }

    public List<CommentVo> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<CommentVo> commentList) {
        this.commentList = commentList;
    }

    public Integer getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(Integer subjectCode) {
        this.subjectCode = subjectCode;
    }

    public Integer getTutorId() {
        return tutorId;
    }

    public void setTutorId(Integer tutorId) {
        this.tutorId = tutorId;
    }

    public TutorBean getTutor() {
        return tutor;
    }

    public void setTutor(TutorBean tutor) {
        this.tutor = tutor;
    }

    public SubjectBean getSubject() {
        return subject;
    }

    public void setSubject(SubjectBean subject) {
        this.subject = subject;
    }

    public void preRenderView() {

        if (getTutorId() != null & getSubjectCode() != null) {
            TutorSubjectVo tutorSubject = FacadeFactory.getInstance().getTutorSubjectFacade().findTutorOnSubject(getTutorId(), getSubjectCode());

            if (tutorSubject.getCommentList() != null) {
                setCommentList(tutorSubject.getCommentList());
            } else {
                setCommentList(null);
            }
            setId(tutorSubject.getId());
            if (tutorSubject.getStudentList() != null) {
                setStudentList(tutorSubject.getStudentList());
            } else {
                setStudentList(null);
            }

            students = new ArrayList<StudentVo>();
            for (Integer each : studentList) {
                StudentVo student = FacadeFactory.getInstance().getStudentFacade().find(each);
                students.add(student);

            }

        }
    }

    void loadComments() {
        commentList = new ArrayList<CommentVo>();
        commentList = FacadeFactory.getInstance().getCommentFacade().getCommentsbyTutorSubject(getId());
    }
}
