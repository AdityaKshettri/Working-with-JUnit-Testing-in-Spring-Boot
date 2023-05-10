package com.aditya.demo.initializer;

import com.aditya.demo.model.User;
import com.aditya.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
public class UserInitializer implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private UserConfig userConfig;

    @Override
    public void run(String... args) {
        userConfig.getData().forEach((alias, name) -> {
            User user = new User();
            user.setAlias(alias);
            user.setName(name);
            userService.save(user);
        });
    }
}
