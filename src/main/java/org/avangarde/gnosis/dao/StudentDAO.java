package org.avangarde.gnosis.dao;

import java.util.List;
import javax.persistence.EntityManager;
import org.avangarde.gnosis.entity.Student;

/**
 *
 * @author Alexander
 */
public class StudentDAO implements IDAO<Student> {

    private static StudentDAO instance;

    public static synchronized StudentDAO getInstance() {
        if (instance == null) {
            instance = new StudentDAO();
        }
        return instance;
    }

    @Override
    public void persist(Student entity, EntityManager em) {
        em.persist(entity);
    }

    @Override
    public Student find(Object id, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(Student entity, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Object id, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Student> getList(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Student login(Student entity, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
