package com.aditya.demo.service;

import java.util.List;

import com.aditya.demo.model.Basic;

public interface BasicService 
{
	public List<Basic> findAll();
	public Basic findById(int id);
}