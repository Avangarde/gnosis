/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avangarde.gnosis.presentation.controller;
  
 

import javax.faces.application.FacesMessage; 
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.*;

@ManagedBean
@RequestScoped

public class RatingBean {  
  

    private Integer rating1;  
  
    private Integer rating2;  
    
    private Integer rating3;  
      
    private Integer rating4 = 3; 
    @ManagedProperty(value = "#{userBean}")
    private UserBean user;
      
    public void onrate(RateEvent rateEvent) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Rate Event", "Has calificado" + ((Integer) rateEvent.getRating()).intValue());  
  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }  
      
    public void oncancel() {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cancel Event", "Rate Reset");  
  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }  
      
    public Integer getRating1() {  
        return rating1;  
    }  
  
    public void setRating1(Integer rating1) {  
        this.rating1 = rating1;  
    }  
  
    public Integer getRating2() {  
        return rating2;  
    }  
  
    public void setRating2(Integer rating2) {  
        this.rating2 = rating2;  
    }  
  
    public Integer getRating3() {  
        return rating3;  
    }  
  
    public void setRating3(Integer rating3) {  
        this.rating3 = rating3;  
    }  
  
    public Integer getRating4() {  
        return rating4;  
    }  
  
    public void setRating4(Integer rating4) {  
        this.rating4 = rating4;  
    }
    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }
}  
