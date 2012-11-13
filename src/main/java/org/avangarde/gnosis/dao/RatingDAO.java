/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avangarde.gnosis.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import org.avangarde.gnosis.entity.Rating;

/**
 *
 * @author Alexander
 */
public class RatingDAO implements IDAO<Rating> {
    
    private static RatingDAO instance;
    
    private RatingDAO() {
    }

    public static synchronized RatingDAO getInstance() {
        if (instance == null) {
            instance = new RatingDAO();
        }
        return instance;
    }

    @Override
    public void persist(Rating entity, EntityManager em) {
        em.persist(entity);
    }

    @Override
    public Rating find(Object id, EntityManager em) {
        return (Rating) em.find(Rating.class, id);
    }

    @Override
    public void update(Rating entity, EntityManager em) {
        em.merge(entity);
    }

    @Override
    public void delete(Object id, EntityManager em) {
        Rating rating = (Rating) em.getReference(Rating.class, id);
        em.remove(rating);
    }

    @Override
    public List<Rating> getList(EntityManager em) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Rating.class));
        Query q = em.createQuery(cq);
        return q.getResultList();
    }
    
}
