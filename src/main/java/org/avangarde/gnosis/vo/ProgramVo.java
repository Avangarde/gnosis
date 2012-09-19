/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avangarde.gnosis.vo;

import java.util.List;

/**
 *
 * @author juanmanuelmartinezromero
 */
public class ProgramVo {

    private int code;
    private String name;
    private List<SubjectVo> subjectList;
    private List<StudentVo> studentList;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SubjectVo> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<SubjectVo> subjectList) {
        this.subjectList = subjectList;
    }

    public List<StudentVo> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<StudentVo> studentList) {
        this.studentList = studentList;
    }
    
    
}
