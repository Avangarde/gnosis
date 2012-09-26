/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avangarde.gnosis.presentation.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.avangarde.gnosis.businesslogic.facade.FacadeFactory;
import org.avangarde.gnosis.businesslogic.facade.StudentFacade;
import org.avangarde.gnosis.vo.StudentVo;

/**
 *
 * @author Alexander
 */
@ManagedBean
@RequestScoped
public class LoginBean {

    private String userName;
    private String password;

    public LoginBean() {
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

    public String logIn() {
        StudentVo studentVo = new StudentVo();
        StudentFacade studentFacade = FacadeFactory.getInstance().getStudentFacade();

        studentVo.setUserName(getUserName());
        studentVo.setPassword(getPassword());

        if (studentVo.getUserName().equals("")
                || studentVo.getPassword().equals("")) {
            return "failure";
        } else {
            StudentVo login = studentFacade.login(studentVo);
            return login != null ? "success" : "failure";
        }
    }
}
