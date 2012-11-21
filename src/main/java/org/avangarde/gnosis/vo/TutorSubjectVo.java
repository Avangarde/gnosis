package org.avangarde.gnosis.vo;

import java.util.List;

/**
 *
 * @author Zergio
 */
public class TutorSubjectVo implements IValueObject {

    private int id;
    private Double reputation;
    private List<Integer> studentList;
    private List<CommentVo> commentList;
    private List<RatingVo> ratingList;
    private Integer subjectCode;
    private int tutorId;
    private int numberVotes;
    private int publishedResources;
    private int questionReceived;
    private int numberStudents;
    private String userName;
    private String urlPhoto;

    public List<RatingVo> getRatingList() {
        return ratingList;
    }

    public void setRatingList(List<RatingVo> ratingList) {
        this.ratingList = ratingList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getReputation() {
        return reputation;
    }

    public void setReputation(Double reputation) {
        this.reputation = reputation;
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

    public int getTutorId() {
        return tutorId;
    }

    public void setTutorId(int tutorId) {
        this.tutorId = tutorId;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }
}
