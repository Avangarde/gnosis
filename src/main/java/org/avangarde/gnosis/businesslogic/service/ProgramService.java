/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avangarde.gnosis.businesslogic.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.persistence.EntityManager;
import org.avangarde.gnosis.dao.DAOFactory;
import org.avangarde.gnosis.entity.Program;
import org.avangarde.gnosis.vo.ProgramVo;

/**
 *
 * @author Familia Martinez
 */
public class ProgramService implements IService<ProgramVo> {

    private static ProgramService instance;
    
    private ProgramService() {
    }

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
        List<ProgramVo> list = new ArrayList<ProgramVo>();
        for (Program program : DAOFactory.getInstance().getProgramDAO().getList(em)) {
            list.add((program).toVo());
        }
        Collections.sort(list, new Comparator() {

            @Override
            public int compare(Object o1, Object o2) {
                ProgramVo p1 = (ProgramVo) o1;
                ProgramVo p2 = (ProgramVo) o2;
                return p1.getName().compareTo(p2.getName());
            }
        });
        return list;
    }
}
