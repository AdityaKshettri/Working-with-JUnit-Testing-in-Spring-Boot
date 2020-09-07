package com.aditya.demo.controller;

import com.aditya.demo.DemoApplication;
import com.aditya.demo.controller.helper.HttpHelper;
import com.aditya.demo.dto.BasicDto;
import com.aditya.demo.dto.BasicsDto;
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
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BasicControllerIntegrationTest {

    private static final String BASIC_URL = "http://localhost:%s/basics";
    private static final String BASIC_ID_URL = "http://localhost:%s/basics/%s";

    private static final int ID = 10;
    private static final int DELETE_ID = 20;

    private static final String NAME = "NAME";
    private static final String NEW_NAME = "NEW_NAME";
    private static final String DELETE_NAME = "DELETE_NAME";

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    @Test
    public void should_find_basic_by_id() {
        //Given
        String url = String.format(BASIC_ID_URL, port, ID);
        HttpEntity<String> request = HttpHelper.getHttpEntity();
        //When
        ResponseEntity<BasicDto> response = testRestTemplate.exchange(url, HttpMethod.GET, request, BasicDto.class);
        //Then
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        assertEquals(response.getBody().getId(), ID);
        assertEquals(response.getBody().getName(), NAME);
    }

    @Test
    public void should_find_all_basics() {
        //Given
        String url = String.format(BASIC_URL, port);
        HttpEntity<String> request = HttpHelper.getHttpEntity();
        //When
        ResponseEntity<BasicsDto> response = testRestTemplate.exchange(url, HttpMethod.GET, request, BasicsDto.class);
        //Then
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        List<BasicDto> basicDtos = response.getBody().getBasicDtos();
        assertEquals(3, basicDtos.size());
        assertEquals(ID, basicDtos.get(0).getId());
        assertEquals(NAME, basicDtos.get(0).getName());
    }

    @Test
    public void should_save_basic() {
        //Given
        String url = String.format(BASIC_URL, port);
        CreateRequest createRequest = givenCreateRequest();
        HttpEntity<CreateRequest> request = HttpHelper.getHttpEntity(createRequest);
        //When
        ResponseEntity<BasicDto> response = testRestTemplate.exchange(url, HttpMethod.POST, request, BasicDto.class);
        //Then
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        assertEquals(response.getBody().getName(), NEW_NAME);
    }

    @Test
    public void should_update_basic() {
        //Given
        String url = String.format(BASIC_URL, port);
        BasicDto basicDto = givenBasicDto();
        HttpEntity<BasicDto> request = HttpHelper.getHttpEntity(basicDto);
        //When
        ResponseEntity<BasicDto> response = testRestTemplate.exchange(url, HttpMethod.PUT, request, BasicDto.class);
        //Then
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        assertEquals(ID, response.getBody().getId());
        assertEquals(NAME, response.getBody().getName());
    }

    @Test
    public void should_delete_basic_by_id() {
        //Given
        String url = String.format(BASIC_ID_URL, port, DELETE_ID);
        HttpEntity<String> request = HttpHelper.getHttpEntity();
        //When
        ResponseEntity<BasicDto> response = testRestTemplate.exchange(url, HttpMethod.DELETE, request, BasicDto.class);
        //Then
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        assertEquals(DELETE_ID, response.getBody().getId());
        assertEquals(DELETE_NAME, response.getBody().getName());
    }

    private CreateRequest givenCreateRequest() {
        CreateRequest createRequest = new CreateRequest();
        createRequest.setName(NEW_NAME);
        return createRequest;
    }

    private BasicDto givenBasicDto() {
        BasicDto basicDto = new BasicDto();
        basicDto.setId(ID);
        basicDto.setName(NAME);
        return basicDto;
    }
}
