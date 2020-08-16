package com.aditya.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.aditya.demo.model.Basic;
import com.aditya.demo.service.BasicService;

@SpringBootTest
class DemoApplicationTests 
{
	@Autowired
	private BasicService basicService;
	
	@Test
	public void findAllTest() throws Exception {
		assertEquals(2, basicService.findAll().size());
	}
	
	@Test
	public void findByIdTest() throws Exception 
	{
		Basic theBasic = new Basic(1, "Aditya");
		assertEquals(theBasic, basicService.findById(1));
	}

}
