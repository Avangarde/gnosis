package org.avangarde.gnosis.businesslogic.facade;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.avangarde.gnosis.businesslogic.service.SubjectService;
import org.avangarde.gnosis.businesslogic.service.TutorSubjectService;
import org.avangarde.gnosis.vo.SubjectVo;
import org.avangarde.gnosis.vo.TutorSubjectVo;
import org.avangarde.gnosis.vo.TutorVo;

/**
 *
 * @author Alexander
 */
public class TutorSubjectFacade extends Facade <TutorSubjectVo> {

    /**
     *
     */
    protected TutorSubjectService service = new TutorSubjectService();
    
    public TutorSubjectFacade(String PUName, TutorSubjectService service) {
        super(PUName, service);
    }    
    
    public boolean isTheTutorOnSubject(TutorVo tutor, Integer subjectCode) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        boolean ret = true;
        try {
            tx = em.getTransaction();
            tx.begin();
            tx.commit();
            ret = service.isTheTutorOnSubject(tutor, subjectCode, em);
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
