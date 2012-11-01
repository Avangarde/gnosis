/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avangarde.gnosis.businesslogic.service;

import java.util.List;
import javax.persistence.EntityManager;
import org.avangarde.gnosis.vo.TutorSubjectVo;

/**
 *
 * @author Familia Martinez
 */
public class TutorSubjectService implements IService<TutorSubjectVo> {

    private static TutorSubjectService instance;
    
    private TutorSubjectService() {
    }

    public static synchronized TutorSubjectService getInstance() {
        if (instance == null) {
            instance = new TutorSubjectService();
        }
        return instance;
    }

    @Override
    public void create(TutorSubjectVo vo, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public TutorSubjectVo find(Object id, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(TutorSubjectVo vo, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Object id, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<TutorSubjectVo> getList(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
