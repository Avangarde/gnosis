/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avangarde.gnosis.businesslogic.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import org.avangarde.gnosis.dao.DAOFactory;
import org.avangarde.gnosis.entity.*;
import org.avangarde.gnosis.vo.ActivityVo;

/**
 *
 * @author Familia Martinez
 */
public class ActivityService implements IService<ActivityVo> {

    private static ActivityService instance;

    private ActivityService() {
    }

    public static synchronized ActivityService getInstance() {
        if (instance == null) {
            instance = new ActivityService();
        }
        return instance;
    }

    @Override
    public void create(ActivityVo vo, EntityManager em) {
        try {
            Activity entity = new Activity();
            SimpleDateFormat format = new SimpleDateFormat ("dd/MM/yyyy - HH:mm:ss");
            entity.setDateActivity(format.parse(vo.getDateActivity()));
            entity.setType(vo.getType());
            Student student = DAOFactory.getInstance().getStudentDAO().find(vo.getStudentId(), em);
            student.getActivityList().add(entity);
            entity.setStudent(student);
            Subject subject = DAOFactory.getInstance().getSubjectDAO().find(vo.getSubjectCode(), em);
            subject.getActivityList().add(entity);
            entity.setSubject(subject);
            
            String details = new String();
            String url = new String();
            if (vo.getPublicationId() != 0) {
                Publication publication = DAOFactory.getInstance().getPublicationDAO().find(vo.getPublicationId(), em);
                publication.getActivityList().add(entity);
                entity.setPublication(publication);
                if (vo.getType().equals("Comment")){
                    details = "ha comentado en el recurso ";
                }
                if (vo.getType().equals("Rating")){
                    details = "ha calificado el recurso ";
                }
                url = "resourceView.xhtml";
            }
            if (vo.getTopicId() != 0) {
                Topic topic = DAOFactory.getInstance().getTopicDAO().find(vo.getTopicId(), em);
                topic.getActivityList().add(entity);
                entity.setTopic(topic);
                if (vo.getType().equals("Comment")){
                    details = "ha respondido en el tema ";
                }
                url = "topicView.xhtml";
            }
            if (vo.getTutorId() != 0) {
                Tutor tutor = DAOFactory.getInstance().getTutorDAO().find(vo.getTutorId(), em);
                tutor.getActivityList().add(entity);
                entity.setTutor(tutor);
                if (vo.getType().equals("Comment")){
                    details = "ha comentado sobre el tutor ";
                }
                if (vo.getType().equals("Rating")){
                    details = "ha calificado al tutor ";
                }
                url = "subject-truknow.xhtml";
            }
            if (vo.getType().equals("Publication")){
                details = "ha publicado un nuevo recurso: ";
            } else if (vo.getType().equals("Topic")){
                details = "ha creado un nuevo tema en el foro: ";
            }
            entity.setDetails(details);
            entity.setUrl(url);
            
            DAOFactory.getInstance().getActivityDAO().persist(entity, em);
        } catch (ParseException ex) {
            Logger.getLogger(ActivityService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ActivityVo find(Object id, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(ActivityVo vo, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Object id, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<ActivityVo> getList(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public List<ActivityVo> getActivitiesBySubject(EntityManager em, Integer subjectCode) {
        List<ActivityVo> activityVo = new ArrayList<ActivityVo>();
        List<Activity> activities = DAOFactory.getInstance().getActivityDAO().getActivitiesBySubject(em, subjectCode);
        for (Activity entity : activities ){
            activityVo.add(entity.toVo());
        }    
        return activityVo;
    }

    public List<ActivityVo> getActivitiesBySubjectsOfStudent(EntityManager em, int studentId) {
        List<ActivityVo> activityVo = new ArrayList<ActivityVo>();
        Student student = DAOFactory.getInstance().getStudentDAO().find(studentId, em);
        for (Subject subject : student.getSubjectList()){
            for (Activity activity : subject.getActivityList()){
                activityVo.add(activity.toVo());
            }
        }
        
        return activityVo;
    }
}
