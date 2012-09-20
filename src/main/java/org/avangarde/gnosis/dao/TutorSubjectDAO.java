/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avangarde.gnosis.dao;

import java.util.List;
import javax.persistence.EntityManager;
import org.avangarde.gnosis.entity.TutorSubject;

/**
 *
 * @author Familia Martinez
 */
public class TutorSubjectDAO implements IDAO<TutorSubject> {
    private static TutorSubjectDAO instance;

    public static synchronized TutorSubjectDAO getInstance() {
        if (instance == null) {
            instance = new TutorSubjectDAO();
        }
        return instance;
    }

    @Override
    public void persist(TutorSubject entity, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public TutorSubject find(Object id, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(TutorSubject entity, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Object id, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<TutorSubject> getList(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
