package org.avangarde.gnosis.vo;

import java.util.Date;

/**
 *
 * @author Zergio
 */
public class EventVo implements IValueObject {


    public EventVo(String name, Date startDate, Date endDate) {
        this.name=name;
        this.startDate=startDate;
        this.endDate=endDate;
    }

    public EventVo() {
    }

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
    
    private int id;
    private String name;
    private String type;
    private Date startDate;
    private Date endDate;
    private Date hour;
    private String description;
    
    private int studentId;
    private Integer subjectCode;

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

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public Integer getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(Integer subjectCode) {
        this.subjectCode = subjectCode;
    }
    
    
}
