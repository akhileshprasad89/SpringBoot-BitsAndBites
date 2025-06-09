package com.lcdw.orm.learn_spring_orm.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "jpa_student")
public class Student {
    @Id
    private int studentId;
    private String studentName;
    private String about;


    public Student(int studentId, String studentName, String about) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.about = about;

    }

    public Student() {
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }


}
