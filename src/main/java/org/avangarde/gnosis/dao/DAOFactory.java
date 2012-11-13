package org.avangarde.gnosis.dao;

/**
 *
 * @author Alexander
 */
public class DAOFactory {

    private DAOFactory() {
    }

    public ActivityDAO getActivityDAO() {
        return ActivityDAO.getInstance();
    }

    public CommentDAO getCommentDAO() {
        return CommentDAO.getInstance();
    }

    public EventDAO getEventDAO() {
        return EventDAO.getInstance();
    }

    public LikeDislikeDAO getLikeDislikeDAO() {
        return LikeDislikeDAO.getInstance();
    }

    public ProgramDAO getProgramDAO() {
        return ProgramDAO.getInstance();
    }

    public PublicationDAO getPublicationDAO() {
        return PublicationDAO.getInstance();
    }

    public StudentDAO getStudentDAO() {
        return StudentDAO.getInstance();
    }

    public StudygroupDAO getStudygroupDAO() {
        return StudygroupDAO.getInstance();
    }

    public SubjectDAO getSubjectDAO() {
        return SubjectDAO.getInstance();
    }

    public TopicDAO getTopicDAO() {
        return TopicDAO.getInstance();
    }

    public TutorDAO getTutorDAO() {
        return TutorDAO.getInstance();
    }

    public TutorSubjectDAO getTutorSubjectDAO() {
        return TutorSubjectDAO.getInstance();
    }
    
    public RatingDAO getRatingDAO() {
        return RatingDAO.getInstance();
    }
    
    private static DAOFactory instance;

    public static synchronized DAOFactory getInstance() {
        if (instance == null) {
            instance = new DAOFactory();
        }
        return instance;
    }
}
