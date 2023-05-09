package com.aditya.demo.service;

import com.aditya.demo.model.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(int id);

    User save(User user);

    User deleteById(int id);

    User update(User user);
}