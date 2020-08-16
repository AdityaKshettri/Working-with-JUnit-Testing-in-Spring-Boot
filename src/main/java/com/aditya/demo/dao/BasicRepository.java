package com.aditya.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aditya.demo.model.Basic;

@Repository
public interface BasicRepository extends JpaRepository<Basic, Integer>
{

}
