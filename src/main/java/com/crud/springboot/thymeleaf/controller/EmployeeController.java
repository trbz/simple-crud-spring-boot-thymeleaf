package com.crud.springboot.thymeleaf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crud.springboot.thymeleaf.entity.Employee;
import com.crud.springboot.thymeleaf.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

	// CONTROLLERADVICE!!!
	
	private final EmployeeService employeeService;

	// == show list of employees ==
	@GetMapping("/list")
	public String listEmployees(Model theModel) {

		List<Employee> theEmployees = employeeService.findAll();
		
		theModel.addAttribute("employees", theEmployees); 
		
		return "employees/list-employees";
	}
	
	// == show add form ==
	@GetMapping("/addForm")
	public String addForm(Model theModel) {
	
		Employee theEmployee = new Employee();
		
		theModel.addAttribute("employee", theEmployee);
		
		return "employees/employee-form";
	}
	
	// == save employee ==
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {
		
		employeeService.save(theEmployee);
		
		// use redirect to prevent duplicate submissions
		return "redirect:/employees/list";
	}
	
	// == show update form ==
	@GetMapping("/updateForm")
	public String updateForm(@RequestParam("employeeId") int theId,
			Model theModel) {
		
		Employee theEmployee = employeeService.findById(theId);
		
		theModel.addAttribute("employee", theEmployee);
		
		return "employees/employee-form";
	}
	
	// == delete employee ==
	@GetMapping("/delete")
	public String deleteEmployee(@RequestParam("employeeId") int theId,
			Model theModel) {
		
		employeeService.deleteById(theId);
		
		return "redirect:/employees/list";
	}
	
	// == search employee ==
	@GetMapping("/search")
	public String delete(@RequestParam("employeeName") String theName,
						 Model theModel) {
		
		List<Employee> theEmployees = employeeService.searchBy(theName);

		theModel.addAttribute("employees", theEmployees);
		
		return "/employees/list-employees";
		
	}
	
}
