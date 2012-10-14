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
import org.avangarde.gnosis.businesslogic.facade.FacadeFactory;
import org.avangarde.gnosis.businesslogic.facade.StudentFacade;
import org.avangarde.gnosis.vo.StudentVo;

/**
 *
 * @author Alexander
 */
@ManagedBean
@RequestScoped
public class SignUpBean {

    private String userName;
    private String password;
    private String passwordR;
    private String firstName;
    private String lastName;
    private String email;
    private Integer programId;
    @ManagedProperty(value = "#{userBean}")
    private UserBean user;

    public SignUpBean() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordR() {
        return passwordR;
    }

    public void setPasswordR(String passwordR) {
        this.passwordR = passwordR;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getProgramId() {
        return programId;
    }

    public void setProgramId(Integer programId) {
        this.programId = programId;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public String signUp() {

        StudentFacade facade = FacadeFactory.getInstance().getStudentFacade();

        StudentVo vo = new StudentVo();
        vo.setFirstName(getFirstName());
        vo.setLastName(getLastName());
        vo.setUserName(getUserName());
        vo.setEmail(getUserName() + "@unal.edu.co");
        vo.setPassword(getPassword());
        vo.setProgramId(getProgramId());

        facade.create(vo);
         
        StudentVo studentVo = new StudentVo();
        StudentFacade studentFacade = FacadeFactory.getInstance().getStudentFacade();

        studentVo.setUserName(getUserName());
        studentVo.setPassword(getPassword());

        StudentVo login = studentFacade.login(studentVo);
        if (login != null) {
            user.setId(login.getId());
            user.setFirstName(login.getFirstName());
            user.setLastName(login.getLastName());
            user.setUserName(login.getUserName());
            user.setProgramId(login.getProgramId());
            user.setLoggedIn(true);
            return "success";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    "loginForm:userName", new FacesMessage(
                    "Nombre de usuario o contraseña inválidos"));
            FacesContext.getCurrentInstance().addMessage(
                    "loginForm:password", new FacesMessage(
                    "Nombre de usuario o contraseña inválidos"));
            return "failure";
        }
    }
}
