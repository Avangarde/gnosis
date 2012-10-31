package org.avangarde.gnosis.businesslogic.service;

import java.util.List;
import javax.persistence.EntityManager;
import org.avangarde.gnosis.dao.DAOFactory;
import org.avangarde.gnosis.entity.Program;
import org.avangarde.gnosis.entity.Student;
import org.avangarde.gnosis.vo.StudentVo;

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

        Program program = DAOFactory.getInstance().getProgramDAO().find(vo.getProgramId(), em);
        program.getStudentList().add(entity);
        entity.setProgram(program);

        DAOFactory.getInstance().getStudentDAO().persist(entity, em);
    }

    @Override
    public StudentVo find(Object id, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
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
        return alumno != null? alumno.toVo():null;  
    }
}
