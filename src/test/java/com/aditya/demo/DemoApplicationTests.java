package com.aditya.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.aditya.demo.model.Basic;
import com.aditya.demo.dao.BasicRepository;
import com.aditya.demo.service.impl.BasicServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class DemoApplicationTests 
{
	Basic theBasic;

	@InjectMocks
	private BasicServiceImpl basicServiceImplMock;

	@Mock
	private BasicRepository basicRepositoryMock;

	@BeforeEach
	public void initializeBasic() {
		theBasic = new Basic(1,"Aditya");
	}
	
	@Test
	public void findAllTest()
	{
		List<Basic> basics = new ArrayList<>();
		basics.add(theBasic);
		when(basicRepositoryMock.findAll()).thenReturn(basics);
		assertEquals(theBasic, basicServiceImplMock.findAll().get(0));
	}

	@Test
	public void findByIdTest()
	{
		when(basicRepositoryMock.findById(1)).thenReturn(Optional.of(theBasic));
		assertEquals(theBasic, basicServiceImplMock.findById(1));
	}

	@Test
	public void saveTest()
	{
		when(basicRepositoryMock.save(theBasic)).thenReturn(theBasic);
		assertEquals(theBasic, basicServiceImplMock.save(theBasic));
	}

	@Test
	public void deleteByIdTest()
	{
		doNothing().when(basicRepositoryMock).deleteById(1);
		assertNull(basicServiceImplMock.deleteById(1));
	}
}
