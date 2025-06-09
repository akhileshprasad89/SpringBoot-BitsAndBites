package com.lcdw.orm.learn_spring_orm.repositories;

import com.lcdw.orm.learn_spring_orm.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
