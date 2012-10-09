package org.avangarde.gnosis.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import org.avangarde.gnosis.vo.LikeDislikeVo;

/**
 *
 * @author Alexander
 */
@Entity
@Table(name = "like_dislike")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LikeDislike.findAll", query = "SELECT l FROM LikeDislike l"),
    @NamedQuery(name = "LikeDislike.findByLike", query = "SELECT l FROM LikeDislike l WHERE l.liked = :like"),
    @NamedQuery(name = "LikeDislike.findByDislike", query = "SELECT l FROM LikeDislike l WHERE l.disliked = :dislike")})
public class LikeDislike implements Serializable, IEntity<LikeDislikeVo> {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "liked")
    private boolean liked;
    @Column(name = "disliked")
    private boolean disliked;
    @JoinColumn(name = "Student_studentId", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Student student;
    @JoinColumn(name = "Comment_idComment", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Comment comment;

    public LikeDislike() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getLike() {
        return liked;
    }

    public void setLike(Boolean like) {
        this.liked = like;
    }

    public Boolean getDislike() {
        return disliked;
    }

    public void setDislike(Boolean dislike) {
        this.disliked = dislike;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    @Override
    public LikeDislikeVo toVo() {

        LikeDislikeVo vo = new LikeDislikeVo();
        vo.setCommentId(getComment().getId());
        vo.setDisliked(getDislike());
        vo.setId(getId());
        vo.setLiked(getLike());
        vo.setStudentId(getStudent().getId());
        
        return vo;
    }
}
