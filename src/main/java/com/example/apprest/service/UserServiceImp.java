package com.example.apprest.service;

import com.example.apprest.models.User;
import com.example.apprest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> index() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public Optional<User> show(long id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void destroy(User user) {
        userRepository.delete(user);
    }
}
