/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avangarde.gnosis.businesslogic.service;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.avangarde.gnosis.dao.DAOFactory;
import org.avangarde.gnosis.entity.Student;
import org.avangarde.gnosis.entity.Tutor;
import org.avangarde.gnosis.entity.TutorSubject;
import org.avangarde.gnosis.vo.TutorSubjectVo;
import org.avangarde.gnosis.vo.TutorVo;

/**
 *
 * @author Familia Martinez
 */
public class TutorService implements IService<TutorVo> {

    private static TutorService instance;

    private TutorService() {
    }

    public static synchronized TutorService getInstance() {
        if (instance == null) {
            instance = new TutorService();
        }
        return instance;
    }

    @Override
    public void create(TutorVo vo, EntityManager em) {
        Tutor entity = new Tutor();
        entity.setId(vo.getId());
        entity.setNumberStudents(vo.getNumberStudents());
        entity.setNumberVotes(vo.getNumberVotes());
        entity.setPublishedResources(vo.getPublishedResources());
        entity.setQuestionReceived(vo.getQuestionReceived());
        entity.setReputation(vo.getReputation());
        entity.setUserName(vo.getUserName());





        //codigo para obtener el student
        Student student = DAOFactory.getInstance().getStudentDAO().find(vo.getStudentId(), em);
        entity.setStudent(student);

        DAOFactory.getInstance().getTutorDAO().persist(entity, em);
    }

    @Override
    public TutorVo find(Object id, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(TutorVo vo, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Object id, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<TutorVo> getList(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public TutorVo findByUsername(TutorVo vo, EntityManager em) {
        Tutor entity = new Tutor();
        entity.setUserName(vo.getUserName());

        Tutor tutor = DAOFactory.getInstance().getTutorDAO().findByUsername(entity, em);
        if (tutor != null) {
            vo = tutor.toVo();
            return vo;
        } else {
            return null;
        }
    }
}
