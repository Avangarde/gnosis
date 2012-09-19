package org.avangarde.gnosis.vo;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Alexander
 */
public class ActivityVo {
    
    private int id;
    private String type;
    private String details;
    private Date dateActivity;
    private String url;
    private List<CommentVo> commentList;
    private int topicId;
    private Integer subjectCode;
    private int tutorId;
    private int studentId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getDateActivity() {
        return dateActivity;
    }

    public void setDateActivity(Date dateActivity) {
        this.dateActivity = dateActivity;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<CommentVo> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<CommentVo> commentList) {
        this.commentList = commentList;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
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

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
   
}
