package org.avangarde.gnosis.presentation.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.avangarde.gnosis.businesslogic.facade.ActivityFacade;
import org.avangarde.gnosis.businesslogic.facade.FacadeFactory;
import org.avangarde.gnosis.businesslogic.facade.RatingFacade;
import org.avangarde.gnosis.vo.ActivityVo;
import org.avangarde.gnosis.vo.CommentVo;
import org.avangarde.gnosis.vo.PublicationVo;
import org.avangarde.gnosis.vo.RatingVo;

/**
 *
 * @author Alexander
 */
@ManagedBean
@ViewScoped
public class ViewResourceBean implements Serializable{

    private int id;
    private String title;
    private String topic;
    private String type;
    private String url;
    private String sharedBy;
    private Double rating;
    private Integer ratingInt;
    private int vote;
    private int numVotes;
    private List<CommentVo> commentList = new ArrayList<CommentVo>();
    @ManagedProperty(value = "#{userBean}")
    private UserBean user;
    @ManagedProperty(value = "#{subjectBean}")
    private SubjectBean subject;

    public ViewResourceBean() {
    }
    
    public void preRenderView() {
        PublicationVo resource = FacadeFactory.getInstance().getPublicationFacade().find(getId());
        setTitle(resource.getTitle());
        setTopic(resource.getTopic());
        setType(resource.getType());
        setUrl(resource.getUrl());
        setSharedBy(resource.getStudentName());
        if (resource.getRating() != null) {
            setRatingInt(resource.getRating().intValue());
            setRating(resource.getRating());
        } else {
            setRatingInt(0);
            setRating(0.0);
        }
        setNumVotes(resource.getNumVotes());
    }
    
    public void rate(){
        RatingFacade ratingFacade = FacadeFactory.getInstance().getRatingFacade();
        
        RatingVo vo = new RatingVo();
        vo.setRating(getVote());
        vo.setStudentId(getUser().getId());
        vo.setPublicationId(getId());
        
        ratingFacade.create(vo);
        
        ActivityFacade activityFacade = FacadeFactory.getInstance().getActivityFacade();
        
        ActivityVo activityVo = new ActivityVo();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
        activityVo.setDateActivity(format.format(new GregorianCalendar().getTime()));
        activityVo.setStudentId(getUser().getId());
        activityVo.setSubjectCode(getSubject().getCode());
        activityVo.setPublicationId(getId());
        activityVo.setType("Rating");
        
        activityFacade.create(activityVo);
    }
    
    public boolean isVoted(){
        return FacadeFactory.getInstance().getPublicationFacade().isVotedByUser(getUser().getId(), getId());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSharedBy() {
        return sharedBy;
    }

    public void setSharedBy(String sharedBy) {
        this.sharedBy = sharedBy;
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
    
    public List<CommentVo> getCommentList() {
        if (commentList.isEmpty()) {
            loadComments();
        }
        return commentList;
    }

    public void setCommentList(List<CommentVo> commentList) {
        this.commentList = commentList;
    }
    
    public void loadComments() {
        commentList = new ArrayList<CommentVo>();
        commentList = FacadeFactory.getInstance().getCommentFacade().getCommentsbyPublication(getId());
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
    
    public Integer getRatingInt() {
        return ratingInt;
    }

    public void setRatingInt(Integer ratingInt) {
        this.ratingInt = ratingInt;
    }

    public int getNumVotes() {
        return numVotes;
    }

    public void setNumVotes(int numVotes) {
        this.numVotes = numVotes;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }
}
