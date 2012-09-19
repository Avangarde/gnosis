package org.avangarde.gnosis.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.avangarde.gnosis.vo.StudentVo;
import org.avangarde.gnosis.vo.StudygroupVo;

/**
 *
 * @author Alexander
 */
@Entity
@Table(name = "studygroup")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Studygroup.findAll", query = "SELECT s FROM Studygroup s"),})

public class Studygroup implements Serializable, IEntity<StudygroupVo> {
    
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @ManyToMany(mappedBy = "studygroupList")
    private List<Student> studentList;
    @JoinColumn(name = "Subject_code", referencedColumnName = "code", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Subject subject;

    public Studygroup() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public StudygroupVo toVo() {
        StudygroupVo vo = new StudygroupVo();
        
        vo.setId(getId());
        vo.setName(getName());
        vo.setSubjectCode(getSubject().getCode());
        
        ArrayList<StudentVo> listStudentVo = new ArrayList<StudentVo>();
        for (Student entity : getStudentList()) {
             listStudentVo.add(entity.toVo());
        }
        
        vo.setStudentList(listStudentVo);
        
        return vo;
    }
   
}
