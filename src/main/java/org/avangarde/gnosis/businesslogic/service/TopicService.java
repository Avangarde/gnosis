/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avangarde.gnosis.businesslogic.service;

import java.util.List;
import javax.persistence.EntityManager;
import org.avangarde.gnosis.vo.TopicVo;

/**
 *
 * @author Familia Martinez
 */
public class TopicService implements IService<TopicVo> {

    private static TopicService instance;

    public static synchronized TopicService getInstance() {
        if (instance == null) {
            instance = new TopicService();
        }
        return instance;
    }

    @Override
    public void persist(TopicVo vo, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public TopicVo find(Object id, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(TopicVo vo, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Object id, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<TopicVo> getList(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
