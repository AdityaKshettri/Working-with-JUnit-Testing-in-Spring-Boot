package com.aditya.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aditya.demo.dao.BasicRepository;
import com.aditya.demo.model.Basic;
import com.aditya.demo.service.BasicService;

@Service
public class BasicServiceImpl implements BasicService 
{
	@Autowired
	private BasicRepository basicRepository;
	
	@Override
	public List<Basic> findAll() {
		return basicRepository.findAll();
	}
	
	@Override
	public Basic findById(int id) {
		Optional<Basic> theBasic = basicRepository.findById(id);
		if(theBasic.isEmpty()) {
			return null;
		}
		return theBasic.get();
	}
}
