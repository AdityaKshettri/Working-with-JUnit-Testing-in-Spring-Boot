package com.aditya.demo.initializer;

import com.aditya.demo.model.Basic;
import com.aditya.demo.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BasicInitializer implements CommandLineRunner {

    @Autowired
    private BasicService basicService;

    @Override
    public void run(String... args) throws Exception {
        Basic basic = new Basic();
        basic.setName("Aditya");
        basicService.save(basic);
    }
}
