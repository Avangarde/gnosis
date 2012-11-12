package org.avangarde.gnosis.businesslogic.facade;

import org.avangarde.gnosis.businesslogic.service.ServiceFactory;

/**
 *
 * @author Alexander
 */
public class FacadeFactory {

    private String PU = "GnosisPU";

    private FacadeFactory() {
    }

    public ActivityFacade getActivityFacade() {
        return new ActivityFacade(PU, ServiceFactory.getInstance().getActivityService());
    }

    public CommentFacade getCommentFacade() {
        return new CommentFacade(PU, ServiceFactory.getInstance().getCommentService());
    }

    public EventFacade getEventFacade() {
        return new EventFacade(PU, ServiceFactory.getInstance().getEventService());
    }

    public LikeDislikeFacade getLikeDislikeFacade() {
        return new LikeDislikeFacade(PU, ServiceFactory.getInstance().getLikeDislikeService());
    }

    public ProgramFacade getProgramFacade() {
        return new ProgramFacade(PU, ServiceFactory.getInstance().getProgramService());
    }

    public PublicationFacade getPublicationFacade() {
        return new PublicationFacade(PU, ServiceFactory.getInstance().getPublicationService());
    }

    public StudentFacade getStudentFacade() {
        return new StudentFacade(PU, ServiceFactory.getInstance().getStudentService());
    }

    public StudygroupFacade getStudygroupFacade() {
        return new StudygroupFacade(PU, ServiceFactory.getInstance().getStudygroupService());
    }

    public SubjectFacade getSubjectFacade() {
        return new SubjectFacade(PU, ServiceFactory.getInstance().getSubjectService());
    }

    public TopicFacade getTopicFacade() {
        return new TopicFacade(PU, ServiceFactory.getInstance().getTopicService());
    }

    public TutorFacade getTutorFacade() {
        return new TutorFacade(PU, ServiceFactory.getInstance().getTutorService());
    }

    public TutorSubjectFacade getTutorSubjectFacade() {
        return new TutorSubjectFacade(PU, ServiceFactory.getInstance().getTutorSubjectService());
    }
    
    public RatingFacade getRatingFacade() {
        return new RatingFacade(PU, ServiceFactory.getInstance().getRatingService());
    }
    
    private static FacadeFactory instance;

    public static synchronized FacadeFactory getInstance() {
        if (instance == null) {
            instance = new FacadeFactory();
        }
        return instance;
    }
}
