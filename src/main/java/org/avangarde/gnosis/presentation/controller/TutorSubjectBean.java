/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avangarde.gnosis.presentation.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import org.avangarde.gnosis.businesslogic.facade.ActivityFacade;
import org.avangarde.gnosis.businesslogic.facade.FacadeFactory;
import org.avangarde.gnosis.businesslogic.facade.RatingFacade;
import org.avangarde.gnosis.vo.ActivityVo;
import org.avangarde.gnosis.vo.CommentVo;
import org.avangarde.gnosis.vo.PublicationVo;
import org.avangarde.gnosis.vo.RatingVo;
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
    @ManagedProperty(value = "#{userBean}")
    private UserBean user;
    private int id;
    private List<Integer> studentList;
    private List<StudentVo> students;
    private List<PublicationVo> publications = new ArrayList<PublicationVo>();
    private List<CommentVo> commentList = new ArrayList<CommentVo>();
    private Double reputation;
    private Integer reputationInt;
    private int vote;
    private int numVotes;
    private List<TutorSubjectVo> tutors = new ArrayList<TutorSubjectVo>();

    public List<PublicationVo> getPublications() {
        return publications;
    }

    public void setPublications(List<PublicationVo> publications) {
        this.publications = publications;
    }

    public int getNumVotes() {
        return numVotes;
    }

    public void setNumVotes(int numVotes) {
        this.numVotes = numVotes;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public Double getReputation() {
        return reputation;
    }

    public void setReputation(Double reputation) {
        this.reputation = reputation;
    }

    public Integer getReputationInt() {
        return reputationInt;
    }

    public void setReputationInt(Integer reputationInt) {
        this.reputationInt = reputationInt;
    }

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

    public List<TutorSubjectVo> getTutors() {
        if (tutors.isEmpty()) {
            loadMyTutors();
        }
        return tutors;
    }

    public void setTutors(List<TutorSubjectVo> tutors) {
        this.tutors = tutors;
    }

    private void loadMyTutors() {
        tutors = new ArrayList<TutorSubjectVo>();
        StudentVo student = FacadeFactory.getInstance().getStudentFacade().find(user.getId());
        for (TutorSubjectVo tutor : student.getTutorSubjectList()) {
            tutors.add(FacadeFactory.getInstance().getTutorSubjectFacade().find(tutor.getId()));
        }
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

            
            publications = FacadeFactory.getInstance().getPublicationFacade().getPublicationsByStudentAndTutorSubject(tutor.getStudentId(), getSubjectCode());

            if (tutorSubject.getReputation() != null) {
                setReputationInt(tutorSubject.getReputation().intValue());
                setReputation(tutorSubject.getReputation());
            } else {
                setReputationInt(0);
                setReputation(0.0);
            }

            setNumVotes(tutorSubject.getNumberVotes());

        }
    }

    void loadComments() {
        commentList = new ArrayList<CommentVo>();
        commentList = FacadeFactory.getInstance().getCommentFacade().getCommentsbyTutorSubject(getId());
    }

    public void rate() {
        RatingFacade ratingFacade = FacadeFactory.getInstance().getRatingFacade();

        RatingVo vo = new RatingVo();
        vo.setRating(getVote());
        vo.setStudentId(getUser().getId());
        vo.setTutorSubjectId(getId());

        ratingFacade.create(vo);

        ActivityFacade activityFacade = FacadeFactory.getInstance().getActivityFacade();

        ActivityVo activityVo = new ActivityVo();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
        activityVo.setDateActivity(format.format(new GregorianCalendar().getTime()));
        activityVo.setStudentId(getUser().getId());
        activityVo.setSubjectCode(getSubject().getCode());
        //Activity calificar tutor
        activityVo.setTutorId(getTutorId());
        activityVo.setType("Rating");

        activityFacade.create(activityVo);
    }

    public boolean isVoted() {
        return FacadeFactory.getInstance().getTutorSubjectFacade().isVotedByUser(getUser().getId(), getId());
    }
}
