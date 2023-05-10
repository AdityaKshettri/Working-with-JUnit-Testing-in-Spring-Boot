package com.aditya.demo.controller;

import com.aditya.demo.DemoApplication;
import com.aditya.demo.controller.helper.HttpHelper;
import com.aditya.demo.dto.UserDto;
import com.aditya.demo.dto.UsersDto;
import com.aditya.demo.request.CreateRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIntegrationTest {

    private static final String USER_URL = "http://localhost:%s/users";
    private static final String USER_ID_URL = "http://localhost:%s/users/%s";

    private static final int ID = 10;
    private static final int DELETE_ID = 20;
    private static final String ALIAS = "ALIAS";
    private static final String NEW_ALIAS = "NEW_ALIAS";
    private static final String TEST_ALIAS = "TEST_ALIAS";
    private static final String DELETE_ALIAS = "DELETE_ALIAS";
    private static final String NAME = "NAME";
    private static final String NEW_NAME = "NEW_NAME";
    private static final String DELETE_NAME = "DELETE_NAME";

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    @Test
    public void should_find_user_by_id() {
        //Given
        String url = String.format(USER_ID_URL, port, ID);
        HttpEntity<String> request = HttpHelper.getHttpEntity();
        //When
        ResponseEntity<UserDto> response = testRestTemplate.exchange(url, HttpMethod.GET, request, UserDto.class);
        //Then
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        assertEquals(response.getBody().getId(), ID);
        assertEquals(response.getBody().getAlias(), TEST_ALIAS);
        assertEquals(response.getBody().getName(), NAME);
    }

    @Test
    public void should_find_all_users() {
        //Given
        String url = String.format(USER_URL, port);
        HttpEntity<String> request = HttpHelper.getHttpEntity();
        //When
        ResponseEntity<UsersDto> response = testRestTemplate.exchange(url, HttpMethod.GET, request, UsersDto.class);
        //Then
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        List<UserDto> userDtos = response.getBody().getUserDtos();
        assertEquals(4, userDtos.size());
        assertEquals(ID, userDtos.get(0).getId());
        assertEquals(ALIAS, userDtos.get(0).getAlias());
        assertEquals(NAME, userDtos.get(0).getName());
    }

    @Test
    public void should_save_user() {
        //Given
        String url = String.format(USER_URL, port);
        CreateRequest createRequest = givenCreateRequest();
        HttpEntity<CreateRequest> request = HttpHelper.getHttpEntity(createRequest);
        //When
        ResponseEntity<UserDto> response = testRestTemplate.exchange(url, HttpMethod.POST, request, UserDto.class);
        //Then
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        assertEquals(response.getBody().getAlias(), NEW_ALIAS);
        assertEquals(response.getBody().getName(), NEW_NAME);
    }

    @Test
    public void should_update_user() {
        //Given
        String url = String.format(USER_URL, port);
        UserDto userDto = givenUserDto();
        HttpEntity<UserDto> request = HttpHelper.getHttpEntity(userDto);
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
    public void should_delete_user_by_id() {
        //Given
        String url = String.format(USER_ID_URL, port, DELETE_ID);
        HttpEntity<String> request = HttpHelper.getHttpEntity();
        //When
        ResponseEntity<UserDto> response = testRestTemplate.exchange(url, HttpMethod.DELETE, request, UserDto.class);
        //Then
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        assertEquals(DELETE_ID, response.getBody().getId());
        assertEquals(DELETE_ALIAS, response.getBody().getAlias());
        assertEquals(DELETE_NAME, response.getBody().getName());
    }

    private CreateRequest givenCreateRequest() {
        CreateRequest createRequest = new CreateRequest();
        createRequest.setAlias(NEW_ALIAS);
        createRequest.setName(NEW_NAME);
        return createRequest;
    }

    private UserDto givenUserDto() {
        UserDto userDto = new UserDto();
        userDto.setId(ID);
        userDto.setAlias(ALIAS);
        userDto.setName(NAME);
        return userDto;
    }
}
