/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avangarde.gnosis.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import org.avangarde.gnosis.entity.Event;

/**
 *
 * @author Familia Martinez
 */
public class EventDAO implements IDAO<Event> {

    private static EventDAO instance;

    private EventDAO() {
    }

    public static synchronized EventDAO getInstance() {
        if (instance == null) {
            instance = new EventDAO();
        }
        return instance;
    }

    @Override
    public void persist(Event entity, EntityManager em) {
        em.persist(entity);
    }

    @Override
    public Event find(Object id, EntityManager em) {
        return (Event) em.find(Event.class, id);
    }

    @Override
    public void update(Event entity, EntityManager em) {
        em.merge(entity);
    }

    @Override
    public void delete(Object id, EntityManager em) {
        Event event = (Event) em.getReference(Event.class, id);
        em.remove(event);
    }

    @Override
    public List<Event> getList(EntityManager em) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Event.class));
        Query q = em.createQuery(cq);
        return q.getResultList();
    }
}
