/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avangarde.gnosis.dao;

import java.util.List;
import javax.persistence.EntityManager;
import org.avangarde.gnosis.entity.Tutor;

/**
 *
 * @author Familia Martinez
 */
public class TutorDAO implements IDAO<Tutor> {

    private static TutorDAO instance;

    public static synchronized TutorDAO getInstance() {
        if (instance == null) {
            instance = new TutorDAO();
        }
        return instance;
    }

    @Override
    public void persist(Tutor entity, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Tutor find(Object id, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(Tutor entity, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Object id, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Tutor> getList(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
