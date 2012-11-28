package org.avangarde.gnosis.presentation.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.avangarde.gnosis.businesslogic.facade.*;
import org.avangarde.gnosis.vo.*;

/**
 *
 * @author Alexander
 */
@ManagedBean
@ViewScoped
public class SubjectBean implements Serializable {

    private Integer code;
    private String name;
    private String description;
    private String studentSubscribed;
    private int numGroups;
    private String noteBook;
    @ManagedProperty(value = "#{userBean}")
    private UserBean user;
    String buttonSubscribeValue;
    String buttonTutorValue;
    //Constantes de clase
    public String TUTOR = "Ir a mi pagina de tutor";
    public String NOTATUTOR = "Convertirme en tutor";
    public String SUBSCRIBED = "Abandonar";
    public String NOTSUBSCRIBED = "Suscribirme a la materia";
    private List<StudentVo> students = new ArrayList<StudentVo>();
    private String query;
    private List<SubjectVo> subjects = new ArrayList<SubjectVo>();
    private List<ActivityVo> activities = new ArrayList<ActivityVo>();
    private List<EventVo> events = new ArrayList<EventVo>();

    public SubjectBean() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumGroups() {
        return numGroups;
    }

    public void setNumGroups(int numGroups) {
        this.numGroups = numGroups;
    }

    public String getNoteBook() {
        return noteBook;
    }

    public void setNoteBook(String noteBook) {
        this.noteBook = noteBook;
    }

    public void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public List<StudentVo> getStudents() {
        if (students.isEmpty()) {
            loadStudentsBySubject();
        }
        return students;
    }

    public void setStudents(List<StudentVo> students) {
        this.students = students;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public void setSubjects(List<SubjectVo> subjects) {
        this.subjects = subjects;
    }

    public List<SubjectVo> getSubjects() {
        if (subjects.isEmpty()) {
            loadMySubjects();
        }
        return subjects;
    }

    public List<ActivityVo> getActivities() {
        activities = new ArrayList<ActivityVo>();
        List<ActivityVo> vos = FacadeFactory.getInstance().getActivityFacade().getActivitiesBySubject(getCode());
        if (vos != null) {
            activities = vos;
        }
        return activities;
    }

    public List<EventVo> getEvents() {
        events = new ArrayList<EventVo>();
        List<EventVo> vos = FacadeFactory.getInstance().getEventFacade().getEventsFromSubject(getCode());
        if (vos != null) {
            events = vos;
        }
        return events;
    }

    public void setEvents(List<EventVo> events) {
        this.events = events;
    }

    public void setActivities(List<ActivityVo> activities) {
        this.activities = activities;
    }

    public void subscribeStudent() {
        if (NOTSUBSCRIBED.equals(buttonSubscribeValue)) {
            if (FacadeFactory.getInstance().getSubjectFacade().subscribeStudent(new Integer(user.getId()), getCode())) {
                addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Te has suscrito a la materia " + getName(), ""));
            } else {
                addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "No te pudiste suscribir a la materia " + getName(), ""));
            }
        } else {
            if (FacadeFactory.getInstance().getSubjectFacade().unSubscribeStudent(new Integer(user.getId()), getCode())) {
                addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Has abandonado la materia " + getName(), ""));

            } else {
                addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "No pudiste abandonar la materia " + getName(), ""));
            }
        }
    }

    public void subscribeStudent(SubjectVo subject) {
        buttonSubscribeValue = FacadeFactory.getInstance().getSubjectFacade().
                isTheStudentSubscribed(new Integer(user.getId()), subject.getCode())
                ? "Abandonar" : "Suscribirme a la materia";
        if ("Suscribirme a la materia".equals(buttonSubscribeValue)) {
            if (FacadeFactory.getInstance().getSubjectFacade().subscribeStudent(new Integer(user.getId()), subject.getCode())) {
                addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Te has suscrito a la materia " + subject.getName(), ""));
            } else {
                addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "No te pudiste suscribir a la materia " + subject.getName(), ""));
            }
        } else {
            if (FacadeFactory.getInstance().getSubjectFacade().unSubscribeStudent(new Integer(user.getId()), subject.getCode())) {
                addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Has abandonado la materia " + subject.getName(), ""));

            } else {
                addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "No pudiste abandonar la materia " + subject.getName(), ""));
            }
        }
    }

    public String changeButtonSubscribeValue() {
        return buttonSubscribeValue = FacadeFactory.getInstance().getSubjectFacade().
                isTheStudentSubscribed(new Integer(user.getId()), getCode())
                ? SUBSCRIBED : NOTSUBSCRIBED;
    }

    public String getStudentSubscribed() {
        if (FacadeFactory.getInstance().getSubjectFacade().
                isTheStudentSubscribed(new Integer(user.getId()), getCode())) {
            studentSubscribed = "#shareModal";
        } else {
            studentSubscribed = "javascript: void(0)";
            addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Recuerda que no puedes compartir recursos si no estas suscrito a la materia", ""));
        }
        return studentSubscribed;
    }

    public String enablednewTopic() {
        if (FacadeFactory.getInstance().getSubjectFacade().
                isTheStudentSubscribed(new Integer(user.getId()), getCode())) {
            studentSubscribed = "#newTopicModal";
        } else {
            studentSubscribed = "javascript: void(0)";
            addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Recuerda que no puedes crear temas si no estas suscrito a la materia", ""));
        }
        return studentSubscribed;
    }

    public boolean studentUnsuscribed() {
        return FacadeFactory.getInstance().getSubjectFacade().
                isTheStudentSubscribed(new Integer(user.getId()), getCode())
                ? false : true;
    }

    public void setStudentSubscribed(String studentSubscribed) {
        this.studentSubscribed = studentSubscribed;
    }

    public String changeButtonTutorValue() {

        TutorVo tutor = new TutorVo();
        tutor.setUserName(user.getUserName());

        buttonTutorValue = FacadeFactory.getInstance().getTutorSubjectFacade().
                isTheTutorOnSubject(tutor, getCode())
                ? TUTOR : NOTATUTOR;

        if (buttonTutorValue.equals(TUTOR)) {
            setShowTutorButton(false);
        } else {
            setShowTutorButton(true);
        }

        return buttonTutorValue;
    }

    public String changeButtonSubscribeValue(int subjectCode) {
        return buttonSubscribeValue = FacadeFactory.getInstance().getSubjectFacade().
                isTheStudentSubscribed(new Integer(user.getId()), subjectCode)
                ? "Abandonar" : "Suscribirme a la materia";
    }

    public void preRenderView() {

        if (getCode() != null) {
            SubjectVo subject = FacadeFactory.getInstance().getSubjectFacade().find(getCode());
            setName(subject.getName());
            setDescription(subject.getDescription());
            setNumGroups(subject.getNumGroups());
            setNoteBook(subject.getNoteBook());
        }
    }

    public void becomeTutorOnSubject() {


        if (NOTATUTOR.equals(buttonTutorValue)) {

            SubjectFacade subjectFacade = FacadeFactory.getInstance().getSubjectFacade();
            if (subjectFacade.isTheStudentSubscribed(user.getId(), code)) {

                StudentFacade studentFacade = FacadeFactory.getInstance().getStudentFacade();

                TutorVo tutorVo = new TutorVo();


                tutorVo.setStudentId(user.getId());
                tutorVo.setUserName(user.getUserName());
                tutorVo.setNumberStudents(0);


                if (!studentFacade.isTutor(tutorVo)) {

                    becomeTutor(tutorVo);


                }

                //continuando

                tutorVo = findTutorByUserName(user.getUserName());

                int subjectCode = code;


                TutorSubjectVo tutorSubjectVo = new TutorSubjectVo();

                tutorSubjectVo.setSubjectCode(subjectCode);
                tutorSubjectVo.setReputation(0.0);

                //Hice un poco de trampa aquí
                tutorSubjectVo.setTutorId(tutorVo.getId());
                tutorSubjectVo.setUrlPhoto(tutorVo.getUrlPhoto());
                tutorSubjectVo.setUserName(tutorVo.getUserName());

                TutorSubjectFacade tutorSubjectFacade = FacadeFactory.getInstance().getTutorSubjectFacade();

                tutorSubjectFacade.create(tutorSubjectVo);

                addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "¡Genial! Ahora eres tutor de la materia", ""));
            } else {
                addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Algo salió mal, Recuerda que debes estar suscrito a la materia para ser su tutor", ""));
            }
        } else {
            setShowTutorButton(false);
        }
    }

    public void becomeTutor(TutorVo tutorVo) {
        TutorFacade tutorFacade = FacadeFactory.getInstance().getTutorFacade();

        //retorna un tutor vacio, que se debe crear antes de continuar
        //rellenar
        tutorVo.setId(user.getId());
        tutorVo.setStudentId(user.getId());

        //defecto
        int def = 0;
        tutorVo.setNumberStudents(def);
        tutorVo.setNumberVotes(def);
        tutorVo.setPublishedResources(def);
        tutorVo.setQuestionReceived(def);
        tutorVo.setReputation(def);
        tutorVo.setUserName(user.getUserName());
        tutorVo.setUrlPhoto(user.getUrlPhoto());


        tutorFacade.create(tutorVo);

    }

    private TutorVo findTutorByUserName(String userName) {

        TutorFacade tutorFacade = FacadeFactory.getInstance().getTutorFacade();

        TutorVo tutorVo = new TutorVo();
        tutorVo.setUserName(userName);

        return tutorFacade.findByUsername(tutorVo);

    }

    private void loadStudentsBySubject() {
        students = new ArrayList<StudentVo>();
        SubjectVo subject = FacadeFactory.getInstance().getSubjectFacade().find(code);
        if (subject.getStudentList() != null || subject.getStudentList().isEmpty()) {
            for (StudentVo student : subject.getStudentList()) {
                students.add(student);
            }
        }
    }

    private void loadMySubjects() {
        subjects = new ArrayList<SubjectVo>();
        StudentVo student = FacadeFactory.getInstance().getStudentFacade().find(user.getId());
        for (Integer code : student.getSubjectList()) {
            subjects.add(FacadeFactory.getInstance().getSubjectFacade().find(code));
        }
    }

    public void subscribeToTutor(TutorSubjectVo tutor) {
        buttonSubscribeValue = FacadeFactory.getInstance().getSubjectFacade().
                isTheStudentSubscribedToTutor(new Integer(user.getId()), tutor.getUserName(), code)
                ? "Abandonar" : "Suscribirme a este tutor";
        if ("Suscribirme a este tutor".equals(buttonSubscribeValue)) {
            if (FacadeFactory.getInstance().getSubjectFacade().subscribeStudentToTutor(new Integer(user.getId()), tutor.getUserName(), code)) {
                addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Te has suscrito al tutor " + tutor.getUserName(), ""));
            } else {
                addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "No te pudiste suscribir al " + tutor.getUserName(), ""));
            }
        } else {
            if (FacadeFactory.getInstance().getSubjectFacade().unSubscribeStudentToTutor(new Integer(user.getId()), tutor.getUserName(), code)) {
                addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Has abandonado el tutor " + tutor.getUserName(), ""));

            } else {
                addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "No pudiste abandonar el tutor " + tutor.getUserName(), ""));
            }
        }
    }

    public String changeButtonSubscribeToTutorValue(TutorSubjectVo tutor) {
        return buttonSubscribeValue = FacadeFactory.getInstance().getSubjectFacade().
                isTheStudentSubscribedToTutor(new Integer(user.getId()), tutor.getUserName(), code)
                ? "Abandonar" : "Suscribirme a este tutor";
    }
//utilidades de vista
    private boolean ShowTutorButton = true;

    public boolean shouldHideButtonBecomeTutor() {

        if (changeButtonTutorValue().equals(TUTOR)) {
            setShowTutorButton(false);
        } else {
            setShowTutorButton(true);
        }

        return ShowTutorButton;

    }

    public boolean shouldHideNavigationButton() {
        if (shouldHideButtonBecomeTutor()) {
            return false;
        } else {
            return true;
        }
    }

    public void setShowTutorButton(boolean newValue) {
        this.ShowTutorButton = newValue;
    }

    public void saveNoteBook() {
        SubjectVo subject = FacadeFactory.getInstance().getSubjectFacade().find(getCode());
        subject.setNoteBook(getNoteBook());
        FacadeFactory.getInstance().getSubjectFacade().update(subject);
    }
}
