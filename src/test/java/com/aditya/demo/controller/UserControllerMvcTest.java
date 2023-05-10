package com.aditya.demo.controller;

import com.aditya.demo.controller.helper.JsonHelper;
import com.aditya.demo.dto.UserDto;
import com.aditya.demo.dto.UsersDto;
import com.aditya.demo.mapper.UserMapper;
import com.aditya.demo.model.User;
import com.aditya.demo.request.UserRequest;
import com.aditya.demo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class UserControllerMvcTest {

    private static final int ID = 1;
    private static final String ALIAS = "ALIAS";
    private static final String NAME = "NAME";

    private static final String USER_URL = "/users";
    private static final String USER_ID_URL = "/users/{id}";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserMapper userMapper;

    @Test
    public void should_find_all_users_returns_200_nominal_case() throws Exception {
        //Given
        User user = givenUser();
        List<User> users = Collections.singletonList(user);
        when(userService.findAll()).thenReturn(users);
        UserDto userDto = givenUserDto();
        List<UserDto> userDtos = Collections.singletonList(userDto);
        when(userMapper.map(users)).thenReturn(userDtos);
        UsersDto usersDto = new UsersDto(userDtos);
        String expected = JsonHelper.toJson(usersDto).orElse("");
        //When
        mockMvc.perform(get(USER_URL)
                .contentType(MediaType.APPLICATION_JSON))
                //Then
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    public void should_find_user_by_id_returns_200_nominal_case() throws Exception {
        //Given
        User user = givenUser();
        when(userService.findById(ID)).thenReturn(user);
        UserDto userDto = givenUserDto();
        when(userMapper.map(user)).thenReturn(userDto);
        String expected = JsonHelper.toJson(userDto).orElse("");
        //When
        mockMvc.perform(get(USER_ID_URL, ID)
                .contentType(MediaType.APPLICATION_JSON))
                //Then
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    public void should_save_user_returns_200_nominal_case() throws Exception {
        //Given
        UserRequest createRequest = givenUserRequest();
        User user = givenUser();
        UserDto userDto = givenUserDto();
        when(userMapper.map(createRequest)).thenReturn(user);
        when(userService.save(user)).thenReturn(user);
        when(userMapper.map(user)).thenReturn(userDto);
        String createRequestJson = JsonHelper.toJson(createRequest).orElse("");
        String expected = JsonHelper.toJson(userDto).orElse("");
        //When
        mockMvc.perform(post(USER_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(createRequestJson))
                //Then
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    public void should_delete_user_by_id_returns_200_nominal_case() throws Exception {
        //Given
        User user = givenUser();
        when(userService.deleteById(ID)).thenReturn(user);
        UserDto userDto = givenUserDto();
        when(userMapper.map(user)).thenReturn(userDto);
        String expected = JsonHelper.toJson(userDto).orElse("");
        //When
        mockMvc.perform(delete(USER_ID_URL, ID)
                .contentType(MediaType.APPLICATION_JSON))
                //Then
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    public void should_update_user_returns_200_nominal_case() throws Exception {
        //Given
        User user = givenUser();
        UserRequest userRequest = givenUserRequest();
        UserDto userDto = givenUserDto();
        when(userMapper.map(ID, userRequest)).thenReturn(user);
        when(userService.update(user)).thenReturn(user);
        when(userMapper.map(user)).thenReturn(userDto);
        String userRequestJson = JsonHelper.toJson(userRequest).orElse("");
        String expected = JsonHelper.toJson(userDto).orElse("");
        //When
        mockMvc.perform(put(USER_ID_URL, ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(userRequestJson))
                //Then
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    private UserRequest givenUserRequest() {
        UserRequest userRequest = new UserRequest();
        userRequest.setAlias(ALIAS);
        userRequest.setName(NAME);
        return userRequest;
    }

    private UserDto givenUserDto() {
        UserDto userDto = new UserDto();
        userDto.setId(ID);
        userDto.setAlias(ALIAS);
        userDto.setName(NAME);
        return userDto;
    }

    private User givenUser() {
        User user = new User();
        user.setId(ID);
        user.setAlias(ALIAS);
        user.setName(NAME);
        return user;
    }
}
