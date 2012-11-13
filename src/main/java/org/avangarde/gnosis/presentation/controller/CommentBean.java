package org.avangarde.gnosis.presentation.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.avangarde.gnosis.businesslogic.facade.CommentFacade;
import org.avangarde.gnosis.businesslogic.facade.FacadeFactory;
import org.avangarde.gnosis.businesslogic.facade.LikeDislikeFacade;
import org.avangarde.gnosis.vo.CommentVo;
import org.avangarde.gnosis.vo.LikeDislikeVo;

/**
 *
 * @author Alexander
 */
@ManagedBean
@ViewScoped
public class CommentBean implements Serializable {

    private int id;
    private String content;
    private String date;
    @ManagedProperty(value = "#{userBean}")
    private UserBean user;
    @ManagedProperty(value = "#{subjectBean}")
    private SubjectBean subject;
    @ManagedProperty(value = "#{topicBean}")
    private TopicBean topic;
    @ManagedProperty(value = "#{viewResourceBean}")
    private ViewResourceBean viewResourceBean;

    public CommentBean() {
    }

    public void createComment() {
        CommentFacade commentFacade = FacadeFactory.getInstance().getCommentFacade();

        CommentVo commentVo = new CommentVo();
        commentVo.setContent(getContent());
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
        commentVo.setDate(format.format(new GregorianCalendar().getTime()));
        commentVo.setStudentId(getUser().getId());
        commentVo.setTopicId(getTopic().getId());
        commentVo.setPublicationId(getViewResourceBean().getId());

        commentFacade.create(commentVo);

        getTopic().loadComments();
        getViewResourceBean().loadComments();
    }

    public void like(int commentId) {
 
        LikeDislikeFacade facade = FacadeFactory.getInstance().getLikeDislikeFacade();
        LikeDislikeVo vo = new LikeDislikeVo();
        vo.setCommentId(commentId);
        vo.setStudentId(getUser().getId());
        vo.setLiked(true);
        vo.setDisliked(false);
        
        facade.create(vo);

    }

    public void dislike(int commentId) {

        LikeDislikeFacade facade = FacadeFactory.getInstance().getLikeDislikeFacade();
        LikeDislikeVo vo = new LikeDislikeVo();
        vo.setCommentId(commentId);
        vo.setStudentId(getUser().getId());
        vo.setLiked(false);
        vo.setDisliked(true);
        
        facade.create(vo);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public SubjectBean getSubject() {
        return subject;
    }

    public void setSubject(SubjectBean subject) {
        this.subject = subject;
    }

    public TopicBean getTopic() {
        return topic;
    }

    public void setTopic(TopicBean topic) {
        this.topic = topic;
    }

    public ViewResourceBean getViewResourceBean() {
        return viewResourceBean;
    }

    public void setViewResourceBean(ViewResourceBean viewResourceBean) {
        this.viewResourceBean = viewResourceBean;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
