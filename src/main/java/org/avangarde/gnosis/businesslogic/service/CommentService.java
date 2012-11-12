/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avangarde.gnosis.businesslogic.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import org.avangarde.gnosis.dao.DAOFactory;
import org.avangarde.gnosis.entity.Comment;
import org.avangarde.gnosis.entity.Student;
import org.avangarde.gnosis.entity.Topic;
import org.avangarde.gnosis.vo.CommentVo;

/**
 *
 * @author Familia Martinez
 */
public class CommentService implements IService<CommentVo> {

    private static CommentService instance;
    
    private CommentService() {
    }

    public static synchronized CommentService getInstance() {
        if (instance == null) {
            instance = new CommentService();
        }
        return instance;
    }

    @Override
    public void create(CommentVo vo, EntityManager em) {
        try {
            Comment entity =  new Comment();
            entity.setContent(vo.getContent());
            SimpleDateFormat format = new SimpleDateFormat ("dd/MM/yyyy - HH:mm:ss");
            entity.setDate(format.parse(vo.getDate()));
            if (vo.getStudentId() != 0) {
                Student student = DAOFactory.getInstance().getStudentDAO().find(vo.getStudentId(), em);
                entity.setStudent(student);
                student.getCommentList().add(entity);
            }
            if (vo.getTopicId() != 0) {
                Topic topic = DAOFactory.getInstance().getTopicDAO().find(vo.getTopicId(), em);
                entity.setTopic(topic);
                topic.getCommentList().add(entity);
            }
            DAOFactory.getInstance().getCommentDAO().persist(entity, em);
        } catch (ParseException ex) {
            Logger.getLogger(CommentService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public CommentVo find(Object id, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(CommentVo vo, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Object id, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<CommentVo> getList(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<CommentVo> getCommentsByTopic(EntityManager em, Integer topicId) {
        List<CommentVo> commentVo = new ArrayList<CommentVo>();
        List<Comment> comments = DAOFactory.getInstance().getCommentDAO().getCommentsByTopic(em, topicId);
        for (Comment entity : comments ){
            commentVo.add(entity.toVo());
        }    
        return commentVo;
    }
}
