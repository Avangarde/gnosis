/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avangarde.gnosis.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import org.avangarde.gnosis.entity.TutorSubject;

/**
 *
 * @author Familia Martinez
 */
public class TutorSubjectDAO implements IDAO<TutorSubject> {

    private static TutorSubjectDAO instance;

    private TutorSubjectDAO() {
    }

    public static synchronized TutorSubjectDAO getInstance() {
        if (instance == null) {
            instance = new TutorSubjectDAO();
        }
        return instance;
    }

    @Override
    public void persist(TutorSubject entity, EntityManager em) {
        em.persist(entity);
    }

    @Override
    public TutorSubject find(Object id, EntityManager em) {
        return (TutorSubject) em.find(TutorSubject.class, id);
    }

    @Override
    public void update(TutorSubject entity, EntityManager em) {
        em.merge(entity);
    }

    @Override
    public void delete(Object id, EntityManager em) {
        TutorSubject tutorSubject = (TutorSubject) em.getReference(TutorSubject.class, id);
        em.remove(tutorSubject);
    }

    @Override
    public List<TutorSubject> getList(EntityManager em) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(TutorSubject.class));
        Query q = em.createQuery(cq);
        return q.getResultList();
    }

    public TutorSubject findByUsernameAndCode(Integer subjectCode, String userName, EntityManager em) {

        TutorSubject tutorSubject = new TutorSubject();
        Query q = em.createQuery("SELECT p FROM TutorSubject p WHERE p.tutor.userName LIKE :userName AND p.subject.code LIKE :code ").
                setParameter("userName", userName).setParameter("code", subjectCode.toString());
        try {
            tutorSubject = (TutorSubject) q.getSingleResult();
        } catch (Exception e) {
            tutorSubject = null;
        }
        return tutorSubject;

    }
}
