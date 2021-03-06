package com.example.apprest.service;

import com.example.apprest.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> index();
    Optional<User> show(long id);
    User save(User user);
    void destroy(User user);
}
