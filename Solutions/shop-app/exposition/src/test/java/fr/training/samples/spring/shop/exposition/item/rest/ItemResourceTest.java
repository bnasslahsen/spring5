
package fr.training.samples.spring.shop.exposition.item.rest;


import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Unit Test exemple with mocked server
 */
@SpringBootTest
@AutoConfigureMockMvc
public class ItemResourceTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getAllItems() throws Exception {
		mockMvc.perform(get("/api/items"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("itemID")));
	}
}
