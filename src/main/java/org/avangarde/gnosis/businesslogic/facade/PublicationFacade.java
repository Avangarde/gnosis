package org.avangarde.gnosis.businesslogic.facade;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.avangarde.gnosis.businesslogic.service.PublicationService;
import org.avangarde.gnosis.vo.PublicationVo;

/**
 *
 * @author Alexander
 */
public class PublicationFacade extends Facade <PublicationVo> {

    public PublicationFacade(String PUName, PublicationService service) {
        super(PUName, service);
    }
    
    public int getNewId() {
        try {
            return ((PublicationService)service).getNewId(em);
        } finally {
            if (em != null) {
                em.clear();
                em.close();
            }
        }
    }

    public List<String> getTopicsBySubject(Integer subjectCode) {
        try {
            em = emf.createEntityManager();
            return ((PublicationService)service).getTopicsBySubject(em, subjectCode);
        } finally {
            if (em != null) {
                em.clear();
                em.close();
            }
        }
    }

    public List<PublicationVo> getPublicationsByTopic(String topic) {
        try {
            em = emf.createEntityManager();
            return ((PublicationService)service).getPublicationsByTopic(topic, em);
        } finally {
            if (em != null) {
                em.clear();
                em.close();
            }
        }
    }

    public boolean isVotedByUser(int studentId, int pubId) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        boolean ret = true;
        try {
            tx = em.getTransaction();
            tx.begin();
            tx.commit();
            ret = ((PublicationService)service).isVotedByUser(studentId, pubId, em);
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
        
    public List<PublicationVo> getPublicationsByStudent(int studentId) {
        try {
            return ((PublicationService) service).getPublicationsByStudent(studentId, em);
        } finally {
            if (em != null) {
                em.clear();
                em.close();
            }
        }
    }
}
