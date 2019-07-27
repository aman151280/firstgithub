package com.employee.demo.IDao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.demo.entity.Employee;


@Repository
public interface IEmployeeDomain extends JpaRepository<Employee, Long> {

}
