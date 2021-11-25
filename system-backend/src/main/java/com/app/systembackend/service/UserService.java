package com.app.systembackend.service;

import com.app.systembackend.model.User;
import com.app.systembackend.model.UserPermission;
import com.app.systembackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserServiceInt {

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean addUser(User user) {

       return userRepository.addUser(user);
    }

    @Override
    public List<User> getAllUsers() {

        return userRepository.getAllUsers();
    }

    @Override
    public User getUserById(int id) {

       User user = userRepository.getUserById(id);

        List<UserPermission> permissions = userRepository.getAllUserPermissions(user.getUserRole().getId());

        user.getUserRole().setUserPermissions(permissions);

        return user;

    }
}
