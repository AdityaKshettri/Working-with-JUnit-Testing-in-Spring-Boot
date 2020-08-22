package com.aditya.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aditya.demo.model.Basic;
import com.aditya.demo.service.BasicService;

@RestController
@RequestMapping("/basics")
public class BasicController 
{
	@Autowired
	private BasicService basicService;
	
	@GetMapping("")
	public List<Basic> findAll() {
		return basicService.findAll();
	}
	
	@GetMapping("/{id}")
	public Basic findById(@PathVariable int id) {
		return basicService.findById(id);
	}
	
	@PostMapping("")
	public Basic save(@RequestBody Basic theBasic) {
		return basicService.save(theBasic);
	}
	
	@DeleteMapping("/{id}")
	public Basic deleteById(@PathVariable int id) {
		return basicService.deleteById(id);
	}
}
