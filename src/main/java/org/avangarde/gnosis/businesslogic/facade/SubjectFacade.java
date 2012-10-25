package org.avangarde.gnosis.businesslogic.facade;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.avangarde.gnosis.businesslogic.service.IService;
import org.avangarde.gnosis.businesslogic.service.SubjectService;
import org.avangarde.gnosis.vo.SubjectVo;

/**
 *
 * @author Alexander
 */
public class SubjectFacade extends Facade<SubjectVo> {

    protected SubjectService service = new SubjectService();

    public SubjectFacade(String PUName, SubjectService service) {
        super(PUName, service);
    }

    public boolean subscribeStudent(Integer userId, Integer subjectCode) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        boolean ret = false;
        try {
            tx = em.getTransaction();
            tx.begin();
            service.suscribeStudent(userId, subjectCode,em);
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
}
