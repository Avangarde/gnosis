/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avangarde.gnosis.businesslogic.service;

import java.util.List;
import javax.persistence.EntityManager;
import org.avangarde.gnosis.vo.LikeDislikeVo;

/**
 *
 * @author Familia Martinez
 */
public class LikeDislikeService implements IService<LikeDislikeVo> {

    private static LikeDislikeService instance;
    
    private LikeDislikeService() {
    }

    public static synchronized LikeDislikeService getInstance() {
        if (instance == null) {
            instance = new LikeDislikeService();
        }
        return instance;
    }

    @Override
    public void create(LikeDislikeVo vo, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public LikeDislikeVo find(Object id, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(LikeDislikeVo vo, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Object id, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<LikeDislikeVo> getList(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
