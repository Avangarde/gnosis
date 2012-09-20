/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avangarde.gnosis.dao;

import java.util.List;
import javax.persistence.EntityManager;
import org.avangarde.gnosis.entity.Publication;

/**
 *
 * @author Familia Martinez
 */
public class PublicationDAO implements IDAO<Publication> {
    private static PublicationDAO instance;

    public static synchronized PublicationDAO getInstance() {
        if (instance == null) {
            instance = new PublicationDAO();
        }
        return instance;
    }

    @Override
    public void persist(Publication entity, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Publication find(Object id, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(Publication entity, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Object id, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Publication> getList(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
