package com.aditya.demo.mapper;

import com.aditya.demo.dto.BasicDto;
import com.aditya.demo.model.Basic;
import com.aditya.demo.request.CreateRequest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class BasicMapperTest {

    private static final int ID = 1;
    private static final String NAME = "NAME";

    @InjectMocks
    private BasicMapper basicMapper;

    @Test
    public void should_map_basic_to_basicDto() {
        //Given
        Basic basic = givenBasic();
        //When
        BasicDto basicDto = basicMapper.map(basic);
        //Then
        assertEquals(basic.getId(), basicDto.getId());
        assertEquals(basic.getName(), basicDto.getName());
    }

    @Test
    public void should_map_basicDto_to_basic() {
        //Given
        BasicDto basicDto = givenBasicDto();
        //When
        Basic basic = basicMapper.map(basicDto);
        //Then
        assertEquals(basic.getId(), basicDto.getId());
        assertEquals(basic.getName(), basicDto.getName());
    }

    @Test
    public void should_map_createRequest_to_basic() {
        //Given
        CreateRequest createRequest = givenCreateRequest();
        //When
        Basic basic = basicMapper.map(createRequest);
        //Then
        assertEquals(basic.getName(), createRequest.getName());
    }

    @Test
    public void should_mao_basics_to_basicDtos() {
        //Given
        Basic basic = givenBasic();
        List<Basic> basics = Collections.singletonList(basic);
        //When
        List<BasicDto> basicDtos = basicMapper.map(basics);
        //Then
        assertEquals(basics.size(), basicDtos.size());
        assertEquals(basicDtos.get(0).getId(), basics.get(0).getId());
        assertEquals(basicDtos.get(0).getName(), basics.get(0).getName());
    }

    private Basic givenBasic() {
        Basic basic = new Basic();
        basic.setId(ID);
        basic.setName(NAME);
        return basic;
    }

    private BasicDto givenBasicDto() {
        BasicDto basicDto = new BasicDto();
        basicDto.setId(ID);
        basicDto.setName(NAME);
        return basicDto;
    }

    private CreateRequest givenCreateRequest() {
        CreateRequest createRequest = new CreateRequest();
        createRequest.setName(NAME);
        return createRequest;
    }
}
