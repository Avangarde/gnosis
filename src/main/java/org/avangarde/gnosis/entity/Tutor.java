package org.avangarde.gnosis.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import org.avangarde.gnosis.vo.ActivityVo;
import org.avangarde.gnosis.vo.TutorSubjectVo;
import org.avangarde.gnosis.vo.TutorVo;

/**
 *
 * @author Alexander
 */
@Entity
@Table(name = "tutor")
@NamedQueries({
    @NamedQuery(name = "Tutor.findAll", query = "SELECT t FROM Tutor t"),
    @NamedQuery(name = "Tutor.findByReputation", query = "SELECT t FROM Tutor t WHERE t.reputation = :reputation"),
    @NamedQuery(name = "Tutor.findByUserName", query = "SELECT t FROM Tutor t WHERE t.userName = :userName"),
    @NamedQuery(name = "Tutor.findByNumberVotes", query = "SELECT t FROM Tutor t WHERE t.numberVotes = :numberVotes"),
    @NamedQuery(name = "Tutor.findByPublishedResources", query = "SELECT t FROM Tutor t WHERE t.publishedResources = :publishedResources"),
    @NamedQuery(name = "Tutor.findByQuestionReceived", query = "SELECT t FROM Tutor t WHERE t.questionReceived = :questionReceived"),
    @NamedQuery(name = "Tutor.findByNumberStudents", query = "SELECT t FROM Tutor t WHERE t.numberStudents = :numberStudents")})

public class Tutor implements Serializable, IEntity<TutorVo> {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "reputation")
    private double reputation;
    @Column(name = "userName")
    private String userName;
    @Column(name = "number_votes")
    private int numberVotes;
    @Column(name = "published_resources")
    private int publishedResources;
    @Column(name = "question_received")
    private int questionReceived;
    @Column(name = "number_students")
    private int numberStudents;
    @ManyToOne
    @JoinColumn(name = "studentId")
    private Student student;
    @OneToMany(mappedBy = "tutor")
    private List<Activity> activityList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tutor")
    private List<TutorSubject> tutorSubjectList;
    @Column(name = "url_Photo")
    private String urlPhoto;

    public Tutor() {
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Activity> getActivityList() {
        return activityList;
    }

    public void setActivityList(List<Activity> activityList) {
        this.activityList = activityList;
    }

    public List<TutorSubject> getTutorSubjectList() {
        return tutorSubjectList;
    }

    public void setTutorSubjectList(List<TutorSubject> tutorSubjectList) {
        this.tutorSubjectList = tutorSubjectList;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }
    
    

    @Override
    public TutorVo toVo() {
        TutorVo vo = new TutorVo();
        List<ActivityVo> listActivityVo = new ArrayList<ActivityVo>();
        for(Activity entity : getActivityList()){
            listActivityVo.add(entity.toVo());
        }
        vo.setActivityList(listActivityVo);
        vo.setId(getId());
        vo.setNumberStudents(getNumberStudents());
        vo.setNumberVotes(getNumberVotes());
        vo.setPublishedResources(getPublishedResources());
        vo.setQuestionReceived(getQuestionReceived());
        vo.setReputation(getReputation());
        vo.setStudentId(getStudent().getId());
        List<TutorSubjectVo> listTutorSubjectVo = new ArrayList<TutorSubjectVo>();
        for(TutorSubject entity : getTutorSubjectList()){
            listTutorSubjectVo.add(entity.toVo());
        }
        vo.setTutorSubjectList(listTutorSubjectVo);
        vo.setUserName(getUserName());
        vo.setUrlPhoto(getUrlPhoto());
        return vo;
    }
}
