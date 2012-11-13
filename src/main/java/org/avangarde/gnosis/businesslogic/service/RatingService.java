/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avangarde.gnosis.businesslogic.service;

import java.util.List;
import javax.persistence.EntityManager;
import org.avangarde.gnosis.dao.DAOFactory;
import org.avangarde.gnosis.entity.Publication;
import org.avangarde.gnosis.entity.Rating;
import org.avangarde.gnosis.entity.Student;
import org.avangarde.gnosis.vo.RatingVo;

/**
 *
 * @author Alexander
 */
public class RatingService implements IService<RatingVo> {
    
    private static RatingService instance;
    
    private RatingService() {
    }

    public static synchronized RatingService getInstance() {
        if (instance == null) {
            instance = new RatingService();
        }
        return instance;
    }
    
    @Override
    public void create(RatingVo vo, EntityManager em) {
        Rating entity = new Rating();
        entity.setRating(vo.getRating());
        Student student = DAOFactory.getInstance().getStudentDAO().find(vo.getStudentId(), em);
        student.getRatingList().add(entity);
        entity.setStudent(student);
        Publication publication = DAOFactory.getInstance().getPublicationDAO().find(vo.getPublicationId(), em);
        publication.getRatingList().add(entity);
        publication.setRating(calculateRating(publication.getRatingList()));
        entity.setPublication(publication);
        
        DAOFactory.getInstance().getRatingDAO().persist(entity, em);
    }

    @Override
    public RatingVo find(Object id, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(RatingVo vo, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Object id, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<RatingVo> getList(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private Double calculateRating(List<Rating> ratingList) {
        Double rating = new Double(0);
        for (Rating entity : ratingList){
            rating += entity.getRating();
        }
        rating = rating/ratingList.size();
        return rating;
    }
    
}
