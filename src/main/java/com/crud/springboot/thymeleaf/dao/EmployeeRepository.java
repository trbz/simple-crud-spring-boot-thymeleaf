package com.crud.springboot.thymeleaf.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.springboot.thymeleaf.entity.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
