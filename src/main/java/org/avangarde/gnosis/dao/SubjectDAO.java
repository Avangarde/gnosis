/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avangarde.gnosis.dao;

import java.util.List;
import javax.persistence.EntityManager;
import org.avangarde.gnosis.entity.Subject;

/**
 *
 * @author Familia Martinez
 */
public class SubjectDAO implements IDAO<Subject> {

    private static SubjectDAO instance;

    public static synchronized SubjectDAO getInstance() {
        if (instance == null) {
            instance = new SubjectDAO();
        }
        return instance;
    }

    @Override
    public void persist(Subject entity, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Subject find(Object id, EntityManager em) {
        return (Subject) em.find(Subject.class, id);
    }

    @Override
    public void update(Subject entity, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Object id, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Subject> getList(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
