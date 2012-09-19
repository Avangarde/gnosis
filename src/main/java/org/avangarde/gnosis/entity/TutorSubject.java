package org.avangarde.gnosis.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.avangarde.gnosis.vo.TutorSubjectVo;

/**
 *
 * @author Alexander
 */
@Entity
@Table(name = "tutor_subject")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TutorSubject.findAll", query = "SELECT t FROM TutorSubject t"),
    @NamedQuery(name = "TutorSubject.findByReputation", query = "SELECT t FROM TutorSubject t WHERE t.reputation = :reputation")})

public class TutorSubject implements Serializable, IEntity<TutorSubjectVo> {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "reputation")
    private double reputation;
    @ManyToMany(mappedBy = "tutorSubjectList")
    private List<Student> studentList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tutorSubject")
    private List<Comment> commentList;
    @JoinColumn(name = "Subject_code", referencedColumnName = "code", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Subject subject;
    @JoinColumn(name = "Tutor_tutorId", referencedColumnName = "tutorId", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Tutor tutor;

    public TutorSubject() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getReputation() {
        return reputation;
    }

    public void setReputation(double reputation) {
        this.reputation = reputation;
    }

    @XmlTransient
    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    @XmlTransient
    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
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

    @Override
    public TutorSubjectVo toVo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

   

}
