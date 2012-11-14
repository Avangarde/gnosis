package org.avangarde.gnosis.businesslogic.service;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.avangarde.gnosis.dao.DAOFactory;
import org.avangarde.gnosis.entity.Program;
import org.avangarde.gnosis.entity.Student;
import org.avangarde.gnosis.entity.Tutor;
import org.avangarde.gnosis.vo.StudentVo;
import org.avangarde.gnosis.vo.TutorVo;

/**
 *
 * @author Alexander
 */
public class StudentService implements IService<StudentVo> {

    private static StudentService instance;

    private StudentService() {
    }

    public static synchronized StudentService getInstance() {
        if (instance == null) {
            instance = new StudentService();
        }
        return instance;
    }

    @Override
    public void create(StudentVo vo, EntityManager em) {
        Student entity = new Student();
        entity.setFirstName(vo.getFirstName());
        entity.setLastName(vo.getLastName());
        entity.setUserName(vo.getUserName());
        entity.setEmail(vo.getEmail());
        entity.setPassword(vo.getPassword());
        entity.setUrlPhoto("./resources/images/childish_User.png");

        Program program = DAOFactory.getInstance().getProgramDAO().find(vo.getProgramId(), em);
        program.getStudentList().add(entity);
        entity.setProgram(program);

        DAOFactory.getInstance().getStudentDAO().persist(entity, em);
    }

    @Override
    public StudentVo find(Object id, EntityManager em) {
        Student student = DAOFactory.getInstance().getStudentDAO().find(id, em);
        if (student != null) {
            return student.toVo();
        } else {
            return null;
        }
    }

    @Override
    public void update(StudentVo vo, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Object id, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<StudentVo> getList(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public StudentVo login(StudentVo vo, EntityManager em) {
        Student entity = new Student();
        entity.setUserName(vo.getUserName());
        entity.setPassword(vo.getPassword());

        Student alumno = DAOFactory.getInstance().getStudentDAO().login(entity, em);
        return alumno != null ? alumno.toVo() : null;

    }

    public boolean isTutor(TutorVo vo, EntityManager em) {
        Tutor entity = new Tutor();
        entity.setUserName(vo.getUserName());

        Tutor tutor = DAOFactory.getInstance().getTutorDAO().findByUsername(entity, em);
        if (tutor != null) {
            vo = tutor.toVo();
            return true;
        } else {
            return false;
        }


    }
    
    public List<StudentVo> getStudents(String query, EntityManager em) {
        List<StudentVo> students = new ArrayList<StudentVo>();
        List<Student> entities = DAOFactory.getInstance().getStudentDAO().getStudents(query, em);
        for (Student student : entities) {
            students.add(student.toVo());
        }
        return students;
    }
}
