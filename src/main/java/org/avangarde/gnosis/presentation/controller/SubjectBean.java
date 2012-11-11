package org.avangarde.gnosis.presentation.controller;

import java.io.Serializable;
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
    String buttonValue;

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

    public void subscribeStudent() {
        if ("Suscribirme a la materia".equals(buttonValue)) {
            if (FacadeFactory.getInstance().getSubjectFacade().subscribeStudent(new Integer(user.getId()), getCode())) {
                addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Te has suscrito a la materia", ""));
            } else {
                addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "No te pudiste suscribir a la materia", ""));
            }
        } else {
            if (FacadeFactory.getInstance().getSubjectFacade().unSubscribeStudent(new Integer(user.getId()), getCode())) {
                addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Has abandonado la materia", ""));

            } else {
                addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "No pudiste abandonar la materia", ""));
            }
        }
    }

    public String changeButtonValue() {
        return buttonValue = FacadeFactory.getInstance().getSubjectFacade().
                isTheStudentSubscribed(new Integer(user.getId()), getCode()) ? 
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
        
        //TODO revisar cuando no se esta suscrito a la materia


        //Logique
        //Si usuario actual aun no es tutor haz:
        //Crear nuevo tutor a partir de la información del usuario

        if (user.isLoggedIn()) {

            StudentFacade studentFacade = FacadeFactory.getInstance().getStudentFacade();

            TutorVo tutorVo = new TutorVo();

            tutorVo.setId(user.getId());
            tutorVo.setStudentId(user.getId());

            if (!studentFacade.isTutor(tutorVo)) {

                becomeTutor(tutorVo);

            }

            //continuando


            SubjectFacade subjectFacade = FacadeFactory.getInstance().getSubjectFacade();
            int subjectCode = code;

            SubjectVo subjectVo = subjectFacade.find(code);

            List<TutorSubjectVo> tutorList = subjectVo.getTutorSubjectList();

            TutorSubjectVo tutorSubjectVo = new TutorSubjectVo();

            tutorSubjectVo.setSubjectCode(subjectCode);
            tutorSubjectVo.setReputation(0);
            tutorSubjectVo.setTutorId(tutorVo.getId());

            TutorSubjectFacade tutorSubjectFacade = FacadeFactory.getInstance().getTutorSubjectFacade();
            
            tutorSubjectFacade.create(tutorSubjectVo);

//            tutorList.add(tutorSubjectVo);
//            subjectVo.setTutorSubjectList(tutorList);
//            boolean flag = subjectFacade.update(subjectVo);

            //Bean de tutor

        }


        //Luego haz:
        //Obtener lista de tutores de la materia
        //agrega al tutor

        //clausura de cosas
        return "success";

    }

    public void becomeTutor(TutorVo tutorVo) {
        TutorFacade tutorFacade = FacadeFactory.getInstance().getTutorFacade();

        //retorna un tutor vacio, que se debe crear antes de continuar
        //rellenar
        tutorVo.setStudentId(tutorVo.getId());

        //defecto
        int def = 0;
        tutorVo.setNumberStudents(def);
        tutorVo.setNumberVotes(def);
        tutorVo.setPublishedResources(def);
        tutorVo.setQuestionReceived(def);
        tutorVo.setReputation(def);

        tutorVo.setUserName(user.getUserName());


        tutorFacade.create(tutorVo);

    }
}
