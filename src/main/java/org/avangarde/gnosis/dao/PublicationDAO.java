/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avangarde.gnosis.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.avangarde.gnosis.entity.Publication;

/**
 *
 * @author Familia Martinez
 */
public class PublicationDAO implements IDAO<Publication> {

    private static PublicationDAO instance;

    public static synchronized PublicationDAO getInstance() {
        if (instance == null) {
            instance = new PublicationDAO();
        }
        return instance;
    }

    @Override
    public void persist(Publication entity, EntityManager em) {
        em.persist(entity);
    }

    @Override
    public Publication find(Object id, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(Publication entity, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Object id, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Publication> getList(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<String> getTopics(EntityManager em) {
        List<String> list;
        Query q = em.createQuery("SELECT DISTINCT p.topic FROM Publication p");
        try{
            list = q.getResultList();
        } catch (Exception e){
            list = new ArrayList<String>();
        }
        return list;
    }

    public List<Publication> getPublicationsByTopic(String topic, EntityManager em) {
        List<Publication> list;
        Query q = em.createQuery("SELECT p FROM Publication p WHERE p.topic LIKE :topic " 
                + "ORDER BY p.title").setParameter("topic", topic);
        try{
            list = q.getResultList();
        } catch (Exception e){
            list = new ArrayList<Publication>();
        }
        return list;
    }
}
