/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avangarde.gnosis.vo;

/**
 *
 * @author Alexander
 */
public class RatingVo implements IValueObject{
    
    private int id;
    private int rating;
    private int studentId;
    private Integer publicationId;
    private Integer tutorSubjectId;

    public Integer getTutorSubjectId() {
        return tutorSubjectId;
    }

    public void setTutorSubjectId(Integer tutorSubjectId) {
        this.tutorSubjectId = tutorSubjectId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public Integer getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(Integer publicationId) {
        this.publicationId = publicationId;
    }
    
}
