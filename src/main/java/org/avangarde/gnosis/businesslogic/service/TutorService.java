/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avangarde.gnosis.businesslogic.service;

import java.util.List;
import javax.persistence.EntityManager;
import org.avangarde.gnosis.vo.TutorVo;

/**
 *
 * @author Familia Martinez
 */
public class TutorService implements IService<TutorVo> {

    private static TutorService instance;

    public static synchronized TutorService getInstance() {
        if (instance == null) {
            instance = new TutorService();
        }
        return instance;
    }

    @Override
    public void persist(TutorVo vo, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public TutorVo find(Object id, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(TutorVo vo, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Object id, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<TutorVo> getList(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
