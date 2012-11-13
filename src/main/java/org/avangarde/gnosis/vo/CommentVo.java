package org.avangarde.gnosis.vo;

import java.util.List;

/**
 *
 * @author Alexander
 */
public class CommentVo implements IValueObject{

    private int id;
    private String content;
    private String date;
    private int liked;
    private int disliked;
    private int tutorSubjectId;
    private int topicId;
    private int activityId;
    private int publicationId;
    private int studentId;
    private String studentName;
    private String studentUrlPhoto;
    private List<LikeDislikeVo> likeDislikeList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getLiked() {
        return liked;
    }

    public void setLiked(int liked) {
        this.liked = liked;
    }

    public int getDisliked() {
        return disliked;
    }

    public void setDisliked(int disliked) {
        this.disliked = disliked;
    }

    public int getTutorSubjectId() {
        return tutorSubjectId;
    }

    public void setTutorSubjectId(int tutorSubjectId) {
        this.tutorSubjectId = tutorSubjectId;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public int getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(int publicationId) {
        this.publicationId = publicationId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public List<LikeDislikeVo> getLikeDislikeList() {
        return likeDislikeList;
    }

    public void setLikeDislikeList(List<LikeDislikeVo> likeDislikeList) {
        this.likeDislikeList = likeDislikeList;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentUrlPhoto() {
        return studentUrlPhoto;
    }

    public void setStudentUrlPhoto(String studentUrlPhoto) {
        this.studentUrlPhoto = studentUrlPhoto;
    }
       
}
