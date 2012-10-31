/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avangarde.gnosis.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import org.avangarde.gnosis.entity.Topic;
import org.avangarde.gnosis.vo.TopicVo;

/**
 *
 * @author Familia Martinez
 */
public class TopicDAO implements IDAO<Topic> {

    private static TopicDAO instance;

    private TopicDAO() {
    }

    public static synchronized TopicDAO getInstance() {
        if (instance == null) {
            instance = new TopicDAO();
        }
        return instance;
    }

    @Override
    public void persist(Topic entity, EntityManager em) {
        em.persist(entity);
    }

    @Override
    public Topic find(Object id, EntityManager em) {
        return (Topic) em.find(Topic.class, id);
    }

    @Override
    public void update(Topic entity, EntityManager em) {
        em.merge(entity);
    }

    @Override
    public void delete(Object id, EntityManager em) {
        Topic topic = (Topic) em.getReference(Topic.class, id);
        em.remove(topic);
    }

    @Override
    public List<Topic> getList(EntityManager em) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Topic.class));
        Query q = em.createQuery(cq);
        return q.getResultList();
    }
    
    public int getNewId(EntityManager em){
        Integer lastId;
        lastId = ((Integer)em.createQuery("SELECT MAX(t.id) FROM Topic t").getSingleResult());
        int newId = (lastId == null) ? 0 : lastId;
        return newId+1;
    }

    public List<Topic> getTopicsBySubject(EntityManager em, Integer subjectCode) {
        List<Topic> topics = new ArrayList<Topic>();
        Query q = em.createQuery("SELECT t FROM Topic t WHERE t.subject.code LIKE :subjectCode").setParameter("subjectCode", subjectCode.toString());
        try {
            topics = q.getResultList();
        } catch (Exception e) {
            topics = new ArrayList<Topic>();
        }
        return topics;
    }
}
