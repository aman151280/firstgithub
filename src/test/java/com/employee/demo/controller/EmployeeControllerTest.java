/**
 * 
 */
package com.employee.demo.controller;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.employee.demo.IBusiness.IEmployeeBusiness;
import com.employee.demo.entity.Employee;
import com.employee.demo.vo.EmployeeDetails;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Aman
 *
 */

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private IEmployeeBusiness service;

	@Autowired
	private ObjectMapper objectMapper;

	private JacksonTester<EmployeeDetails> jsonTester;

	private EmployeeDetails employeeDetails;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		JacksonTester.initFields(this, objectMapper);
		employeeDetails = new EmployeeDetails();
		employeeDetails.setUserName("alex");
	}

	/**
	 * Test method for {@link com.example.demo.controller.FirstController#test()}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testTest() throws Exception {

		mvc.perform(get("/api/hi").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

	}

	/**
	 * Test method for
	 * {@link com.example.demo.controller.FirstController#saveUser(com.example.demo.vo.EmployeeDetails)}.
	 * 
	 * @throws Exception
	 */
@Test
	public void testSaveUser() throws Exception {
		final String employeeDetailsJson = jsonTester.write(employeeDetails).getJson();
		Employee alex = mock(Employee.class);
		alex.setUserName("alex");

		when(service.saveUser(mock(EmployeeDetails.class))).thenReturn(alex);
		mvc.perform(post("/api/saveUser").content(employeeDetailsJson).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
		// .andExpect(jsonPath("$[0].userName", is(alex.getUserName())));
	}

	/**
	 * Test method for
	 * {@link com.example.demo.controller.FirstController#getAllEmployees()}.
	 * @throws Exception 
	 */
	@Test
	public void testGetAllEmployees() throws Exception {
		//fail("Not yet implemented");
		EmployeeDetails alex = new EmployeeDetails();
		alex.setUserName("alex");
		 
		    List<EmployeeDetails> allEmployees = Arrays.asList(alex);
		 
		    when(service.getAllEmployees()).thenReturn(allEmployees);
		 
		    mvc.perform(get("/api/employees")
		      .contentType(MediaType.APPLICATION_JSON))
		      .andExpect(status().isOk())
		      //.andExpect(jsonPath(jsonPath("$", hasItem(allEmployees).))
		      .andExpect(jsonPath("$[0].userName", is(alex.getUserName())));
	}

	/**
	 * Test method for
	 * {@link com.example.demo.controller.FirstController#getEmployeeById(java.lang.Long)}.
	 */
	@Test
	public void testGetEmployeeById() {
		//fail("Not yet implemented");
	}

}
