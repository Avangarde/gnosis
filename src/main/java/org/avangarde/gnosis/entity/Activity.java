package org.avangarde.gnosis.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Size;
import org.avangarde.gnosis.vo.ActivityVo;
import org.avangarde.gnosis.vo.CommentVo;

/**
 *
 * @author Alexander
 */
@Entity
@Table(name = "activity")
@NamedQueries({
    @NamedQuery(name = "Activity.findAll", query = "SELECT a FROM Activity a"),})

public class Activity implements Serializable, IEntity<ActivityVo> {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "type")
    private String type;
    @Size(max = 45)
    @Column(name = "details")
    private String details;
    @Column(name = "dateActivity")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateActivity;
    @Column(name = "url")
    private String url;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activity")
    private List<Comment> commentList;
    @ManyToOne
    @JoinColumn(name = "idTopic")
    private Topic topic;
    @ManyToOne
    @JoinColumn(name = "Subject_code")
    private Subject subject;
    @ManyToOne
    @JoinColumn(name = "tutorId")
    private Tutor tutor;
    @ManyToOne
    @JoinColumn(name = "studentId")
    private Student student;
    @ManyToOne
    @JoinColumn(name = "idPublication")
    private Publication publication;

    public Activity() {
    }

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

    public void setDateActivity(Date date) {
        this.dateActivity = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
    
    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    @Override
    public ActivityVo toVo() {
        ActivityVo vo = new ActivityVo();
        SimpleDateFormat format = new SimpleDateFormat ("dd/MM/yyyy - HH:mm:ss");
        vo.setDateActivity(format.format(getDateActivity()));
        vo.setDetails(getDetails());
        vo.setId(getId());
        vo.setStudentId(getStudent().getId());
        vo.setStudentUsername(getStudent().getUserName());
        vo.setStudentUrlPhoto(getStudent().getUrlPhoto());
        vo.setSubjectCode(getSubject().getCode());
        vo.setSubjectName(getSubject().getName());
        if (getTopic() != null) {
            vo.setTopicId(getTopic().getId());
            vo.setTopicTitle(getTopic().getTitle());
        }
        if (getTutor() != null) {
            vo.setTutorId(getTutor().getId());
        }
        vo.setType(getType());
        vo.setUrl(getUrl());
        if (getPublication() != null) {
            vo.setPublicationId(getPublication().getId());
            vo.setPublicationTitle(getPublication().getTitle());
        }
        List<CommentVo> listVo = new ArrayList<CommentVo>();
        for (Comment entity : getCommentList()){
            listVo.add(entity.toVo());
        }
        vo.setCommentList(listVo);
        return vo;
    }
}
