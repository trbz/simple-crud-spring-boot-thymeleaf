package com.crud.springboot.thymeleaf.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.springboot.thymeleaf.entity.Employee;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	List<Employee> findAllByOrderByLastNameAsc();

	List<Employee>
	findByFirstNameContainsOrLastNameContainsAllIgnoreCaseOrderByLastNameAsc(
			String firstName, String lastName);
}
