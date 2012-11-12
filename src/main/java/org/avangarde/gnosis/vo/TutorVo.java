package org.avangarde.gnosis.vo;

import java.util.List;

/**
 *
 * @author Zergio
 */
public class TutorVo implements IValueObject {

    private int id;
    private double reputation;
    private String userName;
    private int numberVotes;
    private int publishedResources;
    private int questionReceived;
    private int numberStudents;
    private int studentId;
    private List<ActivityVo> activityList;
    private List<TutorSubjectVo> tutorSubjectList;
    private String urlPhoto;

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
    
}
