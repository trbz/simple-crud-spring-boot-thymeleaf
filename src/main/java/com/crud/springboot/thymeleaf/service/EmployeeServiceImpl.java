package com.crud.springboot.thymeleaf.service;

import com.crud.springboot.thymeleaf.dao.EmployeeRepository;
import com.crud.springboot.thymeleaf.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository employeeRepository;

	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAllByOrderByLastNameAsc(); 
	}

	@Override
	public Employee findById(int theId) {
		Optional<Employee> result = employeeRepository.findById(theId);
		
		Employee theEmployee = null;
		
		if (result.isPresent()) {
			theEmployee = result.get();
		} else {
			// we didn't find the employee
			throw new RuntimeException("Did not find employee id - " + theId);
		}
		
		return theEmployee;
	}

	@Override
	public void save(Employee theEmployee) {
		employeeRepository.save(theEmployee);
	}

	@Override
	public void deleteById(int theId) {
		employeeRepository.deleteById(theId);
	}

	@Override
	public List<Employee> searchBy(String theName) {
		
		List<Employee> results = null;
		
		if (theName != null && (theName.trim().length() > 0)) {
			
			results = employeeRepository
					.findByFirstNameContainsOrLastNameContainsAllIgnoreCaseOrderByLastNameAsc(
							theName, theName);
			
		} else {
			results = employeeRepository.findAllByOrderByLastNameAsc();
		}
		
		
		return results;
		
	}

}
