package com.lcdw.orm.learn_spring_orm.couple;

import org.springframework.stereotype.Component;

@Component
public class Student {
    public Student() {
        System.out.println("Creating Student");
    }
    public  void detail(){
        System.out.println("Detailing student");
    }
}
