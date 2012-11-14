package org.avangarde.gnosis.businesslogic.facade;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
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
            service.suscribeStudent(userId, subjectCode, em);
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

    public boolean unSubscribeStudent(Integer userId, Integer subjectCode) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        boolean ret = false;
        try {
            tx = em.getTransaction();
            tx.begin();
            service.unSuscribeStudent(userId, subjectCode, em);
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

    public boolean isTheStudentSubscribed(Integer userId, Integer subjectCode) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        boolean ret = true;
        try {
            tx = em.getTransaction();
            tx.begin();
            tx.commit();
            ret = service.isTheStudentSubscribed(userId, subjectCode, em);
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

    public List<SubjectVo> getSubjectsByName(String query) {
        try {
            return ((SubjectService) service).getSubjectsByName(query, em);
        } finally {
            if (em != null) {
                em.clear();
                em.close();
            }
        }
    }

    public boolean isTheStudentSubscribedToTutor(Integer studentId, String tutorUserName, Integer subjectCode) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        boolean ret = true;
        try {
            tx = em.getTransaction();
            tx.begin();
            tx.commit();
            ret = service.isTheStudentSubscribedToTutor(studentId, tutorUserName, subjectCode, em);
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

    public boolean subscribeStudentToTutor(Integer userId, String tutorUserName, Integer subjectCode) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        boolean ret = false;
        try {
            tx = em.getTransaction();
            tx.begin();
            service.suscribeStudentToTutor(userId, tutorUserName, subjectCode, em);
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
    
    public boolean unSubscribeStudentToTutor(Integer userId, String tutorUserName, Integer subjectCode) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        boolean ret = false;
        try {
            tx = em.getTransaction();
            tx.begin();
            service.unSuscribeStudentToTutor(userId, tutorUserName, subjectCode, em);
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
