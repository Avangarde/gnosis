/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avangarde.gnosis.presentation.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.avangarde.gnosis.businesslogic.facade.*;
import org.avangarde.gnosis.vo.CommentVo;
import org.avangarde.gnosis.vo.TopicVo;

/**
 *
 * @author Alexander
 */
@ManagedBean
@ViewScoped
public class TopicBean implements Serializable{
    
    private int id;
    private Date dateStarted;
    private String title;
    private String firstComment;
    private List<CommentVo> commentList = new ArrayList<CommentVo>();
    private List<TopicVo> topics = new ArrayList<TopicVo>();
    @ManagedProperty(value = "#{userBean}")
    private UserBean user;
    @ManagedProperty(value = "#{subjectBean}")
    private SubjectBean subject;
    
    public TopicBean() {
    }
    
    public void preRenderView() {
        
    }
    
    public void createTopic(){
        
        TopicFacade topicFacade = FacadeFactory.getInstance().getTopicFacade();
        
        TopicVo topicVo = new TopicVo();
        
        topicVo.setId(FacadeFactory.getInstance().getTopicFacade().getNewId());
        topicVo.setTitle(getTitle());
        topicVo.setDateStarted(new GregorianCalendar().getTime());
        topicVo.setStudentId(getUser().getId());
        topicVo.setSubjectCode(getSubject().getCode());
                
        topicFacade.create(topicVo);
        
        CommentFacade commentFacade = FacadeFactory.getInstance().getCommentFacade();
        
        CommentVo commentVo = new CommentVo();
        commentVo.setContent(getFirstComment());
        SimpleDateFormat format = new SimpleDateFormat ("dd/MM/yyyy - HH:mm:ss");
        commentVo.setDate(format.format(new GregorianCalendar().getTime()));
        commentVo.setStudentId(getUser().getId());
        commentVo.setTopicId(topicVo.getId());
        
        commentFacade.create(commentVo);
        
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstComment() {
        return firstComment;
    }

    public void setFirstComment(String firstComment) {
        this.firstComment = firstComment;
    }

    public List<TopicVo> getTopics() {
        if (topics.isEmpty()) {
            loadTopics();
        }
        return topics;
    }

    public void setTopics(List<TopicVo> topics) {
        this.topics = topics;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateStarted() {
        return dateStarted;
    }

    public void setDateStarted(Date dateStarted) {
        this.dateStarted = dateStarted;
    }

    public List<CommentVo> getCommentList() {
        if (commentList.isEmpty()) {
            loadComments();
        }
        return commentList;
    }

    public void setCommentList(List<CommentVo> commentList) {
        this.commentList = commentList;
    }

    private void loadTopics() {
        topics = new ArrayList<TopicVo>();
        topics = FacadeFactory.getInstance().getTopicFacade().getTopicsbySubject(getSubject().getCode());
    }

    private void loadComments() {
        commentList = new ArrayList<CommentVo>();
        commentList = FacadeFactory.getInstance().getCommentFacade().getCommentsbyTopic(getId());
    }
        
}
