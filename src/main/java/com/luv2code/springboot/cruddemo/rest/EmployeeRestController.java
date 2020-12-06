package com.luv2code.springboot.cruddemo.rest;

import java.util.List;

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
import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;

//@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	private EmployeeService employeeService;
	
	@Autowired
	public EmployeeRestController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@GetMapping("/employees")
	public List<Employee> findAll(){
		return employeeService.findAll();
	}
	
	@GetMapping("/employees/{empId}")
	public ResponseEntity getEmployee(@PathVariable int empId) {
		Employee emp = employeeService.findById(empId);
		if(emp==null)
			return new ResponseEntity<>("Employee not found", HttpStatus.OK);
		return new ResponseEntity<Employee>(emp,HttpStatus.OK);
	}
	
	@PostMapping("/employees")
	public Employee save(@RequestBody Employee emp) {
		emp.setId(0);
		employeeService.save(emp);
		return emp;
	}
	
	@PutMapping("/employees")
	public Employee update(@RequestBody Employee emp) {
		employeeService.save(emp);
		return emp;
	}
	
	@DeleteMapping("/employees/{employeeId}")
	public String delete(@PathVariable int employeeId) {
		employeeService.deleteById(employeeId);
		return "Employee Deleted with id - "+employeeId;
	}
}
