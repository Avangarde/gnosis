package org.avangarde.gnosis.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.avangarde.gnosis.vo.CommentVo;
import org.avangarde.gnosis.vo.StudentVo;
import org.avangarde.gnosis.vo.TutorSubjectVo;

/**
 *
 * @author Alexander
 */
@Entity
@Table(name = "tutor_subject")
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
    @ManyToOne
    @JoinColumn(name = "SubjectCode")
    private Subject subject;
    @ManyToOne
    @JoinColumn(name = "TutorId")
    private Tutor tutor;
    @Column(name = "number_votes")
    private int numberVotes;
    @Column(name = "published_resources")
    private int publishedResources;
    @Column(name = "question_received")
    private int questionReceived;
    @Column(name = "number_students")
    private int numberStudents;
    

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

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

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

    public int getNumberVotes() {
        return numberVotes;
    }

    public void setNumberVotes(int numberVotes) {
        this.numberVotes = numberVotes;
    }

    public int getPublishedResources() {
        return publishedResources;
    }

    public void setPublishedResources(int publishedResources) {
        this.publishedResources = publishedResources;
    }

    public int getQuestionReceived() {
        return questionReceived;
    }

    public void setQuestionReceived(int questionReceived) {
        this.questionReceived = questionReceived;
    }

    public int getNumberStudents() {
        return numberStudents;
    }

    public void setNumberStudents(int numberStudents) {
        this.numberStudents = numberStudents;
    }
    
        
    @Override
    public TutorSubjectVo toVo() {
        TutorSubjectVo vo = new TutorSubjectVo();
        List<CommentVo> listVo = new ArrayList<CommentVo>();
        for(Comment entity : getCommentList()){
            listVo.add(entity.toVo());
        }
        vo.setCommentList(listVo);
        vo.setId(getId());
        vo.setReputation(getReputation());
        List<StudentVo> listStudentVo = new ArrayList<StudentVo>();
        for(Student entity : getStudentList()){
            listStudentVo.add(entity.toVo());
        }
        vo.setStudentList(listStudentVo);
        vo.setTutorId(getTutor().getId());
        vo.setNumberStudents(getNumberStudents());
        vo.setNumberVotes(getNumberVotes());
        vo.setPublishedResources(getPublishedResources());
        vo.setQuestionReceived(getQuestionReceived());
        return vo;
    }
}
