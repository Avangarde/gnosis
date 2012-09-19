package org.avangarde.gnosis.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.avangarde.gnosis.vo.ProgramVo;
import org.avangarde.gnosis.vo.StudentVo;
import org.avangarde.gnosis.vo.SubjectVo;

/**
 *
 * @author Alexander
 */
@Entity
@Table(name = "program")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Program.findAll", query = "SELECT p FROM Program p"),
    @NamedQuery(name = "Program.findByCode", query = "SELECT p FROM Program p WHERE p.code = :code"),
    @NamedQuery(name = "Program.findByName", query = "SELECT p FROM Program p WHERE p.name = :name")})

public class Program implements Serializable, IEntity<ProgramVo> {
    
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "code")
    private int code;
    @Column(name = "name")
    private String name;
    @JoinTable(name = "program_subject", joinColumns = {
        @JoinColumn(name = "Program_code", referencedColumnName = "code")}, inverseJoinColumns = {
        @JoinColumn(name = "Subject_code", referencedColumnName = "code")})
    @ManyToMany
    private List<Subject> subjectList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "program")
    private List<Student> studentList;

    public Program() {
    }

    public Program(Integer code) {
        this.code = code;
    }

    public Program(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public List<Subject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<Subject> subjectList) {
        this.subjectList = subjectList;
    }

    @XmlTransient
    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    @Override
    public ProgramVo toVo() {
        ProgramVo vo =new ProgramVo();
        vo.setCode(getCode());
        vo.setName(getName());
        //<editor-fold defaultstate="collapsed" desc="vo.setStudentList">
        List<StudentVo> studentListVo = new ArrayList<StudentVo>();
        for (Student entity : getStudentList()){
            studentListVo.add(entity.toVo());
        }
        vo.setStudentList(studentListVo);
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="vo.setSubjectList">
        List<SubjectVo> subjectListVo = new ArrayList<SubjectVo>();
        for (Subject entity : getSubjectList()){
            subjectListVo.add(entity.toVo());
        }
        vo.setSubjectList(subjectListVo);
        //</editor-fold>
        return vo;
    }
    
}
