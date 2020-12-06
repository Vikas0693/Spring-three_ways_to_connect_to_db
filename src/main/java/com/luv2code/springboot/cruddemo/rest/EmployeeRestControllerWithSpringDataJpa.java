package com.luv2code.springboot.cruddemo.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springboot.cruddemo.dao.EmployeeDAO;
import com.luv2code.springboot.cruddemo.dao.EmployeeRepository;
import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;

@RestController
@RequestMapping("/api/spring-data-jpa")
public class EmployeeRestControllerWithSpringDataJpa {

	private EmployeeRepository employeeRepository;
	
	@Autowired
	public EmployeeRestControllerWithSpringDataJpa(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	@GetMapping("/employees")
	public List<Employee> findAll(){
		return employeeRepository.findAll();
	}
	
	@GetMapping("/employees/{empId}")
	public ResponseEntity getEmployee(@PathVariable int empId) {
		Optional<Employee> result =employeeRepository.findById(empId);
		if(result.isPresent())
			return new ResponseEntity<Employee>(result.get(),HttpStatus.OK);
		else
			return new ResponseEntity<String>("Did not find employee with id - "+empId,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping("/employees")
	public Employee save(@RequestBody Employee emp) {
		emp.setId(0);
		employeeRepository.save(emp);
		return emp;
	}
	
	@PutMapping("/employees")
	public ResponseEntity update(@RequestBody Employee emp) {
		boolean employeeExists = employeeRepository.existsById(emp.getId());
		if(employeeExists)
			emp = employeeRepository.save(emp);
		return new ResponseEntity(employeeExists ? emp : "No employee exists to update",HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/employees/{employeeId}")
	public String delete(@PathVariable int employeeId) {
		employeeRepository.deleteById(employeeId);
		return "Employee Deleted with id - "+employeeId;
	}
}
