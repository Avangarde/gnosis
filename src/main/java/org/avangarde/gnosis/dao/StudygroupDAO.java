/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avangarde.gnosis.dao;

import java.util.List;
import javax.persistence.EntityManager;
import org.avangarde.gnosis.entity.Studygroup;

/**
 *
 * @author Familia Martinez
 */
public class StudygroupDAO implements IDAO<Studygroup> {

    private static StudygroupDAO instance;

    public static synchronized StudygroupDAO getInstance() {
        if (instance == null) {
            instance = new StudygroupDAO();
        }
        return instance;
    }

    @Override
    public void persist(Studygroup entity, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Studygroup find(Object id, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(Studygroup entity, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Object id, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Studygroup> getList(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
