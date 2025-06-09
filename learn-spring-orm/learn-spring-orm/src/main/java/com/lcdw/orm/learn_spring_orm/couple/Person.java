package com.lcdw.orm.learn_spring_orm.couple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Person {

    @Autowired
    Animal animal;
    @Autowired
    Student student;

//    @Autowired
//    public Person(Animal animal,Student student) {
//        this.animal = animal;
//        this.student=student;
//    }


    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public  void playWithAnimal(){
        animal.play();
        student.detail();
    }
}
