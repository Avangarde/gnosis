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
import org.avangarde.gnosis.entity.Event;
import org.avangarde.gnosis.entity.Student;
import org.avangarde.gnosis.entity.Subject;
import org.avangarde.gnosis.vo.EventVo;

/**
 *
 * @author Familia Martinez
 */
public class EventService implements IService<EventVo> {

    private static EventService instance;
    
    private EventService() {
    }

    public static synchronized EventService getInstance() {
        if (instance == null) {
            instance = new EventService();
        }
        return instance;
    }

    @Override
    public void create(EventVo vo, EntityManager em) {
try {
            Event entity = new Event();
            SimpleDateFormat format = new SimpleDateFormat ("dd/MM/yyyy - HH:mm:ss");
            entity.setName(vo.getName());
            entity.setHour(vo.getHour());
            entity.setId(vo.getId());
            entity.setStartDate((vo.getStartDate()));
            entity.setType(vo.getType());
            Student student = DAOFactory.getInstance().getStudentDAO().find(vo.getStudentId(), em);
            student.getEventList().add(entity);
            entity.setStudent(student);
            Subject subject = DAOFactory.getInstance().getSubjectDAO().find(vo.getSubjectCode(), em);
            subject.getEventList().add(entity);
            entity.setSubject(subject);
            
            DAOFactory.getInstance().getEventDAO().persist(entity, em);
        } catch (ParseException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    

    }

    @Override
    public EventVo find(Object id, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(EventVo vo, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Object id, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<EventVo> getList(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    public List<EventVo> getEventsFromSubject(EntityManager em, Integer subjectCode) {
        List<EventVo> eventVo = new ArrayList<EventVo>();
        List<Event> events = DAOFactory.getInstance().getEventDAO().getEventsFromSubject(em, subjectCode);
        for (Event entity : events ){
            eventVo.add(entity.toVo());
        }    
        return eventVo;
    }



}
