package org.avangarde.gnosis.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import org.avangarde.gnosis.vo.ActivityVo;
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
    @Column(name = "startDateEvent")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "endDateEvent")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Column(name = "hourEvent")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hour;
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "studentId")
    private Student student;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
    private List<Activity> activityList;
    @ManyToOne
    @JoinColumn(name = "Subject_code")
    private Subject subject;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Event() {
    }

    public List<Activity> getActivityList() {
        return activityList;
    }

    public void setActivityList(List<Activity> activityList) {
        this.activityList = activityList;
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
        ArrayList<ActivityVo> listActivityVo = new ArrayList<ActivityVo>();
        for (Activity entity : getActivityList()) {
            listActivityVo.add(entity.toVo());
        }
        EventVo vo = new EventVo();
        vo.setStartDate(getStartDate());
        vo.setEndDate(getEndDate());
        vo.setDescription(getDescription());
        vo.setHour(getHour());
        vo.setId(getId());
        vo.setName(getName());
        vo.setStudentId(getStudent().getId());
        vo.setSubjectCode(getSubject().getCode());
        vo.setType(getType());
        vo.setActivityList(listActivityVo);
        return vo;
    }
}
