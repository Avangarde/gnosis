/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avangarde.gnosis.businesslogic.service;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.avangarde.gnosis.dao.DAOFactory;
import org.avangarde.gnosis.dao.StudentDAO;
import org.avangarde.gnosis.dao.SubjectDAO;
import org.avangarde.gnosis.entity.Student;
import org.avangarde.gnosis.entity.Subject;
import org.avangarde.gnosis.vo.SubjectVo;

/**
 *
 * @author Familia Martinez
 */
public class SubjectService implements IService<SubjectVo> {

    private static SubjectService instance;

    public static synchronized SubjectService getInstance() {
        if (instance == null) {
            instance = new SubjectService();
        }
        return instance;
    }

    @Override
    public void create(SubjectVo vo, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public SubjectVo find(Object id, EntityManager em) {
        Subject subject = DAOFactory.getInstance().getSubjectDAO().find(id, em);
        if (subject != null) {
            return subject.toVo();
        } else {
            return null;
        }
    }

    @Override
    public void update(SubjectVo vo, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Object id, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<SubjectVo> getList(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean suscribeStudent(Integer userId, Integer subjectCode, EntityManager em) {
        try {
            StudentDAO studentDAO = DAOFactory.getInstance().getStudentDAO();
            Student student = studentDAO.find(userId, em);

            SubjectDAO subjectDAO = DAOFactory.getInstance().getSubjectDAO();
            Subject subject = subjectDAO.find(subjectCode, em);

            subject.getStudentList().add(student);
            student.getSubjectList().add(subject);

            subjectDAO.update(subject, em);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<SubjectVo> getSubjectsByName(String query, EntityManager em) {
        List<SubjectVo> subjects = new ArrayList<SubjectVo>();
        List<Subject> entities = DAOFactory.getInstance().getSubjectDAO().getSubjectsByname(query, em);
        for (Subject subject : entities) {
            subjects.add(subject.toVo());
        }
        return subjects;
    }

    public boolean isTheStudentSubscribed(Integer userId, Integer subjectCode, EntityManager em) {
        SubjectDAO subjectDAO = DAOFactory.getInstance().getSubjectDAO();
        Subject subject = subjectDAO.find(subjectCode, em);

        StudentDAO studentDAO = DAOFactory.getInstance().getStudentDAO();
        Student student = studentDAO.find(userId, em);

        List<Student> students = subject.getStudentList();
        
        for (Student student1 : students) {
            if (student.getId() == student1.getId()) {
                return true;
            }
        }
        return false;
    }

    public void unSuscribeStudent(Integer userId, Integer subjectCode, EntityManager em) {
        try {
            StudentDAO studentDAO = DAOFactory.getInstance().getStudentDAO();
            Student student = studentDAO.find(userId, em);

            SubjectDAO subjectDAO = DAOFactory.getInstance().getSubjectDAO();
            Subject subject = subjectDAO.find(subjectCode, em);

            subject.getStudentList().remove(student);
            student.getSubjectList().remove(subject);

            subjectDAO.update(subject, em);
            studentDAO.update(student, em);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
