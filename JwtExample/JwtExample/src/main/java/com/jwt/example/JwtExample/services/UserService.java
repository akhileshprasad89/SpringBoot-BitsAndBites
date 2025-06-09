package com.jwt.example.JwtExample.services;

import com.jwt.example.JwtExample.models.User; // ✅ correct import
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UserService {
    List<User> store = new ArrayList<>();

    public UserService() {
        store.add(new User(UUID.randomUUID().toString(), "Akhilesh Prasad", "akhilesh@gmail.com"));
        store.add(new User(UUID.randomUUID().toString(), "Ankit Prasad", "ankit@gmail.com"));
        store.add(new User(UUID.randomUUID().toString(), "Aarya Prasad", "arya@gmail.com"));
        store.add(new User(UUID.randomUUID().toString(), "Anshi Prasad", "anshi@gmail.com"));
    }

    public List<User> getUser() {
        return store;
    }
}