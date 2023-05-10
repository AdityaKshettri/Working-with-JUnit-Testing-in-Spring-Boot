package com.aditya.demo.initializer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public class UserConfigTest {

    @Autowired
    private UserConfig userConfig;

    @Test
    public void verifyData() {
        assertThat(userConfig.getData()).isNotNull()
                .containsEntry("alias", "name");
    }
}
