package com.aditya.demo.controller;

import com.aditya.demo.DemoApplication;
import com.aditya.demo.controller.helper.HttpHelper;
import com.aditya.demo.dto.UserDto;
import com.aditya.demo.dto.UsersDto;
import com.aditya.demo.request.UserRequest;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIntegrationTest {

    private static final String USER_URL = "http://localhost:%s/users";
    private static final String USER_ID_URL = "http://localhost:%s/users/%s";

    private static final int ID = 3;
    private static final String ALIAS = "ALIAS";
    private static final String NAME = "NAME";

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    @Test
    public void test_1_should_save_user() {
        //Given
        String url = String.format(USER_URL, port);
        UserRequest createRequest = givenUserRequest();
        HttpEntity<UserRequest> request = HttpHelper.getHttpEntity(createRequest);
        //When
        ResponseEntity<UserDto> response = testRestTemplate.exchange(url, HttpMethod.POST, request, UserDto.class);
        //Then
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        assertEquals(response.getBody().getId(), ID);
        assertEquals(response.getBody().getAlias(), ALIAS);
        assertEquals(response.getBody().getName(), NAME);
    }

    @Test
    public void test_2_should_find_user_by_id() {
        //Given
        String url = String.format(USER_ID_URL, port, ID);
        HttpEntity<String> request = HttpHelper.getHttpEntity();
        //When
        ResponseEntity<UserDto> response = testRestTemplate.exchange(url, HttpMethod.GET, request, UserDto.class);
        //Then
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        assertEquals(response.getBody().getId(), ID);
        assertEquals(response.getBody().getAlias(), ALIAS);
        assertEquals(response.getBody().getName(), NAME);
    }

    @Test
    public void test_3_should_find_all_users() {
        //Given
        String url = String.format(USER_URL, port);
        HttpEntity<String> request = HttpHelper.getHttpEntity();
        //When
        ResponseEntity<UsersDto> response = testRestTemplate.exchange(url, HttpMethod.GET, request, UsersDto.class);
        //Then
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        List<UserDto> userDtos = response.getBody().getUserDtos();
        assertEquals(3, userDtos.size());
        assertEquals(ID, userDtos.get(2).getId());
        assertEquals(ALIAS, userDtos.get(2).getAlias());
        assertEquals(NAME, userDtos.get(2).getName());
    }

    @Test
    public void test_4_should_update_user() {
        //Given
        String url = String.format(USER_ID_URL, port, ID);
        UserRequest userRequest = givenUserRequest();
        HttpEntity<UserRequest> request = HttpHelper.getHttpEntity(userRequest);
        //When
        ResponseEntity<UserDto> response = testRestTemplate.exchange(url, HttpMethod.PUT, request, UserDto.class);
        //Then
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        assertEquals(ID, response.getBody().getId());
        assertEquals(ALIAS, response.getBody().getAlias());
        assertEquals(NAME, response.getBody().getName());
    }

    @Test
    public void test_5_should_delete_user_by_id() {
        //Given
        String url = String.format(USER_ID_URL, port, ID);
        HttpEntity<String> request = HttpHelper.getHttpEntity();
        //When
        ResponseEntity<UserDto> response = testRestTemplate.exchange(url, HttpMethod.DELETE, request, UserDto.class);
        //Then
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        assertEquals(ID, response.getBody().getId());
        assertEquals(ALIAS, response.getBody().getAlias());
        assertEquals(NAME, response.getBody().getName());
    }

    private UserRequest givenUserRequest() {
        UserRequest userRequest = new UserRequest();
        userRequest.setAlias(ALIAS);
        userRequest.setName(NAME);
        return userRequest;
    }
}
