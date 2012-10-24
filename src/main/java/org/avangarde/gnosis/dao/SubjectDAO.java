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
import org.avangarde.gnosis.entity.Subject;

/**
 *
 * @author Familia Martinez
 */
public class SubjectDAO implements IDAO<Subject> {

    private static SubjectDAO instance;
    
    private SubjectDAO() {
    }

    public static synchronized SubjectDAO getInstance() {
        if (instance == null) {
            instance = new SubjectDAO();
        }
        return instance;
    }

    @Override
    public void persist(Subject entity, EntityManager em) {
        em.persist(entity);
    }

    @Override
    public Subject find(Object id, EntityManager em) {
        return (Subject) em.find(Subject.class, id);
    }

    @Override
    public void update(Subject entity, EntityManager em) {
        em.merge(entity);
    }

    @Override
    public void delete(Object id, EntityManager em) {
        Subject subject = (Subject) em.getReference(Subject.class, id);
        em.remove(subject);
    }

    @Override
    public List<Subject> getList(EntityManager em) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Subject.class));
        Query q = em.createQuery(cq);
        return q.getResultList();
    }
    
    public List<Subject> getSubjectsByProgram(Integer programCode, EntityManager em){
        List<Subject> subjects = new ArrayList<Subject>();
        
        Query q = em.createQuery("SELECT s FROM Subject s INNER JOIN s.programList p WHERE p.code LIKE :programCode").
                setParameter("programCode", programCode.toString());

        try {
            subjects = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subjects;
    }
}
