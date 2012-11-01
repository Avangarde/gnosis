/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avangarde.gnosis.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import org.avangarde.gnosis.entity.LikeDislike;

/**
 *
 * @author Familia Martinez
 */
public class LikeDislikeDAO implements IDAO<LikeDislike> {

    private static LikeDislikeDAO instance;
    
    private LikeDislikeDAO() {
    }

    public static synchronized LikeDislikeDAO getInstance() {
        if (instance == null) {
            instance = new LikeDislikeDAO();
        }
        return instance;
    }

    @Override
    public void persist(LikeDislike entity, EntityManager em) {
        em.persist(entity);
    }

    @Override
    public LikeDislike find(Object id, EntityManager em) {
        return (LikeDislike) em.find(LikeDislike.class, id);
    }

    @Override
    public void update(LikeDislike entity, EntityManager em) {
        em.merge(entity);
    }

    @Override
    public void delete(Object id, EntityManager em) {
        LikeDislike likeDislike = (LikeDislike) em.getReference(LikeDislike.class, id);
        em.remove(likeDislike);
    }

    @Override
    public List<LikeDislike> getList(EntityManager em) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(LikeDislike.class));
        Query q = em.createQuery(cq);
        return q.getResultList();
    }
}
