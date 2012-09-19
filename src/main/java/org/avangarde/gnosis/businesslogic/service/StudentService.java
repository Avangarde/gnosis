package org.avangarde.gnosis.businesslogic.service;

import java.util.List;
import javax.persistence.EntityManager;
import org.avangarde.gnosis.vo.StudentVo;

/**
 *
 * @author Alexander
 */
public class StudentService implements IService<StudentVo> {
    
    private static StudentService instance;

    public static synchronized StudentService getInstance() {
        if (instance == null) {
            instance = new StudentService();
        }
        return instance;
    }

    @Override
    public void persist(StudentVo vo, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
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
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
