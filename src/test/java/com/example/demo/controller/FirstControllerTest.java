/**
 * 
 */
package com.example.demo.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.demo.IBusiness.IEmployeeBusiness;
import com.example.demo.entity.Employee;
import com.example.demo.vo.EmployeeDetails;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Aman
 *
 */

@RunWith(SpringRunner.class)
@WebMvcTest(FirstController.class)
public class FirstControllerTest {

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
		List<EmployeeDetails> list = new ArrayList<EmployeeDetails>();
		EmployeeDetails employeeDetails = new EmployeeDetails();
		employeeDetails.setUserName("alex");
		list.add(employeeDetails);
		when(service.getAllEmployees()).thenReturn(list);
		MockHttpServletRequestBuilder httpServletRequestBuilder = MockMvcRequestBuilders.get("/api/employees");
		ResultActions resultActions = mvc.perform(httpServletRequestBuilder.accept(MediaType.APPLICATION_JSON));
		ResultActions actions = resultActions.andDo(print());
		ResultActions actions2 = actions.andExpect(status().isOk());
		ResultActions actions3 = actions2.andExpect(MockMvcResultMatchers.jsonPath("$").exists());
		actions3.andExpect(MockMvcResultMatchers.jsonPath("$[0].userName",is(employeeDetails.getUserName())));
	}

	/**
	 * Test method for
	 * {@link com.example.demo.controller.FirstController#getEmployeeById(java.lang.Long)}.
	 * @throws Exception 
	 */
	@Test
	public void testGetEmployeeById() throws Exception {
		EmployeeDetails employeeDetails = new EmployeeDetails();
		employeeDetails.setUserName("alex");
		when(service.findEmployeeById(Mockito.anyLong())).thenReturn(employeeDetails);
		MockHttpServletRequestBuilder httpServletRequestBuilder = MockMvcRequestBuilders.get("/api/employees/{id}",1);
		ResultActions resultActions = mvc.perform(httpServletRequestBuilder.accept(MediaType.APPLICATION_JSON));
		ResultActions actions = resultActions.andDo(print());
		ResultActions actions2 = actions.andExpect(status().isOk());
		ResultActions actions3 = actions2.andExpect(MockMvcResultMatchers.jsonPath("$").exists());
		actions3.andExpect(MockMvcResultMatchers.jsonPath("$.userName",is(employeeDetails.getUserName())));
	}

}
