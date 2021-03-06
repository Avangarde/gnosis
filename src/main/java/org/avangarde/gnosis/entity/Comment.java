package org.avangarde.gnosis.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import org.avangarde.gnosis.vo.CommentVo;
import org.avangarde.gnosis.vo.LikeDislikeVo;

/**
 *
 * @author Alexander
 */
@Entity
@Table(name = "comment")
@NamedQueries({
    @NamedQuery(name = "Comment.findAll", query = "SELECT c FROM Comment c"),})
public class Comment implements Serializable, IEntity<CommentVo> {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "content", length = 1000)
    private String content;
    @Column(name = "dateComment")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Column(name = "liked")
    private int liked;
    @Column(name = "disliked")
    private int disliked;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "comment")
    private List<LikeDislike> likeDislikeList;
    @ManyToOne
    @JoinColumn(name = "idTutorSubject")
    private TutorSubject tutorSubject;
    @ManyToOne
    @JoinColumn(name = "idTopic")
    private Topic topic;
    @ManyToOne
    @JoinColumn(name = "idActivity")
    private Activity activity;
    @ManyToOne
    @JoinColumn(name = "idPublication")
    private Publication publication;
    @ManyToOne
    @JoinColumn(name = "studentId")
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
        CommentVo vo = new CommentVo();
        if (getActivity() != null) {
            vo.setActivityId(getActivity().getId());
        }
        vo.setContent(getContent());
        
        SimpleDateFormat format = new SimpleDateFormat ("dd/MM/yyyy - HH:mm:ss");
        vo.setDate(format.format(getDate()));
        vo.setDisliked(getDislike());
        vo.setId(getId());
        List<LikeDislikeVo> listVo = new ArrayList<LikeDislikeVo>();
        for (LikeDislike entity : getLikeDislikeList()) {
            listVo.add(entity.toVo());
        }
        vo.setLikeDislikeList(listVo);
        vo.setLiked(getLike());
        if (getPublication() != null) {
            vo.setPublicationId(getPublication().getId());
        }
        if (getStudent() != null) {
            vo.setStudentId(getStudent().getId());
            vo.setStudentName(getStudent().getUserName());
            vo.setStudentUrlPhoto(getStudent().getUrlPhoto());
        }
        if (getTopic() != null) {
            vo.setTopicId(getTopic().getId());
        }
        if (getTutorSubject() != null) {
            vo.setTutorSubjectId(getTutorSubject().getId());
        }
        return vo;
    }
}
