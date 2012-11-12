/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avangarde.gnosis.businesslogic.service;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.avangarde.gnosis.dao.DAOFactory;
import org.avangarde.gnosis.entity.Student;
import org.avangarde.gnosis.entity.Subject;
import org.avangarde.gnosis.entity.Topic;
import org.avangarde.gnosis.vo.TopicVo;

/**
 *
 * @author Familia Martinez
 */
public class TopicService implements IService<TopicVo> {

    private static TopicService instance;
    
    private TopicService() {
    }

    public static synchronized TopicService getInstance() {
        if (instance == null) {
            instance = new TopicService();
        }
        return instance;
    }

    @Override
    public void create(TopicVo vo, EntityManager em) {
        Topic entity = new Topic();
        entity.setId(vo.getId());
        entity.setTitle(vo.getTitle());
        entity.setDateStarted(vo.getDateStarted());
        Student student = DAOFactory.getInstance().getStudentDAO().find(vo.getStudentId(), em);
        entity.setStudent(student);
        student.getTopicList().add(entity);
        Subject subject = DAOFactory.getInstance().getSubjectDAO().find(vo.getSubjectCode(), em);
        entity.setSubject(subject);
        subject.getTopicList().add(entity);
        
        DAOFactory.getInstance().getTopicDAO().persist(entity, em);
    }

    @Override
    public TopicVo find(Object id, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(TopicVo vo, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Object id, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<TopicVo> getList(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public int getNewId(EntityManager em){
        return DAOFactory.getInstance().getTopicDAO().getNewId(em);
    }

    public List<TopicVo> getTopicsBySubject(EntityManager em, Integer subjectCode) {
        List<TopicVo> topicVo = new ArrayList<TopicVo>();
        List<Topic> topics = DAOFactory.getInstance().getTopicDAO().getTopicsBySubject(em, subjectCode);
        for (Topic entity : topics ){
            topicVo.add(entity.toVo());
        }    
        return topicVo;
    }
    
}
