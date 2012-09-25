/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avangarde.gnosis.businesslogic.service;

import java.util.List;
import javax.persistence.EntityManager;
import org.avangarde.gnosis.vo.ProgramVo;

/**
 *
 * @author Familia Martinez
 */
public class ProgramService implements IService<ProgramVo> {

    private static ProgramService instance;

    public static synchronized ProgramService getInstance() {
        if (instance == null) {
            instance = new ProgramService();
        }
        return instance;
    }

    @Override
    public void create(ProgramVo vo, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ProgramVo find(Object id, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(ProgramVo vo, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Object id, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<ProgramVo> getList(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
