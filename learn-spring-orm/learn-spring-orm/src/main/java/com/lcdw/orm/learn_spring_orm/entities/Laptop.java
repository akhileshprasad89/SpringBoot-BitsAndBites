package com.lcdw.orm.learn_spring_orm.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "jpa_laptop")
public class Laptop {
    @Id
    private int laptopId;
    private String modelNo;
    private String brand;
    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public Laptop(int laptopId, String modelNo, String brand, Student student) {
        this.laptopId = laptopId;
        this.modelNo = modelNo;
        this.brand = brand;
        this.student = student;
    }

    public Laptop() {
    }

    public int getLaptopId() {
        return laptopId;
    }

    public void setLaptopId(int laptopId) {
        this.laptopId = laptopId;
    }

    public String getModelNo() {
        return modelNo;
    }

    public void setModelNo(String modelNo) {
        this.modelNo = modelNo;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
