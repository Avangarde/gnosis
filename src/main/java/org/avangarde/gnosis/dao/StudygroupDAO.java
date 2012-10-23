/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avangarde.gnosis.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import org.avangarde.gnosis.entity.Studygroup;

/**
 *
 * @author Familia Martinez
 */
public class StudygroupDAO implements IDAO<Studygroup> {

    private static StudygroupDAO instance;
    
    private StudygroupDAO() {
    }

    public static synchronized StudygroupDAO getInstance() {
        if (instance == null) {
            instance = new StudygroupDAO();
        }
        return instance;
    }

    @Override
    public void persist(Studygroup entity, EntityManager em) {
        em.persist(entity);
    }

    @Override
    public Studygroup find(Object id, EntityManager em) {
        return (Studygroup) em.find(Studygroup.class, id);
    }

    @Override
    public void update(Studygroup entity, EntityManager em) {
        em.merge(entity);
    }

    @Override
    public void delete(Object id, EntityManager em) {
        Studygroup studygroup = (Studygroup) em.getReference(Studygroup.class, id);
        em.remove(studygroup);
    }

    @Override
    public List<Studygroup> getList(EntityManager em) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Studygroup.class));
        Query q = em.createQuery(cq);
        return q.getResultList();
    }
}
