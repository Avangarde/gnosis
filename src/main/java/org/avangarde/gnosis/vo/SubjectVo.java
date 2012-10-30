/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avangarde.gnosis.vo;

import java.util.List;

/**
 *
 * @author Zergio
 */
public class SubjectVo implements IValueObject {
    
    private Integer code;
    private String name;
    private String description;
    private int numGroups;
    
    private List<StudentVo> studentList;
//    private List<ProgramVo> programList;
    private List<TopicVo> topicList;
    private List<EventVo> eventList;
    private List<StudygroupVo> studygroupList;
    private List<ActivityVo> activityList;
    private List<TutorSubjectVo> tutorSubjectList;
    private List<PublicationVo> publicationList;

    public List<ActivityVo> getActivityList() {
        return activityList;
    }

    public void setActivityList(List<ActivityVo> activityList) {
        this.activityList = activityList;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<EventVo> getEventList() {
        return eventList;
    }

    public void setEventList(List<EventVo> eventList) {
        this.eventList = eventList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumGroups() {
        return numGroups;
    }

    public void setNumGroups(int numGroups) {
        this.numGroups = numGroups;
    }

//    public List<ProgramVo> getProgramList() {
//        return programList;
//    }
//
//    public void setProgramList(List<ProgramVo> programList) {
//        this.programList = programList;
//    }

    public List<PublicationVo> getPublicationList() {
        return publicationList;
    }

    public void setPublicationList(List<PublicationVo> publicationList) {
        this.publicationList = publicationList;
    }

    public List<StudentVo> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<StudentVo> studentList) {
        this.studentList = studentList;
    }

    public List<StudygroupVo> getStudygroupList() {
        return studygroupList;
    }

    public void setStudygroupList(List<StudygroupVo> studygroupList) {
        this.studygroupList = studygroupList;
    }

    public List<TopicVo> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<TopicVo> topicList) {
        this.topicList = topicList;
    }

    public List<TutorSubjectVo> getTutorSubjectList() {
        return tutorSubjectList;
    }

    public void setTutorSubjectList(List<TutorSubjectVo> tutorSubjectList) {
        this.tutorSubjectList = tutorSubjectList;
    }
    
    
    
}
