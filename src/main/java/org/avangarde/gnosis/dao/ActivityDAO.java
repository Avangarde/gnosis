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
import org.avangarde.gnosis.entity.Activity;

/**
 *
 * @author Familia Martinez
 */
public class ActivityDAO implements IDAO<Activity> {

    private static ActivityDAO instance;

    private ActivityDAO() {
    }

    public static synchronized ActivityDAO getInstance() {
        if (instance == null) {
            instance = new ActivityDAO();
        }
        return instance;
    }

    @Override
    public void persist(Activity entity, EntityManager em) {
        em.persist(entity);
    }

    @Override
    public Activity find(Object id, EntityManager em) {
        return (Activity) em.find(Activity.class, id);
    }

    @Override
    public void update(Activity entity, EntityManager em) {
        em.merge(entity);
    }

    @Override
    public void delete(Object id, EntityManager em) {
        Activity activity = (Activity) em.getReference(Activity.class, id);
        em.remove(activity);
    }

    @Override
    public List<Activity> getList(EntityManager em) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Activity.class));
        Query q = em.createQuery(cq);
        return q.getResultList();
    }
    
    public List<Activity> getActivitiesBySubject(EntityManager em, Integer subjectCode) {
        List<Activity> activities;
        Query q = em.createQuery("SELECT a FROM Activity a WHERE a.subject.code LIKE :subjectCode "
                + "ORDER BY a.dateActivity").setParameter("subjectCode", subjectCode.toString());
        try {
            activities = q.getResultList();
        } catch (Exception e) {
            activities = new ArrayList<Activity>();
        }
        return activities;
    }
}
