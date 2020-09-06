package com.aditya.demo.service;

import com.aditya.demo.dao.BasicRepository;
import com.aditya.demo.model.Basic;
import com.aditya.demo.service.impl.BasicServiceImpl;
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
public class BasicServiceTest {

    private static final int ID = 1;
    private static final String NAME = "NAME";

    @InjectMocks
    private BasicServiceImpl basicService;

    @Mock
    private BasicRepository basicRepositoryMock;

    @Test
    public void should_find_all_basics() {
        //Given
        Basic basic = givenBasic();
        List<Basic> basics = Collections.singletonList(basic);
        when(basicRepositoryMock.findAll()).thenReturn(basics);
        //When
        //Then
        assertEquals(basic, basicService.findAll().get(0));
    }

    @Test
    public void should_find_basic_by_id() {
        //Given
        Basic basic = givenBasic();
        when(basicRepositoryMock.findById(ID)).thenReturn(Optional.of(basic));
        //When
        //Then
        assertEquals(basic, basicService.findById(ID));
    }

    @Test
    public void should_return_null_find_basic_by_id() {
        //Given
        when(basicRepositoryMock.findById(ID)).thenReturn(Optional.empty());
        //When
        //Then
        assertNull(basicService.findById(ID));
    }

    @Test
    public void should_save_basic() {
        //Given
        Basic basic = givenBasic();
        basic.setId(0);
        when(basicRepositoryMock.save(basic)).thenReturn(basic);
        //When
        //Then
        assertEquals(basic, basicService.save(basic));
    }

    @Test
    public void should_return_null_save_basic() {
        //Given
        Basic basic = givenBasic();
        when(basicRepositoryMock.save(basic)).thenReturn(basic);
        //When
        //Then
        assertNull(basicService.save(basic));
    }

    @Test
    public void should_update_basic() {
        //Given
        Basic basic = givenBasic();
        when(basicRepositoryMock.findById(basic.getId())).thenReturn(Optional.of(basic));
        when(basicRepositoryMock.save(basic)).thenReturn(basic);
        //When
        //Then
        assertEquals(basic, basicService.update(basic));
    }

    @Test
    public void should_return_null_update_basic() {
        //Given
        Basic basic = givenBasic();
        when(basicRepositoryMock.findById(basic.getId())).thenReturn(Optional.empty());
        when(basicRepositoryMock.save(basic)).thenReturn(basic);
        //When
        //Then
        assertNull(basicService.update(basic));
    }

    @Test
    public void should_delete_basic_by_id() {
        //Given
        Basic basic = givenBasic();
        when(basicRepositoryMock.findById(basic.getId())).thenReturn(Optional.of(basic));
        //When
        doNothing().when(basicRepositoryMock).deleteById(ID);
        //Then
        assertEquals(basic, basicService.deleteById(ID));
    }

    @Test
    public void should_return_null_delete_basic_by_id() {
        //Given
        Basic basic = givenBasic();
        when(basicRepositoryMock.findById(basic.getId())).thenReturn(Optional.empty());
        //When
        doNothing().when(basicRepositoryMock).deleteById(ID);
        //Then
        assertNull(basicService.deleteById(ID));
    }

    private Basic givenBasic() {
        Basic basic = new Basic();
        basic.setId(ID);
        basic.setName(NAME);
        return basic;
    }
}
