package com.aditya.demo.mapper;

import com.aditya.demo.dto.UserDto;
import com.aditya.demo.model.User;
import com.aditya.demo.request.CreateRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserDto map(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setAlias(user.getAlias());
        userDto.setName(user.getName());
        return userDto;
    }

    public User map(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setAlias(userDto.getAlias());
        user.setName(userDto.getName());
        return user;
    }

    public User map(CreateRequest createRequest) {
        User user = new User();
        user.setAlias(createRequest.getAlias());
        user.setName(createRequest.getName());
        return user;
    }

    public List<UserDto> map(List<User> users) {
        return users
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}
