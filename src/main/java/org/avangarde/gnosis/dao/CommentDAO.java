/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avangarde.gnosis.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import org.avangarde.gnosis.entity.Comment;

/**
 *
 * @author Familia Martinez
 */
public class CommentDAO implements IDAO<Comment> {

    private static CommentDAO instance;

    private CommentDAO() {
    }

    public static synchronized CommentDAO getInstance() {
        if (instance == null) {
            instance = new CommentDAO();
        }
        return instance;
    }

    @Override
    public void persist(Comment entity, EntityManager em) {
        em.persist(entity);
    }

    @Override
    public Comment find(Object id, EntityManager em) {
        return (Comment) em.find(Comment.class, id);
    }

    @Override
    public void update(Comment entity, EntityManager em) {
        em.merge(entity);
    }

    @Override
    public void delete(Object id, EntityManager em) {
        Comment comment = (Comment) em.getReference(Comment.class, id);
        em.remove(comment);
    }

    @Override
    public List<Comment> getList(EntityManager em) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Comment.class));
        Query q = em.createQuery(cq);
        return q.getResultList();
    }
}
