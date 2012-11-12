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

    public List<Comment> getCommentsByTopic(EntityManager em, Integer topicId) {
        List<Comment> comments;
        Query q = em.createQuery("SELECT c FROM Comment c WHERE c.topic.id LIKE :topicId").setParameter("topicId", topicId.toString());
        try {
            comments = q.getResultList();
        } catch (Exception e) {
            comments = new ArrayList<Comment>();
        }
        return comments;
    }
    
    public List<Comment> getCommentsbyPublication(int pubId, EntityManager em) {
        List<Comment> comments;
        Query q = em.createQuery("SELECT c FROM Comment c WHERE c.publication.id LIKE :pubId").setParameter("pubId", new Integer(pubId).toString());
        try {
            comments = q.getResultList();
        } catch (Exception e) {
            comments = new ArrayList<Comment>();
        }
        return comments;
    }
}
