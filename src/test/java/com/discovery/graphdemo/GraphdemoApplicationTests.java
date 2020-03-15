package com.discovery.graphdemo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.discovery.graphdemo.dto.DeleteEmployeeResponseDto;
import com.discovery.graphdemo.dto.EmployeeRequestDto;
import com.discovery.graphdemo.dto.EmployeeResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
class GraphdemoApplicationTests {

	private static final Integer EMP_ID_99999 = 99999;

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private MockMvc mockMvc;

	@AfterEach
	public void cleanup() throws Exception {
		final MvcResult result = this.mockMvc.perform(delete("/v1/employee/delete/" + EMP_ID_99999.toString()))//
				.andDo(print())//
				.andExpect(status().isOk())//
				.andReturn();//
	}

	@BeforeEach
	public void setup() throws Exception {
		final EmployeeRequestDto requestDto1 = new EmployeeRequestDto();
		requestDto1.setEmpId(EMP_ID_99999);
		requestDto1.setName("Sam");

		this.mockMvc.perform(post("/v1/employee/add-employee")//
				.content(mapper.writeValueAsString(requestDto1))//
				.contentType(MediaType.APPLICATION_JSON)//
				.accept(MediaType.APPLICATION_JSON))//
				.andDo(print())//
				.andExpect(status().isOk())//
				.andReturn();//
	}

	@Test
	public void testDeleteEmployee() throws Exception {
		final MvcResult result = this.mockMvc.perform(delete("/v1/employee/delete/" + EMP_ID_99999.toString()))//
				.andDo(print())//
				.andExpect(status().isOk())//
				.andReturn();//

		final DeleteEmployeeResponseDto response = mapper.readValue(result.getResponse().getContentAsString(),
				DeleteEmployeeResponseDto.class);
		assertThat(response.getMessage()).contains(EMP_ID_99999.toString());

		final MvcResult result1 = this.mockMvc.perform(get("/v1/employee/all"))//
				.andDo(print())//
				.andExpect(status().isOk())//
				.andReturn();//

		final EmployeeResponseDto response1 = mapper.readValue(result1.getResponse().getContentAsString(),
				EmployeeResponseDto.class);

		assertThat(response1.getEmployees()).isEmpty();

	}

	@Test
	public void testGetAllEmployees() throws Exception {
		final MvcResult result = this.mockMvc.perform(get("/v1/employee/all"))//
				.andDo(print())//
				.andExpect(status().isOk())//
				.andReturn();//

		final EmployeeResponseDto response = mapper.readValue(result.getResponse().getContentAsString(),
				EmployeeResponseDto.class);

		assertThat(response.getEmployees()).isNotEmpty();
		assertThat(response.getEmployees()).hasSize(1);
		assertThat(response.getEmployees().get(0).getName()).isEqualTo("Sam");
	}

	@Test
	public void testGetEmployees() throws Exception {
		final MvcResult result = this.mockMvc.perform(get("/v1/employee/" + EMP_ID_99999.toString()))//
				.andDo(print())//
				.andExpect(status().isOk())//
				.andReturn();//
		final EmployeeResponseDto response = mapper.readValue(result.getResponse().getContentAsString(),
				EmployeeResponseDto.class);

		assertThat(response.getEmployees()).isNotEmpty();
		assertThat(response.getEmployees().get(0).getName()).isEqualTo("Sam");
	}

	@Test
	public void testUpdateEmployee() throws Exception {
		final EmployeeRequestDto requestDto1 = new EmployeeRequestDto();
		requestDto1.setEmpId(EMP_ID_99999);
		requestDto1.setName("Sachin");

		this.mockMvc.perform(put("/v1/employee/update-employee")//
				.content(mapper.writeValueAsString(requestDto1))//
				.contentType(MediaType.APPLICATION_JSON)//
				.accept(MediaType.APPLICATION_JSON))//
				.andDo(print())//
				.andExpect(status().isOk())//
				.andReturn();//

		final MvcResult result = this.mockMvc.perform(get("/v1/employee/all"))//
				.andDo(print())//
				.andExpect(status().isOk())//
				.andReturn();//

		final EmployeeResponseDto response = mapper.readValue(result.getResponse().getContentAsString(),
				EmployeeResponseDto.class);

		assertThat(response.getEmployees()).isNotEmpty();
		assertThat(response.getEmployees().get(0).getName()).isEqualTo("Sachin");
	}

}
