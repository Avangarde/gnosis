package org.avangarde.gnosis.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import org.avangarde.gnosis.vo.ActivityVo;
import org.avangarde.gnosis.vo.CommentVo;
import org.avangarde.gnosis.vo.TopicVo;

/**
 *
 * @author Alexander
 */
@Entity
@Table(name = "topic")
@NamedQueries({
    @NamedQuery(name = "Topic.findAll", query = "SELECT t FROM Topic t"),
    @NamedQuery(name = "Topic.findByDateStarted", query = "SELECT t FROM Topic t WHERE t.dateStarted = :dateStarted"),
    @NamedQuery(name = "Topic.findByTitle", query = "SELECT t FROM Topic t WHERE t.title = :title"),})
public class Topic implements Serializable, IEntity<TopicVo> {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "dateStarted")
    @Temporal(TemporalType.DATE)
    private Date dateStarted;
    @Column(name = "title")
    private String title;
    @ManyToOne
    @JoinColumn(name = "studentId")
    private Student student;
    @ManyToOne
    @JoinColumn(name = "Subject_code")
    private Subject subject;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "topic")
    private List<Comment> commentList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "topic")
    private List<Activity> activityList;

    public Topic() {
    }

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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public List<Activity> getActivityList() {
        return activityList;
    }

    public void setActivityList(List<Activity> activityList) {
        this.activityList = activityList;
    }

    @Override
    public TopicVo toVo() {
        TopicVo vo = new TopicVo();
        vo.setDateStarted(getDateStarted());
        vo.setId(getId());
        vo.setStudentId(getStudent().getId());
        vo.setSubjectCode(getSubject().getCode());
        vo.setTitle(getTitle());
        List<CommentVo> listCommentVo = new ArrayList<CommentVo>();
        List<ActivityVo> listActivityVo = new ArrayList<ActivityVo>();
        for (Comment entity : getCommentList()) {
            listCommentVo.add(entity.toVo());
        }
        for (Activity entity : getActivityList()) {
            listActivityVo.add(entity.toVo());
        }
        vo.setCommentList(listCommentVo);
        vo.setActivityList(listActivityVo);
        return vo;
    }
}
