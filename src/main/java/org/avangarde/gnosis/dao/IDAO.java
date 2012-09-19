package org.avangarde.gnosis.dao;

import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Alexander
 */
public interface IDAO<E> {

    void persist(E entity, EntityManager em);

    E find(Object id, EntityManager em);

    void update(E entity, EntityManager em);

    void delete(Object id, EntityManager em);

    List<E> getList(EntityManager em);
}
