/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avangarde.gnosis.dao;

import java.util.List;
import javax.persistence.EntityManager;
import org.avangarde.gnosis.entity.Topic;

/**
 *
 * @author Familia Martinez
 */
public class TopicDAO implements IDAO<Topic> {

    private static TopicDAO instance;

    public static synchronized TopicDAO getInstance() {
        if (instance == null) {
            instance = new TopicDAO();
        }
        return instance;
    }

    @Override
    public void persist(Topic entity, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Topic find(Object id, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(Topic entity, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Object id, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Topic> getList(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
