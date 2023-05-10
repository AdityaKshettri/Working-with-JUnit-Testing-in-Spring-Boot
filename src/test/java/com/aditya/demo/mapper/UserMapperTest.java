package com.aditya.demo.mapper;

import com.aditya.demo.dto.UserDto;
import com.aditya.demo.model.User;
import com.aditya.demo.request.CreateRequest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
        assertEquals(user.getId(), userDto.getId());
        assertEquals(user.getAlias(), userDto.getAlias());
        assertEquals(user.getName(), userDto.getName());
    }

    @Test
    public void should_map_userDto_to_user() {
        //Given
        UserDto userDto = givenUserDto();
        //When
        User user = userMapper.map(userDto);
        //Then
        assertEquals(user.getId(), userDto.getId());
        assertEquals(user.getAlias(), userDto.getAlias());
        assertEquals(user.getName(), userDto.getName());
    }

    @Test
    public void should_map_createRequest_to_user() {
        //Given
        CreateRequest createRequest = givenCreateRequest();
        //When
        User user = userMapper.map(createRequest);
        //Then
        assertEquals(user.getAlias(), createRequest.getAlias());
        assertEquals(user.getName(), createRequest.getName());
    }

    @Test
    public void should_mao_users_to_userDtos() {
        //Given
        User user = givenUser();
        List<User> users = Collections.singletonList(user);
        //When
        List<UserDto> userDtos = userMapper.map(users);
        //Then
        assertEquals(users.size(), userDtos.size());
        assertEquals(userDtos.get(0).getId(), users.get(0).getId());
        assertEquals(userDtos.get(0).getAlias(), users.get(0).getAlias());
        assertEquals(userDtos.get(0).getName(), users.get(0).getName());
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

    private CreateRequest givenCreateRequest() {
        CreateRequest createRequest = new CreateRequest();
        createRequest.setAlias(ALIAS);
        createRequest.setName(NAME);
        return createRequest;
    }
}
