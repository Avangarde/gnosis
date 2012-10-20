package org.avangarde.gnosis.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
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
        return (Student) em.find(Student.class, id);
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
        Student student;
        Query q = em.createQuery("SELECT u FROM Student u "
                + "WHERE u.userName LIKE :username "
                + "AND u.password LIKE :password")
                .setParameter("username", entity.getUserName())
                .setParameter("password", entity.getPassword());
        try {
            student = (Student) q.getSingleResult();
        } catch (NonUniqueResultException e) {
            student = (Student) q.getResultList().get(0);
        } catch (NoResultException e) {
            student = null;
        }
        return student;
    }
}
