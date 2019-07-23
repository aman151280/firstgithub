package com.example.demo.IBusiness;

import java.util.List;

import com.example.demo.entity.Employee;
import com.example.demo.vo.EmployeeDetails;

public interface IEmployeeBusiness {

	public Employee saveUser(EmployeeDetails employee);
	
	public List<EmployeeDetails> getAllEmployees();
	
	public EmployeeDetails findEmployeeById(Long id);
}
