package org.avangarde.gnosis.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import org.avangarde.gnosis.vo.EventVo;

/**
 *
 * @author Alexander
 */
@Entity
@Table(name = "event")
@NamedQueries({
    @NamedQuery(name = "Event.findAll", query = "SELECT e FROM Event e"),})

public class Event implements Serializable, IEntity<EventVo> {
    
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private String type;
    @Column(name = "dateEvent")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "hourEvent")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hour;
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "studentId")
    private Student student;
    @ManyToOne
    @JoinColumn(name = "Subject_code")
    private Subject subject;

    public Event() {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getHour() {
        return hour;
    }

    public void setHour(Date hour) {
        this.hour = hour;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
    
    @Override
    public EventVo toVo() {
        EventVo vo = new EventVo();
        vo.setDate(getDate());
        vo.setDescription(getDescription());
        vo.setHour(getHour());
        vo.setId(getId());
        vo.setName(getName());
        vo.setStudentId(getStudent().getId());
        vo.setSubjectCode(getSubject().getCode());
        vo.setType(getType());      
        return vo;     
    }
   
}
