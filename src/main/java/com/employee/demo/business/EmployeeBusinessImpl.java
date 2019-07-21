package com.employee.demo.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.demo.IBusiness.IEmployeeBusiness;
import com.employee.demo.IDao.IEmployeeDomain;
import com.employee.demo.entity.Employee;
import com.employee.demo.vo.EmployeeDetails;


@Service
public class EmployeeBusinessImpl implements IEmployeeBusiness {
	@Autowired
	IEmployeeDomain IEmployeeDomain;

	@Override
	public Employee saveUser(EmployeeDetails emp) {
		// TODO Auto-generated method stub

		Employee employee = new Employee();
		BeanUtils.copyProperties(emp, employee);
		return IEmployeeDomain.save(employee);
	}

	@Override
	public List<EmployeeDetails> getAllEmployees() {
		List<Employee> empList = IEmployeeDomain.findAll();
		List<EmployeeDetails> empDetailsList = new ArrayList<EmployeeDetails>();
		empList.forEach(emp -> {
			EmployeeDetails details = new EmployeeDetails();
			BeanUtils.copyProperties(emp, details);
			empDetailsList.add(details);
		});

		return empDetailsList;
	}

	@Override
	public EmployeeDetails findEmployeeById(Long id) {
		Optional<Employee> emp = IEmployeeDomain.findById(id);
			EmployeeDetails details = new EmployeeDetails();
			BeanUtils.copyProperties(emp.isPresent()?emp.get():null, details);
		return details;
	}

}
