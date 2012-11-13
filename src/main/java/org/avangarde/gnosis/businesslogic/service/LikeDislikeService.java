/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avangarde.gnosis.businesslogic.service;

import java.util.List;
import javax.persistence.EntityManager;
import org.avangarde.gnosis.dao.DAOFactory;
import org.avangarde.gnosis.entity.Comment;
import org.avangarde.gnosis.entity.LikeDislike;
import org.avangarde.gnosis.entity.Student;
import org.avangarde.gnosis.vo.LikeDislikeVo;

/**
 *
 * @author Familia Martinez
 */
public class LikeDislikeService implements IService<LikeDislikeVo> {

    private static LikeDislikeService instance;
    
    private LikeDislikeService() {
    }

    public static synchronized LikeDislikeService getInstance() {
        if (instance == null) {
            instance = new LikeDislikeService();
        }
        return instance;
    }

    @Override
    public void create(LikeDislikeVo vo, EntityManager em) {
        LikeDislike entity = new LikeDislike();
        entity.setLike(vo.isLiked());
        entity.setDislike(vo.isDisliked());
        Comment comment = DAOFactory.getInstance().getCommentDAO().find(vo.getCommentId(), em);
        comment.getLikeDislikeList().add(entity);
        if (vo.isLiked()){
            comment.setLike(comment.getLike() + 1);
        } else if (vo.isDisliked()){
            comment.setDislike(comment.getDislike() + 1);
        }
        entity.setComment(comment);
        Student student = DAOFactory.getInstance().getStudentDAO().find(vo.getStudentId(), em);
        student.getLikeDislikeList().add(entity);
        entity.setStudent(student);
        
        DAOFactory.getInstance().getLikeDislikeDAO().persist(entity, em);
    }

    @Override
    public LikeDislikeVo find(Object id, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(LikeDislikeVo vo, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Object id, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<LikeDislikeVo> getList(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
