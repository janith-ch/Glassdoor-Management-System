package com.app.systembackend.service;

import com.app.systembackend.model.User;

import java.util.List;

public interface UserServiceInt {

     User addUser(User user);

     List<User> getAllUsers();

     User getUserById(int id);
}
