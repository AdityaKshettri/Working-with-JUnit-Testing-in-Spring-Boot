package com.aditya.demo.dao;

import com.aditya.demo.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    private static final String ALIAS = "ALIAS";
    private static final String NAME = "NAME";
    private static final String UPDATED_NAME = "UPDATED_NAME";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void should_save_user() {
        //Given
        User user = givenUser();
        //When
        user = userRepository.save(user);
        //Then
        User actual = testEntityManager.find(User.class, user.getId());
        assertEquals(actual, user);
    }

    @Test
    public void should_find_user_by_id() {
        //Given
        User user = givenUser();
        user = testEntityManager.persist(user);
        //When
        Optional<User> actual = userRepository.findById(user.getId());
        //Then
        assertNotEquals(actual, Optional.empty());
        assertEquals(actual.get(), user);
    }

    @Test
    public void should_find_all_users() {
        //Given
        User user = givenUser();
        user = testEntityManager.persist(user);
        //When
        List<User> users = userRepository.findAll();
        //Then
        assertThat(users).contains(user);
        System.out.println(users);
    }

    @Test
    public void should_delete_user_by_id() {
        //Given
        User user = givenUser();
        user = testEntityManager.persist(user);
        //When
        userRepository.deleteById(user.getId());
        //Then
        User actual = testEntityManager.find(User.class, user.getId());
        assertNull(actual);
    }

    @Test
    public void should_update_user() {
        //Given
        User user = givenUser();
        user = testEntityManager.persist(user);
        User updatedUser = givenUpdatedUser();
        updatedUser.setId(user.getId());
        //When
        updatedUser = userRepository.save(updatedUser);
        //Then
        User actual = testEntityManager.find(User.class, user.getId());
        assertEquals(actual, updatedUser);

    }

    private User givenUser() {
        User user = new User();
        user.setId(0);
        user.setAlias(ALIAS);
        user.setName(NAME);
        return user;
    }

    private User givenUpdatedUser() {
        User user = new User();
        user.setAlias(ALIAS);
        user.setName(UPDATED_NAME);
        return user;
    }
}
