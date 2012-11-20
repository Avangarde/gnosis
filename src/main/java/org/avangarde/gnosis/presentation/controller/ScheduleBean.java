/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avangarde.gnosis.presentation.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.avangarde.gnosis.vo.EventVo;
import org.primefaces.event.DateSelectEvent;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.ScheduleEntrySelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author Alexander
 */
@ManagedBean
@ViewScoped
public class ScheduleBean implements Serializable {

    private ScheduleModel eventModel;
    private ScheduleEvent event = new DefaultScheduleEvent();
    @ManagedProperty(value = "#{userBean}")
    private UserBean user;
    @ManagedProperty(value = "#{subjectBean}")
    private SubjectBean subject;    
    private List<EventVo> events;

    public List<EventVo> getEvents() {
        return events;
    }

    public void setEvents(List<EventVo> events) {
        this.events = events;
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
    
    public ScheduleBean() {
        
       
        eventModel = new DefaultScheduleModel();
    }

    public ScheduleModel getEventModel() {
        if (subject.getEvents()!=null){
            loadEvents(subject.getEvents());
        }
        return eventModel;
    }

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }
    private void loadEvents(List<EventVo> eventsList) {
        for (EventVo evento : eventsList) {
            eventModel.addEvent(new  DefaultScheduleEvent(evento.getName(), evento.getStartDate(), evento.getEndDate()));
        }
    }
    
    public void addEvent() {
        if (event.getId() == null) {
            eventModel.addEvent(event);
            events=subject.getEvents();
                events.add(new EventVo(event.getId(),event.getStartDate(),event.getEndDate()));
                subject.setEvents(events);
        } else {
            eventModel.updateEvent(event);
            events=subject.getEvents();
            List<EventVo> eventList = null;
            for(EventVo eve: events){
                if(event.getId().equals(eve.getName())){
                    eve.setStartDate(event.getStartDate());
                    eve.setEndDate(event.getEndDate());
                    eventList.add(eve);
                } else{
                    eventList.add(eve);
                }
                events=eventList;
            }
                      
            
        }

        event = new DefaultScheduleEvent();
    }

    public void onEventSelect(ScheduleEntrySelectEvent selectEvent) {
        event = selectEvent.getScheduleEvent();
    }

    public void onDateSelect(DateSelectEvent selectEvent) {
        event = new DefaultScheduleEvent("", selectEvent.getDate(), selectEvent.getDate());
    }

    public void onEventMove(ScheduleEntryMoveEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

        addMessage(message);
    }

    public void onEventResize(ScheduleEntryResizeEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

        addMessage(message);
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }    

}
