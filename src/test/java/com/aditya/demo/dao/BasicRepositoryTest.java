package com.aditya.demo.dao;

import com.aditya.demo.model.Basic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BasicRepositoryTest {

    private static final String NAME = "NAME";
    private static final String UPDATED_NAME = "UPDATED_NAME";

    @Autowired
    private BasicRepository basicRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void should_save_basic() {
        //Given
        Basic basic = givenBasic();
        basic.setId(0);
        //When
        basic = basicRepository.save(basic);
        //Then
        Basic actual = testEntityManager.find(Basic.class, basic.getId());
        assertEquals(actual, basic);
    }

    @Test
    public void should_find_basic_by_id() {
        //Given
        Basic basic = givenBasic();
        basic = testEntityManager.persist(basic);
        //When
        Optional<Basic> actual = basicRepository.findById(basic.getId());
        //Then
        assertNotEquals(actual, Optional.empty());
        assertEquals(actual.get(), basic);
    }

    @Test
    public void should_find_all_basics() {
        //Given
        Basic basic = givenBasic();
        basic = testEntityManager.persist(basic);
        //When
        List<Basic> basics = basicRepository.findAll();
        //Then
        assertThat(basics).contains(basic);
    }

    @Test
    public void should_delete_basic_by_id() {
        //Given
        Basic basic = givenBasic();
        basic = testEntityManager.persist(basic);
        //When
        basicRepository.deleteById(basic.getId());
        //Then
        Basic actual = testEntityManager.find(Basic.class, basic.getId());
        assertNull(actual);
    }

    @Test
    public void should_update_basic() {
        //Given
        Basic basic = givenBasic();
        basic = testEntityManager.persist(basic);
        Basic updatedBasic = givenUpdatedBasic();
        updatedBasic.setId(basic.getId());
        //When
        updatedBasic = basicRepository.save(updatedBasic);
        //Then
        Basic actual = testEntityManager.find(Basic.class, basic.getId());
        assertEquals(actual, updatedBasic);

    }

    private Basic givenBasic() {
        Basic basic = new Basic();
        basic.setId(0);
        basic.setName(NAME);
        return basic;
    }

    private Basic givenUpdatedBasic() {
        Basic basic = new Basic();
        basic.setName(UPDATED_NAME);
        return basic;
    }
}
