package org.avangarde.gnosis.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

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

public class LikeDislike implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "liked")
    private Boolean liked;
    @Column(name = "disliked")
    private Boolean disliked;
    @JoinColumn(name = "Student_studentId", referencedColumnName = "studentId", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Student student;
    @JoinColumn(name = "Comment_idComment", referencedColumnName = "idComment", insertable = false, updatable = false)
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
 
}
