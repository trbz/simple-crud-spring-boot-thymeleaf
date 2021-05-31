package com.crud.springboot.thymeleaf.service;

import com.crud.springboot.thymeleaf.entity.Employee;

import java.util.List;

public interface EmployeeService {

	List<Employee> findAll();
	
	Employee findById(int theId);
	
	void save(Employee theEmployee);
	
	void deleteById(int theId);

	List<Employee> searchBy(String theName);

}
