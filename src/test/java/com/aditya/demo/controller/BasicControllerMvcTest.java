package com.aditya.demo.controller;

import com.aditya.demo.controller.helper.JsonHelper;
import com.aditya.demo.dto.BasicDto;
import com.aditya.demo.dto.BasicsDto;
import com.aditya.demo.mapper.BasicMapper;
import com.aditya.demo.model.Basic;
import com.aditya.demo.request.CreateRequest;
import com.aditya.demo.service.BasicService;
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
public class BasicControllerMvcTest {

    private static final int ID = 1;
    private static final String NAME = "NAME";

    private static final String BASIC_URL = "/basics";
    private static final String BASIC_ID_URL = "/basics/{id}";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BasicService basicService;

    @MockBean
    private BasicMapper basicMapper;

    @Test
    public void should_find_all_basics_returns_200_nominal_case() throws Exception {
        //Given
        Basic basic = givenBasic();
        List<Basic> basics = Collections.singletonList(basic);
        when(basicService.findAll()).thenReturn(basics);
        BasicDto basicDto = givenBasicDto();
        List<BasicDto> basicDtos = Collections.singletonList(basicDto);
        when(basicMapper.map(basics)).thenReturn(basicDtos);
        BasicsDto basicsDto = new BasicsDto(basicDtos);
        //When
        String expected = JsonHelper.toJson(basicsDto).orElse("");
        //Then
        mockMvc.perform(get(BASIC_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    public void should_find_basic_by_id_returns_200_nominal_case() throws Exception {
        //Given
        Basic basic = givenBasic();
        when(basicService.findById(ID)).thenReturn(basic);
        BasicDto basicDto = givenBasicDto();
        when(basicMapper.map(basic)).thenReturn(basicDto);
        //When
        String expected = JsonHelper.toJson(basicDto).orElse("");
        //Then
        mockMvc.perform(get(BASIC_ID_URL, ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    public void should_save_basic_returns_200_nominal_case() throws Exception {
        //Given
        CreateRequest createRequest = givenCreateRequest();
        Basic basic = givenBasic();
        BasicDto basicDto = givenBasicDto();
        when(basicMapper.map(createRequest)).thenReturn(basic);
        when(basicService.save(basic)).thenReturn(basic);
        when(basicMapper.map(basic)).thenReturn(basicDto);
        //When
        String createRequestJson = JsonHelper.toJson(createRequest).orElse("");
        String expected = JsonHelper.toJson(basicDto).orElse("");
        //Then
        mockMvc.perform(post(BASIC_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(createRequestJson))
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    public void should_delete_basic_by_id_returns_200_nominal_case() throws Exception {
        //Given
        Basic basic = givenBasic();
        when(basicService.deleteById(ID)).thenReturn(basic);
        BasicDto basicDto = givenBasicDto();
        when(basicMapper.map(basic)).thenReturn(basicDto);
        //When
        String expected = JsonHelper.toJson(basicDto).orElse("");
        //Then
        mockMvc.perform(delete(BASIC_ID_URL, ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    public void should_update_basic_returns_200_nominal_case() throws Exception {
        //Given
        Basic basic = givenBasic();
        BasicDto basicDto = givenBasicDto();
        when(basicMapper.map(basicDto)).thenReturn(basic);
        when(basicService.update(basic)).thenReturn(basic);
        when(basicMapper.map(basic)).thenReturn(basicDto);
        //When
        String expected = JsonHelper.toJson(basicDto).orElse("");
        //Then
        mockMvc.perform(put(BASIC_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(expected))
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    private CreateRequest givenCreateRequest() {
        CreateRequest createRequest = new CreateRequest();
        createRequest.setName(NAME);
        return createRequest;
    }

    private BasicDto givenBasicDto() {
        BasicDto basicDto = new BasicDto();
        basicDto.setId(ID);
        basicDto.setName(NAME);
        return basicDto;
    }

    private Basic givenBasic() {
        Basic basic = new Basic();
        basic.setId(ID);
        basic.setName(NAME);
        return basic;
    }
}
