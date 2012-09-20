/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avangarde.gnosis.dao;

import java.util.List;
import javax.persistence.EntityManager;
import org.avangarde.gnosis.entity.Program;

/**
 *
 * @author Familia Martinez
 */
public class ProgramDAO implements IDAO<Program> {

    private static ProgramDAO instance;

    public static synchronized ProgramDAO getInstance() {
        if (instance == null) {
            instance = new ProgramDAO();
        }
        return instance;
    }

    @Override
    public void persist(Program entity, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Program find(Object id, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(Program entity, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Object id, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Program> getList(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
