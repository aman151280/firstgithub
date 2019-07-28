package com.employee.demo.business;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.employee.demo.IBusiness.IEmployeeBusiness;
import com.employee.demo.IDao.IEmployeeDomain;
import com.employee.demo.entity.Employee;
import com.employee.demo.vo.EmployeeDetails;

@RunWith(SpringRunner.class)
public class EmployeeBusinessImplTest {

	@TestConfiguration
	static class EmployeeBusinessImplTestContextConfiguration {

		@Bean
		public IEmployeeBusiness employeeService() {
			return new EmployeeBusinessImpl();
		}
	}

	@Autowired
	private IEmployeeBusiness iEmployeeBusiness;
	@MockBean
	IEmployeeDomain iEmployeeDomain;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSaveUser() {
		EmployeeDetails details   = new EmployeeDetails();
		details.setUserName("alex");
		Employee alex1 = new Employee();
		alex1.setUserName("alex");
		when(iEmployeeDomain.save(alex1)).thenReturn(alex1);
		Employee alex2 = iEmployeeBusiness.saveUser(details);
		//assertNotNull(alex2);
		
	}

	@Test
	public void testGetAllEmployees() {
		Employee alex = new Employee();
		alex.setUserName("alex");
		List<Employee> empList = new ArrayList<Employee>();
		empList.add(alex);
		when(iEmployeeDomain.findAll()).thenReturn(empList);
		List<EmployeeDetails> empDetails = iEmployeeBusiness.getAllEmployees();
		assertEquals(empDetails.size(), empList.size());
	}

	@Test
	public void testFindEmployeeById() {
		Employee alex = new Employee();
		alex.setUserName("alex");
		when(iEmployeeDomain.getOne(1L)).thenReturn(alex);
		
		EmployeeDetails details = iEmployeeBusiness.findEmployeeById(1L);
		 assertThat(details.getUserName())
	      .isEqualTo(alex.getUserName());
	}

}
