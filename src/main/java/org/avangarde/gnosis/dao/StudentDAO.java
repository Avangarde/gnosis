package org.avangarde.gnosis.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import org.avangarde.gnosis.entity.Student;

/**
 *
 * @author Alexander
 */
public class StudentDAO implements IDAO<Student> {

    private static StudentDAO instance;

    private StudentDAO() {
    }

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
        em.merge(entity);
    }

    @Override
    public void delete(Object id, EntityManager em) {
        Student student = (Student) em.getReference(Student.class, id);
        em.remove(student);
    }

    @Override
    public List<Student> getList(EntityManager em) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Student.class));
        Query q = em.createQuery(cq);
        return q.getResultList();
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
    
    public List<Student> getStudents(String query, EntityManager em) {
        List<Student> students = new ArrayList<Student>();

        Query q = em.createQuery("SELECT t FROM Student t WHERE t.userName LIKE :userName").
                setParameter("userName", "%" + query + "%");

        try {
            students = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    public Student findByUserName(String userName, EntityManager em) {
        Student student = new Student();

        Query q = em.createQuery("SELECT t FROM Student t WHERE t.userName LIKE :userName").
                setParameter("userName", userName);

        try {
            student = (Student) q.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return student;
    }
}
