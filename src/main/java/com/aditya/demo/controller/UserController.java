package com.aditya.demo.controller;

import com.aditya.demo.dto.UserDto;
import com.aditya.demo.dto.UsersDto;
import com.aditya.demo.mapper.UserMapper;
import com.aditya.demo.model.User;
import com.aditya.demo.request.UserRequest;
import com.aditya.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("")
    public UsersDto findAll() {
        List<User> users = userService.findAll();
        List<UserDto> userDtos = userMapper.map(users);
        return new UsersDto(userDtos);
    }

    @GetMapping("/{id}")
    public UserDto findById(@PathVariable int id) {
        User user = userService.findById(id);
        if (user == null) {
            return null;
        }
        return userMapper.map(user);
    }

    @PostMapping("")
    public UserDto save(@RequestBody UserRequest userRequest) {
        User user = userMapper.map(userRequest);
        user = userService.save(user);
        if (user == null) {
            return null;
        }
        return userMapper.map(user);
    }

    @DeleteMapping("/{id}")
    public UserDto deleteById(@PathVariable int id) {
        User user = userService.deleteById(id);
        if (user == null) {
            return null;
        }
        return userMapper.map(user);
    }

    @PutMapping("/{id}")
    public UserDto update(@PathVariable int id, @RequestBody UserRequest userRequest) {
        User user = userMapper.map(id, userRequest);
        user = userService.update(user);
        if (user == null) {
            return null;
        }
        return userMapper.map(user);
    }
}
