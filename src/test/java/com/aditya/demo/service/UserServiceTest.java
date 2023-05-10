package com.aditya.demo.service;

import com.aditya.demo.dao.UserRepository;
import com.aditya.demo.model.User;
import com.aditya.demo.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    private static final int ID = 1;
    private static final String ALIAS = "ALIAS";
    private static final String NAME = "NAME";

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void should_find_all_users() {
        //Given
        User user = givenUser();
        List<User> users = Collections.singletonList(user);
        when(userRepository.findAll()).thenReturn(users);
        //When
        //Then
        assertEquals(users, userService.findAll());
    }

    @Test
    public void should_find_user_by_id() {
        //Given
        User user = givenUser();
        when(userRepository.findById(ID)).thenReturn(Optional.of(user));
        //When
        //Then
        assertEquals(user, userService.findById(ID));
    }

    @Test
    public void should_return_null_find_user_by_id() {
        //Given
        when(userRepository.findById(ID)).thenReturn(Optional.empty());
        //When
        //Then
        assertNull(userService.findById(ID));
    }

    @Test
    public void should_save_user() {
        //Given
        User user = givenUser();
        user.setId(0);
        when(userRepository.save(user)).thenReturn(user);
        //When
        //Then
        assertEquals(user, userService.save(user));
    }

    @Test
    public void should_return_null_save_user() {
        //Given
        User user = givenUser();
        when(userRepository.save(user)).thenReturn(user);
        //When
        //Then
        assertNull(userService.save(user));
    }

    @Test
    public void should_update_user() {
        //Given
        User user = givenUser();
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);
        //When
        //Then
        assertEquals(user, userService.update(user));
    }

    @Test
    public void should_return_null_update_user() {
        //Given
        User user = givenUser();
        when(userRepository.findById(user.getId())).thenReturn(Optional.empty());
        when(userRepository.save(user)).thenReturn(user);
        //When
        //Then
        assertNull(userService.update(user));
    }

    @Test
    public void should_delete_user_by_id() {
        //Given
        User user = givenUser();
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        //When
        doNothing().when(userRepository).deleteById(ID);
        //Then
        assertEquals(user, userService.deleteById(ID));
    }

    @Test
    public void should_return_null_delete_user_by_id() {
        //Given
        User user = givenUser();
        when(userRepository.findById(user.getId())).thenReturn(Optional.empty());
        //When
        doNothing().when(userRepository).deleteById(ID);
        //Then
        assertNull(userService.deleteById(ID));
    }

    private User givenUser() {
        User user = new User();
        user.setId(ID);
        user.setAlias(ALIAS);
        user.setName(NAME);
        return user;
    }
}
