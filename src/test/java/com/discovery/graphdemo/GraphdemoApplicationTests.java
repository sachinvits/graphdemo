package com.discovery.graphdemo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class GraphdemoApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testGetAllEmployees() throws Exception {
		final MvcResult result = this.mockMvc.perform(get("/v1/employee/all"))//
				.andDo(print())//
				.andExpect(status().isOk())//
				.andReturn();//
	}

}
