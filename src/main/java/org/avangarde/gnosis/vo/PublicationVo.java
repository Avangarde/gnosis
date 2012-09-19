package org.avangarde.gnosis.vo;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Zergio
 */
public class PublicationVo implements IValueObject{
   
    private int id;
    private String title;
    private String topic;
    private String type;
    private Double rating;
    private String url;
    private Date date;
    private List<CommentVo> commentList;
    private int studentId;
    private Integer SubjectCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<CommentVo> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<CommentVo> commentList) {
        this.commentList = commentList;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public Integer getSubjectCode() {
        return SubjectCode;
    }

    public void setSubjectCode(Integer SubjectCode) {
        this.SubjectCode = SubjectCode;
    }
    
    
}
