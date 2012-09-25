package org.avangarde.gnosis.businesslogic.facade;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.avangarde.gnosis.businesslogic.service.IService;

/**
 *
 * @author Alexander
 */
public class Facade<VO> {

    protected EntityManagerFactory emf;
    protected EntityManager em;
    protected IService service;

    public Facade(String PUName, IService service) {
        emf = Persistence.createEntityManagerFactory(PUName);
        em = emf.createEntityManager();
        this.service = service;
    }

    public void create(VO vo) {
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            service.create(vo, em);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (em != null && tx != null) {
                tx.rollback();
            }
        } finally {
            if (em != null) {
                em.clear();
                em.close();
            }
        }
    }

    public VO find(Object id) {
        try {
            return (VO) service.find(id, em);
        } finally {
            if (em != null) {
                em.clear();
                em.close();
            }
        }

    }

    public boolean update(VO vo) {
        EntityManager em = null;
        EntityTransaction tx = null;
        boolean ret = false;
        try {
            tx = em.getTransaction();
            tx.begin();
            service.update(vo, em);
            tx.commit();
            ret = true;
        } catch (Exception e) {
            e.printStackTrace();
            if (em != null && tx != null) {
                tx.rollback();
            }
        } finally {
            if (em != null) {
                em.clear();
                em.close();
            }
            return ret;
        }
    }

    public boolean delete(Object id) {
        EntityManager em = null;
        EntityTransaction tx = null;
        boolean ret = false;
        try {
            tx = em.getTransaction();
            tx.begin();
            service.delete(id, em);
            tx.commit();
            ret = true;
        } catch (Exception e) {
            e.printStackTrace();
            if (em != null && tx != null) {
                tx.rollback();
            }
        } finally {
            if (em != null) {
                em.clear();
                em.close();
            }
            return ret;
        }
    }

    public List<VO> getList() {
        try {
            return service.getList(em);
        } finally {
            if (em != null) {
                em.clear();
                em.close();
            }
        }
    }
}
