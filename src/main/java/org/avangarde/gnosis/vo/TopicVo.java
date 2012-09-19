package org.avangarde.gnosis.vo;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Zergio
 */
public class TopicVo {
    
    private int id;
    private Date dateStarted;
    private String title;
    private int studentId;
    private Integer subjectCode;
    private List<CommentVo> commentList;
    private List<ActivityVo> activityList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateStarted() {
        return dateStarted;
    }

    public void setDateStarted(Date dateStarted) {
        this.dateStarted = dateStarted;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public Integer getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(Integer subjectCode) {
        this.subjectCode = subjectCode;
    }

    public List<CommentVo> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<CommentVo> commentList) {
        this.commentList = commentList;
    }

    public List<ActivityVo> getActivityList() {
        return activityList;
    }

    public void setActivityList(List<ActivityVo> activityList) {
        this.activityList = activityList;
    }
    
    
}
