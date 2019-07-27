package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.IBusiness.IEmployeeBusiness;
import com.example.demo.entity.Employee;
import com.example.demo.vo.EmployeeDetails;

@RestController
@RequestMapping("/api")
@CrossOrigin(allowedHeaders={"*", "*/"}, origins={"*", "*/"})
public class FirstController {
	private static final Logger LOGGER = LoggerFactory.getLogger(FirstController.class);
@Autowired
IEmployeeBusiness iEmployeeBusiness;

	@GetMapping(value = "/hi")
	public ResponseEntity<String> test() {
		LOGGER.debug("hiiiiiiiiiiiiiiiiiiiiiiii");
		return new ResponseEntity<String>("test",HttpStatus.OK);
	}

	
	@PostMapping(value = "/saveUser", consumes = "application/json",
            produces = "application/json")
	public ResponseEntity<Employee> saveUser(@RequestBody EmployeeDetails employeeDetails) {
		Employee emp = iEmployeeBusiness.saveUser(employeeDetails);
		LOGGER.debug("hiiiiiiiiiiiiSuccessiiiiiiiiiiii");
		return new ResponseEntity<Employee>(emp, HttpStatus.CREATED);
	}
	
    @GetMapping(value = "/employees")
	public ResponseEntity<List<EmployeeDetails>>  getAllEmployees(){
    	LOGGER.debug("getAllEmployees==============================");
		return new ResponseEntity<List<EmployeeDetails>>(iEmployeeBusiness.getAllEmployees(),HttpStatus.OK);
		
	}
    
    @GetMapping(value = "/employees/{id}")
   	public EmployeeDetails getEmployeeById(@PathVariable (name = "id") Long id){
    	LOGGER.debug("hiiiiiiiiiiiiSuccessfindEmployeeByIdiiiiiiiiiiii");
   		return iEmployeeBusiness.findEmployeeById(id);
   		
   	}
	
}
