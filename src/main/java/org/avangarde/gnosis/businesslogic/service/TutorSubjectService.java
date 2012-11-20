/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avangarde.gnosis.businesslogic.service;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.avangarde.gnosis.dao.DAOFactory;
import org.avangarde.gnosis.dao.TutorSubjectDAO;
import org.avangarde.gnosis.entity.Rating;
import org.avangarde.gnosis.entity.Subject;
import org.avangarde.gnosis.entity.Tutor;
import org.avangarde.gnosis.entity.TutorSubject;
import org.avangarde.gnosis.vo.TutorSubjectVo;
import org.avangarde.gnosis.vo.TutorVo;

/**
 *
 * @author Familia Martinez
 */
public class TutorSubjectService implements IService<TutorSubjectVo> {

    private static TutorSubjectService instance;

    public static synchronized TutorSubjectService getInstance() {
        if (instance == null) {
            instance = new TutorSubjectService();
        }
        return instance;
    }

    @Override
    public void create(TutorSubjectVo vo, EntityManager em) {
        TutorSubject entity = new TutorSubject();
        entity.setId(vo.getId());
        entity.setReputation(0);
        entity.setUrlPhoto(vo.getUrlPhoto());
        entity.setUserName(vo.getUserName());

        Subject subject = DAOFactory.getInstance().getSubjectDAO().find(vo.getSubjectCode(), em);
        subject.getTutorSubjectList().add(entity);
        entity.setSubject(subject);

        Tutor tutor = DAOFactory.getInstance().getTutorDAO().find(vo.getTutorId(), em);
        tutor.getTutorSubjectList().add(entity);
        entity.setTutor(tutor);

        DAOFactory.getInstance().getTutorSubjectDAO().persist(entity, em);
    }

    @Override
    public TutorSubjectVo find(Object id, EntityManager em) {
        TutorSubject tutorSubject = DAOFactory.getInstance().getTutorSubjectDAO().find(id, em);
        if (tutorSubject != null) {
            return tutorSubject.toVo();
        } else {
            return null;
        }
    }

    @Override
    public void update(TutorSubjectVo vo, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Object id, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<TutorSubjectVo> getList(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isTheTutorOnSubject(TutorVo tutor, Integer subjectCode, EntityManager em) {
        TutorSubjectDAO tutorSubjectDAO = DAOFactory.getInstance().getTutorSubjectDAO();
        TutorSubject tutorSubject = tutorSubjectDAO.findByUsernameAndCode(subjectCode, tutor.getUserName(), em);

        if (tutorSubject != null) {
            return true;
        } else {
            return false;
        }


    }

    public List<TutorSubjectVo> getTutorsByName(String query, EntityManager em) {
        List<TutorSubjectVo> tutors = new ArrayList<TutorSubjectVo>();
        List<TutorSubject> entities = DAOFactory.getInstance().getTutorSubjectDAO().getTutorsByname(query, em);
        for (TutorSubject tutor : entities) {
            tutors.add(tutor.toVo());
        }
        return tutors;




    }

    public TutorSubjectVo findTutorOnSubject(Integer tutorId, Integer subjectCode, EntityManager em) {
        TutorSubjectDAO tutorSubjectDAO = DAOFactory.getInstance().getTutorSubjectDAO();
        TutorSubject tutorSubject = tutorSubjectDAO.findByIdAndCode(tutorId, subjectCode, em);

        if (tutorSubject != null) {
            return tutorSubject.toVo();
        } else {
            return null;
        }
    }

    public boolean isVotedByUser(int studentId, int tutorSubjectId, EntityManager em) {
        TutorSubject tutorSubject = DAOFactory.getInstance().getTutorSubjectDAO().find(tutorSubjectId, em);

        for (Rating rating : tutorSubject.getRatingList()) {
            if (rating.getStudent().getId() == studentId) {
                return true;
            }
        }
        return false;
    }
}
