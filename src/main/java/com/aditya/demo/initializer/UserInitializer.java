package com.aditya.demo.initializer;

import com.aditya.demo.model.User;
import com.aditya.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserInitializer implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) {
        User user = new User();
        user.setName("Aditya");
        userService.save(user);
    }
}
