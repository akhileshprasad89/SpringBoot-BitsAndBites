package com.lcdw.orm.learn_spring_orm.repositories;

import com.lcdw.orm.learn_spring_orm.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentReposatory extends JpaRepository<Student,Integer> {
}
