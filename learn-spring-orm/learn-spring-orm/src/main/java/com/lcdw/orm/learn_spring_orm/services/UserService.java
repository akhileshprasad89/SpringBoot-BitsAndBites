package com.lcdw.orm.learn_spring_orm.services;

import com.lcdw.orm.learn_spring_orm.entities.User;

import java.util.List;

public interface UserService {
    public User saveUser(User user);
    public User updateUser(User user,int userId);
    public void deleteUser(int userId);
    public List<User> getAllUser();
    User getUser(int userId);
}
