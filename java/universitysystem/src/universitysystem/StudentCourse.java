/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universitysystem;

/**
 *
 * @author Dell
 */
import java.math.BigDecimal;
import java.sql.Date;

public class StudentCourse {
    private int studentId;
    private int courseId;
    private BigDecimal grades;
    private Date dateG;

    // Constructors
    public StudentCourse() {
    }

    public StudentCourse(int studentId, int courseId, BigDecimal grades, Date dateG) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.grades = grades;
        this.dateG = dateG;
    }

    // Setters
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setGrades(BigDecimal grades) {
        this.grades = grades;
    }

    public void setDateG(Date dateG) {
        this.dateG = dateG;
    }

    // Getters
    public int getStudentId() {
        return studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public BigDecimal getGrades() {
        return grades;
    }

    public Date getDateG() {
        return dateG;
    }
}

