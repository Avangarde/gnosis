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
import org.avangarde.gnosis.entity.Program;

/**
 *
 * @author Familia Martinez
 */
public class ProgramDAO implements IDAO<Program> {

    private static ProgramDAO instance;

    private ProgramDAO() {
    }

    public static synchronized ProgramDAO getInstance() {
        if (instance == null) {
            instance = new ProgramDAO();
        }
        return instance;
    }

    @Override
    public void persist(Program entity, EntityManager em) {
        em.persist(entity);
    }

    @Override
    public Program find(Object id, EntityManager em) {
        return (Program) em.find(Program.class, id);
    }

    @Override
    public void update(Program entity, EntityManager em) {
        em.merge(entity);
    }

    @Override
    public void delete(Object id, EntityManager em) {
        Program program = (Program) em.getReference(Program.class, id);
        em.remove(program);
    }

    @Override
    public List<Program> getList(EntityManager em) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Program.class));
        Query q = em.createQuery(cq);
        return q.getResultList();
    }

    public List<String> getProgramsBySubject(EntityManager em, Integer subjectCode) {
        List<String> list;
        Query q = em.createQuery("SELECT p.name FROM Program p INNER JOIN p.subjectList s "
                + "WHERE s.code LIKE :subjectCode ").setParameter("subjectCode", subjectCode.toString());
        try {
            list = q.getResultList();
        } catch (Exception e) {
            list = new ArrayList<String>();
        }
        return list;
    }
}
