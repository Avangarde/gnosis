package org.avangarde.gnosis.presentation.controller;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.avangarde.gnosis.businesslogic.facade.FacadeFactory;
import org.avangarde.gnosis.businesslogic.facade.StudentFacade;
import org.avangarde.gnosis.businesslogic.facade.SubjectFacade;
import org.avangarde.gnosis.businesslogic.facade.TutorFacade;
import org.avangarde.gnosis.vo.ActivityVo;
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

    public String becomeTutor() {
        //TODO revisar cuando no se esta suscrito a la materia
        SubjectVo subjectVo = new SubjectVo();
        SubjectFacade subjectFacade = FacadeFactory.getInstance().getSubjectFacade();

        //Logique
        //Si usuario actual aun no es tutor haz:
        //Crear nuevo tutor a partir de la información del usuario

        if (user.isLoggedIn()) {
            StudentFacade studentFacade = FacadeFactory.getInstance().getStudentFacade();
            TutorFacade tutorFacade = FacadeFactory.getInstance().getTutorFacade();

            TutorVo tutorVo = new TutorVo();

            tutorVo.setId(user.getId());

            if (!studentFacade.isTutor(tutorVo)) {
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

                tutorFacade.create(tutorVo);
            }

            //continuando
            int subjectCode = code;

            subjectVo = subjectFacade.find(code);

            List<TutorSubjectVo> tutorList = subjectVo.getTutorSubjectList();

            TutorSubjectVo tutorSubjectVo = new TutorSubjectVo();
            tutorSubjectVo.setId(tutorVo.getId());
            tutorSubjectVo.setSubjectCode(subjectCode);
            tutorSubjectVo.setReputation(0);


            tutorList.add(tutorSubjectVo);
            subjectVo.setTutorSubjectList(tutorList);
            subjectFacade.update(subjectVo);

            //Bean de tutor

        }


        //Luego haz:
        //Obtener lista de tutores de la materia
        //agrega al tutor

        //clausura de cosas
        return "¡Felicidades! Eres el nuevo tutor de " + name;

    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }
}
