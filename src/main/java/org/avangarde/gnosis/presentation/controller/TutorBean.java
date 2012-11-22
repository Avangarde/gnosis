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
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import org.avangarde.gnosis.businesslogic.facade.FacadeFactory;
import org.avangarde.gnosis.vo.ActivityVo;
import org.avangarde.gnosis.vo.StudentVo;
import org.avangarde.gnosis.vo.SubjectVo;
import org.avangarde.gnosis.vo.TutorSubjectVo;
import org.avangarde.gnosis.vo.TutorVo;

/**
 *
 * @author zergio
 */
@ManagedBean
@ViewScoped
public class TutorBean implements Serializable {

    private Integer id;
    private double reputation;
    private String userName;
    private int numberVotes;
    private int publishedResources;
    private int questionReceived;
    private int numberStudents;
    private int studentId;
    private List<Integer> students = new ArrayList<Integer>();
    private List<Integer> subjects = new ArrayList<Integer>();
    private List<ActivityVo> activityList;
    private List<TutorSubjectVo> tutorSubjectList;
    private String urlPhoto;
    private List<TutorSubjectVo> tutors = new ArrayList<TutorSubjectVo>();
    @ManagedProperty(value = "#{userBean}")
    private UserBean user;
    
    //ManagedBeans
//    @ManagedProperty(value = "#{subjectBean}")
//    private SubjectBean subject;

    public List<Integer> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Integer> subjects) {
        this.subjects = subjects;
    }

//    public SubjectBean getSubject() {
//        return subject;
//    }
//
//    public void setSubject(SubjectBean subject) {
//        this.subject = subject;
//    }
    public List<Integer> getStudents() {
        return students;
    }

    public void setStudents(List<Integer> students) {
        this.students = students;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getReputation() {
        return reputation;
    }

    public void setReputation(double reputation) {
        this.reputation = reputation;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getNumberVotes() {
        return numberVotes;
    }

    public void setNumberVotes(int numberVotes) {
        this.numberVotes = numberVotes;
    }

    public int getPublishedResources() {
        return publishedResources;
    }

    public void setPublishedResources(int publishedResources) {
        this.publishedResources = publishedResources;
    }

    public int getQuestionReceived() {
        return questionReceived;
    }

    public void setQuestionReceived(int questionReceived) {
        this.questionReceived = questionReceived;
    }

    public int getNumberStudents() {
        return numberStudents;
    }

    public void setNumberStudents(int numberStudents) {
        this.numberStudents = numberStudents;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public List<ActivityVo> getActivityList() {
        return activityList;
    }

    public void setActivityList(List<ActivityVo> activityList) {
        this.activityList = activityList;
    }

    public List<TutorSubjectVo> getTutorSubjectList() {
        return tutorSubjectList;
    }

    public void setTutorSubjectList(List<TutorSubjectVo> tutorSubjectList) {
        this.tutorSubjectList = tutorSubjectList;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
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

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }
    
    private void loadMyTutors() {
        tutors = new ArrayList<TutorSubjectVo>();
        StudentVo student = FacadeFactory.getInstance().getStudentFacade().find(user.getId());
        for (TutorSubjectVo tutor : student.getTutorSubjectList()) {
            tutors.add(FacadeFactory.getInstance().getTutorSubjectFacade().find(tutor.getId()));
        }
    }

    public void preRenderView() {

        if (getId() != null) {
            TutorVo tutor = FacadeFactory.getInstance().getTutorFacade().find(getId());
            
            setUserName(tutor.getUserName());
            setActivityList(tutor.getActivityList());
            setNumberStudents(tutor.getNumberStudents());
            setNumberVotes(tutor.getNumberVotes());
            setPublishedResources(tutor.getPublishedResources());
            setQuestionReceived(tutor.getQuestionReceived());
            setReputation(tutor.getReputation());
            setStudentId(tutor.getStudentId());
            setTutorSubjectList(tutor.getTutorSubjectList());
            setUrlPhoto(tutor.getUrlPhoto());
            
            for(TutorSubjectVo each : tutorSubjectList){
                students.addAll(each.getStudentList());
                subjects.add(each.getSubjectCode());
                
                
            }
            
        }
    }
    
    public String getLevelInformation(){
        
        if (reputation<=1){
            return "Cuero";
        }
        else if(reputation<=2){
            return "Roca";
        }
        else if(reputation<=3){
            return "Cobre";
        }
        else if(reputation<=4){
            return "Hierro";
        }
        else if(reputation<=5){
            return "Plata";
        }
        else if(reputation<=6){
            return "Oro";
        }
        else if(reputation<=7){
            return "Esmeralda";
        }
        else if(reputation<=8){
            return "Diamante";
        }else{
            return "Obsidiana";
        }
        
                
    }
    
}
