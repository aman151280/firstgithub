package com.employee.demo.IBusiness;

import java.util.List;

import com.employee.demo.entity.Employee;
import com.employee.demo.vo.EmployeeDetails;



public interface IEmployeeBusiness {

	public Employee saveUser(EmployeeDetails employee);
	
	public List<EmployeeDetails> getAllEmployees();
	
	public EmployeeDetails findEmployeeById(Long id);
}
