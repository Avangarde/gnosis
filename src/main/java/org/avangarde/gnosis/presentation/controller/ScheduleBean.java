/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avangarde.gnosis.presentation.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.avangarde.gnosis.businesslogic.facade.ActivityFacade;
import org.avangarde.gnosis.businesslogic.facade.EventFacade;
import org.avangarde.gnosis.businesslogic.facade.FacadeFactory;
import org.avangarde.gnosis.vo.ActivityVo;
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
        if (subject.getEvents() != null) {
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
        eventModel.clear();
        for (EventVo evento : eventsList) {
            eventModel.addEvent(new DefaultScheduleEvent(evento.getName(), evento.getStartDate(), evento.getEndDate()));
            System.out.println("cargo los n eventos" + evento.getName());
        }
    }

    public void addEvent() {
        System.out.println("Entra a addEvent");
        System.out.println(event.getId());
        if (event.getId() == null) {
            System.out.println("El id es nulo");
            eventModel.addEvent(event);
            events = subject.getEvents();

            events.add(new EventVo(event.getId(), event.getStartDate(), event.getEndDate()));
            System.out.println("events = " + events);

            EventFacade eventFacade = FacadeFactory.getInstance().getEventFacade();

            EventVo eventVo = new EventVo();
            eventVo.setName(event.getTitle());
            eventVo.setStartDate(event.getStartDate());
            eventVo.setEndDate(event.getEndDate());
            eventVo.setStudentId(user.getId());
            eventVo.setSubjectCode(subject.getCode());

            eventFacade.create(eventVo);

            ActivityFacade activityFacade = FacadeFactory.getInstance().getActivityFacade();
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
            ActivityVo activityVo = new ActivityVo();
            activityVo.setDateActivity(format.format(new GregorianCalendar().getTime()));
            activityVo.setStudentId(user.getId());
            activityVo.setSubjectCode(subject.getCode());
            activityVo.setEventId(subject.getEvents().size());
            activityVo.setType("NewEvent");

            activityFacade.create(activityVo);
            subject.setEvents(events);

        } else {
            System.out.println("El id no es nulo");
            eventModel.updateEvent(event);
            events = subject.getEvents();
            int aux = 0;
            for (EventVo eve : events) {
                if (event.getTitle().equals(eve.getName())) {
                    eve.setStartDate(event.getStartDate());
                    eve.setEndDate(event.getEndDate());
                    events.set(aux, eve);
                    EventFacade eventFacade = FacadeFactory.getInstance().getEventFacade();

                    EventVo eventVo = new EventVo();
                    eventVo.setName(event.getTitle());
                    eventVo.setStartDate(event.getStartDate());
                    eventVo.setEndDate(event.getEndDate());
                    eventVo.setStudentId(user.getId());
                    eventVo.setSubjectCode(subject.getCode());

                    eventFacade.update(eventVo);

                    ActivityFacade activityFacade = FacadeFactory.getInstance().getActivityFacade();
                    ActivityVo activityVo = new ActivityVo();
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
                    activityVo.setDateActivity(format.format(new GregorianCalendar().getTime()));
                    activityVo.setStudentId(user.getId());
                    activityVo.setSubjectCode(subject.getCode());
                    activityVo.setEventId(eve.getId());
                    activityVo.setType("UpdatedEvent");

                    activityFacade.create(activityVo);
                    aux++;
                } else {
                    System.out.println("rarooo");
                }

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
