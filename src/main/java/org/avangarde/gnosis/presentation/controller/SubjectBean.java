package org.avangarde.gnosis.presentation.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.avangarde.gnosis.businesslogic.facade.FacadeFactory;
import org.avangarde.gnosis.businesslogic.facade.StudentFacade;
import org.avangarde.gnosis.businesslogic.facade.SubjectFacade;
import org.avangarde.gnosis.businesslogic.facade.TutorFacade;
import org.avangarde.gnosis.businesslogic.facade.TutorSubjectFacade;
import org.avangarde.gnosis.vo.StudentVo;
import org.avangarde.gnosis.vo.SubjectVo;
import org.avangarde.gnosis.vo.TutorSubjectVo;
import org.avangarde.gnosis.vo.TutorVo;

/**
 *
 * @author Alexander
 */
@ManagedBean
@SessionScoped
public class SubjectBean implements Serializable {

    private Integer code;
    private String name;
    private String description;
    private int numGroups;
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
                isTheStudentSubscribed(new Integer(user.getId()), subject.getCode()) ? 
                "Abandonar" : "Suscribirme a la materia";
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

    public String changeButtonTutorValue() {

        TutorVo tutor = new TutorVo();
        tutor.setUserName(user.getUserName());

        return buttonTutorValue = FacadeFactory.getInstance().getTutorSubjectFacade().
                isTheTutorOnSubject(tutor, getCode())
                ? TUTOR : NOTATUTOR;
    }
    
    public String changeButtonSubscribeValue(int subjectCode) {
        return buttonSubscribeValue = FacadeFactory.getInstance().getSubjectFacade().
                isTheStudentSubscribed(new Integer(user.getId()), subjectCode) ? 
                "Abandonar" : "Suscribirme a la materia";
    }

    public void preRenderView() {

        if (getCode() != null) {
            SubjectVo subject = FacadeFactory.getInstance().getSubjectFacade().find(getCode());
            setName(subject.getName());
            setDescription(subject.getDescription());
            setNumGroups(subject.getNumGroups());
        }
    }

    public String becomeTutorOnSubject() {


        if (NOTATUTOR.equals(buttonTutorValue)) {

            SubjectFacade subjectFacade = FacadeFactory.getInstance().getSubjectFacade();
            if (subjectFacade.isTheStudentSubscribed(user.getId(), code)) {

                StudentFacade studentFacade = FacadeFactory.getInstance().getStudentFacade();

                TutorVo tutorVo = new TutorVo();


                tutorVo.setStudentId(user.getId());
                tutorVo.setUserName(user.getUserName());


                if (!studentFacade.isTutor(tutorVo)) {

                    becomeTutor(tutorVo);
                    
                    
                }

                //continuando
                
                tutorVo = findTutorByUserName(user.getUserName());

                int subjectCode = code;


                TutorSubjectVo tutorSubjectVo = new TutorSubjectVo();

                tutorSubjectVo.setSubjectCode(subjectCode);
                tutorSubjectVo.setReputation(0);

                //Hice un poco de trampa aquí
                tutorSubjectVo.setTutorId(tutorVo.getId());
                tutorSubjectVo.setUrlPhoto(tutorVo.getUrlPhoto());
                tutorSubjectVo.setUserName(tutorVo.getUserName());

                TutorSubjectFacade tutorSubjectFacade = FacadeFactory.getInstance().getTutorSubjectFacade();

                tutorSubjectFacade.create(tutorSubjectVo);

                addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "¡Genial! Ahora eres tutor de la materia", ""));
                return "success";

            } else {
                addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Algo salió mal, Recuerda que debes estar suscrito a la materia para ser su tutor", ""));
                return "failure";
            }



        } else {

            //Navigation case
            return "success";

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
    
    private void loadStudentsBySubject(){
        students = new ArrayList<StudentVo>();
        if (query == null || query.equals("")) {
            SubjectVo subject = FacadeFactory.getInstance().getSubjectFacade().find(code);
            if (subject.getStudentList() != null || subject.getStudentList().isEmpty()) {
                for (StudentVo student : subject.getStudentList()) {
                    students.add(student);
                }
            }
        } else {
            List<StudentVo> searchedStudents = FacadeFactory.getInstance().getStudentFacade().getStudents(query);
            for (StudentVo student : searchedStudents) {
                students.add(student);
            }
        }
    }
}
