/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avangarde.gnosis.businesslogic.service;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.avangarde.gnosis.dao.DAOFactory;
import org.avangarde.gnosis.entity.Publication;
import org.avangarde.gnosis.entity.Rating;
import org.avangarde.gnosis.entity.Student;
import org.avangarde.gnosis.entity.Subject;
import org.avangarde.gnosis.vo.PublicationVo;

/**
 *
 * @author Familia Martinez
 */
public class PublicationService implements IService<PublicationVo> {

    private static PublicationService instance;

    private PublicationService() {
    }

    public static synchronized PublicationService getInstance() {
        if (instance == null) {
            instance = new PublicationService();
        }
        return instance;
    }

    @Override
    public void create(PublicationVo vo, EntityManager em) {
        Publication entity = new Publication();
        entity.setId(DAOFactory.getInstance().getPublicationDAO().getNewId(em));
        entity.setDate(vo.getDate());
        entity.setTitle(vo.getTitle());
        entity.setTopic(vo.getTopic());
        entity.setType(vo.getType());

        String newURL = vo.getUrl();
        if (newURL.startsWith("https://docs")) {
            int edit = newURL.lastIndexOf("/");
            newURL = (newURL.substring(0, edit) + "/preview");
        } else if (newURL.startsWith("http://www.youtube")) {
            int edit = newURL.lastIndexOf("/");
            newURL = (newURL.substring(0, edit) + "/embed/" + newURL.substring(edit + 9));
        }
        entity.setUrl(newURL);

        Student student = DAOFactory.getInstance().getStudentDAO().find(vo.getStudentId(), em);
        student.getPublicationList().add(entity);
        entity.setStudent(student);

        Subject subject = DAOFactory.getInstance().getSubjectDAO().find(vo.getSubjectCode(), em);
        subject.getPublicationList().add(entity);
        entity.setSubject(subject);

        DAOFactory.getInstance().getPublicationDAO().persist(entity, em);
    }

    @Override
    public PublicationVo find(Object id, EntityManager em) {
        Publication publication = DAOFactory.getInstance().getPublicationDAO().find(id, em);
        if (publication != null) {
            return publication.toVo();
        } else {
            return null;
        }
    }

    @Override
    public void update(PublicationVo vo, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Object id, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<PublicationVo> getList(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<String> getTopicsBySubject(EntityManager em, Integer subjectCode) {

        List<String> list = DAOFactory.getInstance().getPublicationDAO().getTopicsBySubject(em, subjectCode);

        return list;
    }

    public List<PublicationVo> getPublicationsByTopic(String topic, EntityManager em) {
        List<PublicationVo> list = new ArrayList<PublicationVo>();
        for (Publication publication : DAOFactory.getInstance().getPublicationDAO().getPublicationsByTopic(topic, em)) {
            list.add((publication).toVo());
        }
        return list;
    }

    public boolean isVotedByUser(int studentId, int pubId, EntityManager em) {
        Publication publication = DAOFactory.getInstance().getPublicationDAO().find(pubId, em);

        for (Rating rating : publication.getRatingList()) {
            if (rating.getStudent().getId() == studentId) {
                return true;
            }
        }
        return false;
    }

    public int getNewId(EntityManager em) {
        return DAOFactory.getInstance().getPublicationDAO().getNewId(em);
    }

    public List<PublicationVo> getPublicationsByStudent(int studentId, EntityManager em) {
        List<PublicationVo> list = new ArrayList<PublicationVo>();
        for (Publication publication : DAOFactory.getInstance().getPublicationDAO().getPublicationsByStudent(studentId, em)) {
            list.add((publication).toVo());
        }
        return list;
    }

    public List<PublicationVo> getPublicationsByStudentAndTutorSubject(int studentId, int subjectCode, EntityManager em) {
        List<PublicationVo> list = new ArrayList<PublicationVo>();
        for (Publication publication : DAOFactory.getInstance().getPublicationDAO().getPublicationsByStudentAndTutorSubject(studentId, subjectCode, em)) {
            list.add((publication).toVo());
        }
        return list;
    }
}
