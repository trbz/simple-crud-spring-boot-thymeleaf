package com.crud.springboot.thymeleaf.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.springboot.thymeleaf.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	public List<Employee> findAllByOrderByLastNameAsc();

	public List<Employee> 
	findByFirstNameContainsOrLastNameContainsAllIgnoreCaseOrderByLastNameAsc(
			String firstName, String lastName);
}
