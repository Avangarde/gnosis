/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avangarde.gnosis.presentation.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.avangarde.gnosis.vo.ActivityVo;
import org.avangarde.gnosis.vo.StudentVo;
import org.avangarde.gnosis.vo.TutorSubjectVo;

/**
 *
 * @author zergio
 */
@ManagedBean
@SessionScoped
public class TutorBean implements Serializable {

    private int id;
    private double reputation;
    private String userName;
    private int numberVotes;
    private int publishedResources;
    private int questionReceived;
    private int numberStudents;
    private int studentId;
    private List<StudentVo> students = new ArrayList<StudentVo>();

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
    private List<ActivityVo> activityList;
    private List<TutorSubjectVo> tutorSubjectList;
    private String urlPhoto;
}
