/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avangarde.gnosis.businesslogic.service;

import java.util.List;
import javax.persistence.EntityManager;
import org.avangarde.gnosis.vo.StudygroupVo;

/**
 *
 * @author Familia Martinez
 */
public class StudygroupService implements IService<StudygroupVo> {

    private static StudygroupService instance;
    
    private StudygroupService() {
    }

    public static synchronized StudygroupService getInstance() {
        if (instance == null) {
            instance = new StudygroupService();
        }
        return instance;
    }

    @Override
    public void create(StudygroupVo vo, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public StudygroupVo find(Object id, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(StudygroupVo vo, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Object id, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<StudygroupVo> getList(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
