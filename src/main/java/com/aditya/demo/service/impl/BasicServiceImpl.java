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
		Optional<Basic> basic = basicRepository.findById(id);
		if(basic.isEmpty()) {
			return null;
		}
		return basic.get();
	}

	@Override
	public Basic save(Basic basic) {
		if(basic.getId() != 0) {
			return null;
		}
		return basicRepository.save(basic);
	}

	@Override
	public Basic deleteById(int id) {
		Basic basic = findById(id);
		if(basic == null) {
			return null;
		}
		basicRepository.deleteById(id);
		return basic;
	}

	@Override
	public Basic update(Basic basic) {
		basic = findById(basic.getId());
		if(basic == null) {
			return null;
		}
		return basicRepository.save(basic);
	}
}
