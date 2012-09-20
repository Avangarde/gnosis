/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avangarde.gnosis.dao;

import java.util.List;
import javax.persistence.EntityManager;
import org.avangarde.gnosis.entity.LikeDislike;

/**
 *
 * @author Familia Martinez
 */
public class LikeDislikeDAO implements IDAO<LikeDislike> {
    private static LikeDislikeDAO instance;

    public static synchronized LikeDislikeDAO getInstance() {
        if (instance == null) {
            instance = new LikeDislikeDAO();
        }
        return instance;
    }

    @Override
    public void persist(LikeDislike entity, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public LikeDislike find(Object id, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(LikeDislike entity, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Object id, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<LikeDislike> getList(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
