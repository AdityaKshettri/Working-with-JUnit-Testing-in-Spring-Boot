package com.aditya.demo.mapper;

import com.aditya.demo.dto.UserDto;
import com.aditya.demo.model.User;
import com.aditya.demo.request.UserRequest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserMapperTest {

    private static final int ID = 1;
    private static final String ALIAS = "ALIAS";
    private static final String NAME = "NAME";

    @InjectMocks
    private UserMapper userMapper;

    @Test
    public void should_map_user_to_userDto() {
        //Given
        User user = givenUser();
        //When
        UserDto userDto = userMapper.map(user);
        //Then
        assertEquals(ID, userDto.getId());
        assertEquals(ALIAS, userDto.getAlias());
        assertEquals(NAME, userDto.getName());
    }

    @Test
    public void should_map_userDto_to_user() {
        //Given
        UserDto userDto = givenUserDto();
        //When
        User user = userMapper.map(userDto);
        //Then
        assertEquals(ID, user.getId());
        assertEquals(ALIAS, user.getAlias());
        assertEquals(NAME, user.getName());
    }

    @Test
    public void should_map_userRequest_to_user() {
        //Given
        UserRequest createRequest = givenUserRequest();
        //When
        User user = userMapper.map(createRequest);
        //Then
        assertEquals(ALIAS, user.getAlias());
        assertEquals(NAME, user.getName());
    }

    @Test
    public void should_map_id_and_userRequest_to_user() {
        //Given
        UserRequest createRequest = givenUserRequest();
        //When
        User user = userMapper.map(ID, createRequest);
        //Then
        assertEquals(ID, user.getId());
        assertEquals(ALIAS, user.getAlias());
        assertEquals(NAME, user.getName());
    }

    @Test
    public void should_mao_users_to_userDtos() {
        //Given
        User user = givenUser();
        List<User> users = Collections.singletonList(user);
        //When
        List<UserDto> userDtos = userMapper.map(users);
        //Then
        assertThat(users).hasSize(1);
        assertEquals(ID, userDtos.get(0).getId());
        assertEquals(ALIAS, userDtos.get(0).getAlias());
        assertEquals(NAME, userDtos.get(0).getName());
    }

    private User givenUser() {
        User user = new User();
        user.setId(ID);
        user.setAlias(ALIAS);
        user.setName(NAME);
        return user;
    }

    private UserDto givenUserDto() {
        UserDto userDto = new UserDto();
        userDto.setId(ID);
        userDto.setAlias(ALIAS);
        userDto.setName(NAME);
        return userDto;
    }

    private UserRequest givenUserRequest() {
        UserRequest userRequest = new UserRequest();
        userRequest.setAlias(ALIAS);
        userRequest.setName(NAME);
        return userRequest;
    }
}
