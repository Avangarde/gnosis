package org.avangarde.gnosis.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import org.avangarde.gnosis.vo.*;

/**
 *
 * @author Alexander
 */
@Entity
@Table(name = "student")
@NamedQueries({
    @NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s"),
    @NamedQuery(name = "Student.findByUserName", query = "SELECT s FROM Student s WHERE s.userName = :userName"),
    @NamedQuery(name = "Student.findByEmail", query = "SELECT s FROM Student s WHERE s.email = :email"),})
public class Student implements Serializable, IEntity<StudentVo> {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "userName")
    private String userName;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "url_Photo")
    private String urlPhoto;
    @JoinTable(name = "student_studygroup", joinColumns = {
        @JoinColumn(name = "Student_studentId", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "StudyGroup_idStudyGroup", referencedColumnName = "id")})
    @ManyToMany
    private List<Studygroup> studygroupList;
    @ManyToMany
    @JoinTable(name = "student_has_tutor_subject", joinColumns = {
        @JoinColumn(name = "Student_studentId", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "TutorSubject_TutorId", referencedColumnName = "TutorId"),
        @JoinColumn(name = "TutorSubject_SubjectCode", referencedColumnName = "SubjectCode")})
    private List<TutorSubject> tutorSubjectList;
    @JoinTable(name = "student_subject", joinColumns = {
        @JoinColumn(name = "Student_studentId", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "Subject_code", referencedColumnName = "code")})
    @ManyToMany
    private List<Subject> subjectList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    private List<Topic> topicList;
    @ManyToOne
    @JoinColumn(name = "Program_code")
    private Program program;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    private List<Event> eventList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    private List<Tutor> tutorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    private List<LikeDislike> likeDislikeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    private List<Comment> commentList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    private List<Activity> activityList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    private List<Publication> publicationList;

    public Student() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public List<Studygroup> getStudygroupList() {
        return studygroupList;
    }

    public void setStudygroupList(List<Studygroup> studygroupList) {
        this.studygroupList = studygroupList;
    }

    public List<TutorSubject> getTutorSubjectList() {
        return tutorSubjectList;
    }

    public void setTutorSubjectList(List<TutorSubject> tutorSubjectList) {
        this.tutorSubjectList = tutorSubjectList;
    }
    public List<Subject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<Subject> subjectList) {
        this.subjectList = subjectList;
    }

    public List<Topic> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<Topic> topicList) {
        this.topicList = topicList;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }

    public List<Tutor> getTutorList() {
        return tutorList;
    }

    public void setTutorList(List<Tutor> tutorList) {
        this.tutorList = tutorList;
    }

    public List<LikeDislike> getLikeDislikeList() {
        return likeDislikeList;
    }

    public void setLikeDislikeList(List<LikeDislike> likeDislikeList) {
        this.likeDislikeList = likeDislikeList;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public List<Activity> getActivityList() {
        return activityList;
    }

    public void setActivityList(List<Activity> activityList) {
        this.activityList = activityList;
    }

    public List<Publication> getPublicationList() {
        return publicationList;
    }

    public void setPublicationList(List<Publication> publicationList) {
        this.publicationList = publicationList;
    }

    @Override
    public StudentVo toVo() {
        StudentVo vo = new StudentVo();

        //atributos
        vo.setEmail(getEmail());
        vo.setFirstName(getFirstName());
        vo.setLastName(getLastName());
        vo.setId(getId());
        vo.setPassword(getPassword());
        if (getProgram() != null) {
            vo.setProgramId(getProgram().getCode());
        }
        vo.setUrlPhoto(getUrlPhoto());
        vo.setUserName(getUserName());

        //Listas

        ArrayList<StudygroupVo> listStudygroupVo = new ArrayList<StudygroupVo>();
        for (Studygroup entity : getStudygroupList()) {
            listStudygroupVo.add(entity.toVo());
        }



        ArrayList<TutorSubjectVo> listTutorSubjectVo = new ArrayList<TutorSubjectVo>();
        for (TutorSubject entity : getTutorSubjectList()) {
            listTutorSubjectVo.add(entity.toVo());
        }

//        ArrayList<SubjectVo> listSubjectVo = new ArrayList<SubjectVo>();
//        for (Subject entity : getSubjectList()) {
//            listSubjectVo.add(entity.toVo());
//        }

        ArrayList<TopicVo> listTopicVo = new ArrayList<TopicVo>();
        for (Topic entity : getTopicList()) {
            listTopicVo.add(entity.toVo());
        }

        ArrayList<EventVo> listEventVo = new ArrayList<EventVo>();
        for (Event entity : getEventList()) {
            listEventVo.add(entity.toVo());
        }

        ArrayList<TutorVo> listTutorVo = new ArrayList<TutorVo>();
        for (Tutor entity : getTutorList()) {
            listTutorVo.add(entity.toVo());
        }

        ArrayList<LikeDislikeVo> listLikeDislikeVo = new ArrayList<LikeDislikeVo>();
        for (LikeDislike entity : getLikeDislikeList()) {
            listLikeDislikeVo.add(entity.toVo());
        }

        ArrayList<CommentVo> listCommentVo = new ArrayList<CommentVo>();
        for (Comment entity : getCommentList()) {
            listCommentVo.add(entity.toVo());
        }

        ArrayList<ActivityVo> listActivityVo = new ArrayList<ActivityVo>();
        for (Activity entity : getActivityList()) {
            listActivityVo.add(entity.toVo());
        }

        ArrayList<PublicationVo> listPublicationVo = new ArrayList<PublicationVo>();
        for (Publication entity : getPublicationList()) {
            listPublicationVo.add(entity.toVo());
        }

        vo.setStudygroupList(listStudygroupVo);
        vo.setTutorSubjectList(listTutorSubjectVo);
//        vo.setSubjectList(listSubjectVo);
        vo.setTopicList(listTopicVo);
        vo.setEventList(listEventVo);
        vo.setTutorList(listTutorVo);
        vo.setLikeDislikeList(listLikeDislikeVo);
        vo.setCommentList(listCommentVo);
        vo.setActivityList(listActivityVo);
        vo.setPublicationList(listPublicationVo);

        return vo;

    }
}
