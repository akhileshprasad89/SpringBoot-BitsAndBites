package com.lcdw.orm.learn_spring_orm.couple;

import org.springframework.stereotype.Component;

//@Component
public class Cat implements Animal{
    @Override
    public void play() {
        System.out.println("Cat is playing");
    }
}
