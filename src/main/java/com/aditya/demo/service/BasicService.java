package com.aditya.demo.service;

import java.util.List;

import com.aditya.demo.model.Basic;

public interface BasicService 
{
	List<Basic> findAll();
	Basic findById(int id);
	Basic save(Basic theBasic);
	Basic deleteById(int id);
	Basic update(Basic theBasic);
}