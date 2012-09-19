package org.avangarde.gnosis.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.avangarde.gnosis.vo.CommentVo;

/**
 *
 * @author Alexander
 */
@Entity
@Table(name = "comment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comment.findAll", query = "SELECT c FROM Comment c"),})

public class Comment implements Serializable, IEntity<CommentVo> {
    
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "content")
    private String content;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "liked")
    private int liked;
    @Column(name = "disliked")
    private int disliked;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "comment")
    private List<LikeDislike> likeDislikeList;
    @JoinColumns({
        @JoinColumn(name = "Tutor_Subject_Tutor_tutorId", referencedColumnName = "Tutor_tutorId", insertable = false, updatable = false),
        @JoinColumn(name = "Tutor_Subject_Subject_code", referencedColumnName = "Subject_code", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private TutorSubject tutorSubject;
    @JoinColumn(name = "Topic_idTopic", referencedColumnName = "idTopic", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Topic topic;
    @JoinColumn(name = "Activity_idActivity", referencedColumnName = "idActivity", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Activity activity;
    @JoinColumn(name = "Publication_idPublication", referencedColumnName = "idPublication", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Publication publication;
    @JoinColumn(name = "Student_studentId", referencedColumnName = "studentId", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Student student;

    public Comment() {
    }

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getLike() {
        return liked;
    }

    public void setLike(int like) {
        this.liked = like;
    }

    public int getDislike() {
        return disliked;
    }

    public void setDislike(int dislike) {
        this.disliked = dislike;
    }

    @XmlTransient
    public List<LikeDislike> getLikeDislikeList() {
        return likeDislikeList;
    }

    public void setLikeDislikeList(List<LikeDislike> likeDislikeList) {
        this.likeDislikeList = likeDislikeList;
    }

    public TutorSubject getTutorSubject() {
        return tutorSubject;
    }

    public void setTutorSubject(TutorSubject tutorSubject) {
        this.tutorSubject = tutorSubject;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public CommentVo toVo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
   
}
