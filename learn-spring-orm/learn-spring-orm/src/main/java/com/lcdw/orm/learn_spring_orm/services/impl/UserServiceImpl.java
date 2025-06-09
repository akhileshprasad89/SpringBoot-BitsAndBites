package com.lcdw.orm.learn_spring_orm.services.impl;

import com.lcdw.orm.learn_spring_orm.entities.User;
import com.lcdw.orm.learn_spring_orm.repositories.UserRepository;
import com.lcdw.orm.learn_spring_orm.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    Logger logger=LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;
    @Override
    public User saveUser(User user) {
        User saveUser = userRepository.save(user);
        logger.info("User Saved {}",saveUser.getId());
        return saveUser;
    }

    @Override
    public User updateUser(User user, int userId) {
        Optional<User> byId = userRepository.findById(userId);
        User oldUser = byId.orElseThrow(() -> new RuntimeException("User not found"));
        oldUser.setName(user.getName());
        oldUser.setCity(user.getCity());
        oldUser.setAge(user.getAge());
        User save = userRepository.save(oldUser);
        return save;
    }

    @Override
    public void deleteUser(int userId) {
        User userNotFound = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not Found"));
        userRepository.delete(userNotFound);
        logger.info("User Deleted");
    }

    @Override
    public List<User> getAllUser() {
        List<User> all = userRepository.findAll();
        return all;
    }

    @Override
    public User getUser(int userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        User userNotFound = userOptional.orElseThrow(() -> new RuntimeException("User Not found"));
        return userNotFound;
    }

}
