/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avangarde.gnosis.presentation.controller;

import java.awt.event.ActionEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.DateSelectEvent;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.ScheduleEntrySelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author Alexander
 */
@ManagedBean
@RequestScoped
public class ScheduleBean {
    
    private Date date1 = new Date();
    private Date date2 = new Date();
    
    private ScheduleModel eventModel;  
    private ScheduleModel lazyEventModel;  
    private ScheduleEvent event = new DefaultScheduleEvent();    
    private String theme;  
  
    public ScheduleBean() {
        
        eventModel = new DefaultScheduleModel();
        
        Calendar c1 = GregorianCalendar.getInstance();
        c1.set(2012, Calendar.NOVEMBER, 25);
        date2 = c1.getTime();
            
        eventModel.addEvent(new DefaultScheduleEvent("Parcial II", date1, date2));

        lazyEventModel = new LazyScheduleModel() {  
            public void fetchEvents(Date start, Date end) {  
                clear();  
                  
                Date random = getRandomDate(start);  
                addEvent(new DefaultScheduleEvent("Lazy Event 1", random, random));  
                  
                random = getRandomDate(start);  
                addEvent(new DefaultScheduleEvent("Lazy Event 2", random, random));  
            } 
            private Date getRandomDate(Date start) {
                throw new UnsupportedOperationException("Not yet implemented");
            }
        };  
    }  
      
    public void addEvent(ActionEvent actionEvent) {  
        if(event.getId() == null) {
            eventModel.addEvent(event);
        }  
        else {
            eventModel.updateEvent(event);
        }  
          
        event = new DefaultScheduleEvent();  
    }  
      
    public void onEventSelect(ScheduleEntrySelectEvent selectEvent) {  
        event = selectEvent.getScheduleEvent();  
    }  
      
    public void onDateSelect(DateSelectEvent selectEvent) {  
        event = new DefaultScheduleEvent(Math.random() + "", selectEvent.getDate(), selectEvent.getDate());  
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
      
    //Getters and Setters

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public void setEventModel(ScheduleModel eventModel) {
        this.eventModel = eventModel;
    }

    public ScheduleModel getLazyEventModel() {
        return lazyEventModel;
    }

    public void setLazyEventModel(ScheduleModel lazyEventModel) {
        this.lazyEventModel = lazyEventModel;
    }

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
    
}
