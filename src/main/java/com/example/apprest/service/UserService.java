package com.example.apprest.service;

import com.example.apprest.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    Optional<User> getOneUser(long id);
    User saveUser(User user);
    void deleteUser(User user);
}
