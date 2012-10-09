package org.avangarde.gnosis.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.avangarde.gnosis.vo.CommentVo;
import org.avangarde.gnosis.vo.LikeDislikeVo;

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
//    @JoinColumns({
//        @JoinColumn(name = "Tutor_Subject_TutorId", referencedColumnName = "TutorId", insertable = false, updatable = false),
//        @JoinColumn(name = "Tutor_Subject_SubjectCode", referencedColumnName = "SubjectCode", insertable = false, updatable = false)})
//    @ManyToOne(optional = false)
//    private TutorSubject tutorSubject;
    @JoinColumn(name = "Topic_idTopic", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Topic topic;
    @JoinColumn(name = "Activity_idActivity", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Activity activity;
    @JoinColumn(name = "Publication_idPublication", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Publication publication;
    @JoinColumn(name = "Student_studentId", referencedColumnName = "id", insertable = false, updatable = false)
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

//    public TutorSubject getTutorSubject() {
//        return tutorSubject;
//    }
//
//    public void setTutorSubject(TutorSubject tutorSubject) {
//        this.tutorSubject = tutorSubject;
//    }

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
        CommentVo vo =new CommentVo();
        vo.setActivityId(getActivity().getId());
        vo.setContent(getContent());
        vo.setDate(getDate());
        vo.setDisliked(getDislike());
        vo.setId(getId());
        List<LikeDislikeVo> listVo =new ArrayList<LikeDislikeVo>();
        for(LikeDislike entity:getLikeDislikeList()){
            listVo.add(entity.toVo());
        }
        vo.setLikeDislikeList(listVo);
        vo.setLiked(getLike());
        vo.setPublicationId(getPublication().getId());
        vo.setStudentId(getStudent().getId());
        vo.setTopicId(getTopic().getId());
//        vo.setTutorSubjectId(getTutorSubject().getId());
        return vo;
    }
   
}
