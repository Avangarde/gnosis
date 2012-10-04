/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avangarde.gnosis.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
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
        return (Program) em.find(Program.class, id);
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
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Program.class));
        Query q = em.createQuery(cq);
        return q.getResultList();
    }
}
