/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avangarde.gnosis.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import org.avangarde.gnosis.entity.Tutor;

/**
 *
 * @author Familia Martinez
 */
public class TutorDAO implements IDAO<Tutor> {

    private static TutorDAO instance;
    
    private TutorDAO() {
    }

    public static synchronized TutorDAO getInstance() {
        if (instance == null) {
            instance = new TutorDAO();
        }
        return instance;
    }

    @Override
    public void persist(Tutor entity, EntityManager em) {
        em.persist(entity);
    }

    @Override
    public Tutor find(Object id, EntityManager em) {
        return (Tutor) em.find(Tutor.class, id);
    }

    @Override
    public void update(Tutor entity, EntityManager em) {
        em.merge(entity);
    }

    @Override
    public void delete(Object id, EntityManager em) {
        Tutor tutor = (Tutor) em.getReference(Tutor.class, id);
        em.remove(tutor);
    }

    @Override
    public List<Tutor> getList(EntityManager em) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Tutor.class));
        Query q = em.createQuery(cq);
        return q.getResultList();
    }
}
