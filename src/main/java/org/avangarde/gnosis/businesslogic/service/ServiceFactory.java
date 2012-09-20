package org.avangarde.gnosis.businesslogic.service;

/**
 *
 * @author Alexander
 */
public class ServiceFactory {

    private ServiceFactory() {
    }

    public ActivityService getActivityService() {
        return ActivityService.getInstance();
    }
    
    public CommentService getCommentService() {
        return CommentService.getInstance();
    }
    
    public EventService getEventService() {
        return EventService.getInstance();
    }
    
    public ProgramService getProgramService() {
        return ProgramService.getInstance();
    }
    
    public PublicationService getPublicationService() {
        return PublicationService.getInstance();
    }
    
    public StudentService getStudentService() {
        return StudentService.getInstance();
    }
    
    public StudygroupService getStudygroupService() {
        return StudygroupService.getInstance();
    }
    
    public SubjectService getSubjectService() {
        return SubjectService.getInstance();
    }
    
    public TopicService getTopicService() {
        return TopicService.getInstance();
    }
    
    public TutorService getTutorService() {
        return TutorService.getInstance();
    }
    
    public TutorSubjectService getTutorSubjectService() {
        return TutorSubjectService.getInstance();
    }
    
    private static ServiceFactory instance;

    public static synchronized ServiceFactory getInstance() {
        if (instance == null) {
            instance = new ServiceFactory();
        }
        return instance;
    }
}
