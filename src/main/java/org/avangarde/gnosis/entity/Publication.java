package org.avangarde.gnosis.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.avangarde.gnosis.vo.CommentVo;
import org.avangarde.gnosis.vo.PublicationVo;

/**
 *
 * @author Alexander
 */
@Entity
@Table(name = "publication")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Publication.findAll", query = "SELECT p FROM Publication p"),
    @NamedQuery(name = "Publication.findByTitle", query = "SELECT p FROM Publication p WHERE p.title = :title"),
    @NamedQuery(name = "Publication.findByTopic", query = "SELECT p FROM Publication p WHERE p.topic = :topic"),
    @NamedQuery(name = "Publication.findByType", query = "SELECT p FROM Publication p WHERE p.type = :type"),
    @NamedQuery(name = "Publication.findByRating", query = "SELECT p FROM Publication p WHERE p.rating = :rating"),
    @NamedQuery(name = "Publication.findByUrl", query = "SELECT p FROM Publication p WHERE p.url = :url"),
    @NamedQuery(name = "Publication.findByDate", query = "SELECT p FROM Publication p WHERE p.date = :date")})

public class Publication implements Serializable, IEntity<PublicationVo> {
    
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "topic")
    private String topic;
    @Column(name = "type")
    private String type;
    @Column(name = "rating")
    private Double rating;
    @Column(name = "url")
    private String url;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "publication")
    private List<Comment> commentList;
    @JoinColumn(name = "Student_studentId", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Student student;
    @JoinColumn(name = "Subject_code", referencedColumnName = "code", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Subject subject;

    public Publication() {
    }

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

    @XmlTransient
    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
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

        
    @Override
    public PublicationVo toVo() {
        PublicationVo vo = new PublicationVo();
        List<CommentVo> listVo = new ArrayList<CommentVo>();
        for(Comment entity : getCommentList()){
            listVo.add(entity.toVo());
        }
        vo.setCommentList(listVo);
        vo.setDate(getDate());
        vo.setId(getId());
        vo.setRating(getRating());
        vo.setStudentId(getStudent().getId());
        vo.setSubjectCode(getSubject().getCode());
        vo.setTitle(getTitle());
        vo.setTopic(getTopic());
        vo.setType(getType());
        vo.setUrl(getUrl());
        return vo;
        
    }
}
