package com.abc.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.abc.entity.Product;
import com.abc.service.ProductServiceTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = ProductController.class)
public class ProductControllerTest {

	@MockBean
	private ProductServiceTest productService;

	@MockBean
	private ProductController productController;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getAllProductsTest() throws JsonProcessingException, Exception {

		Product product = new Product(1, "abcd", 10, "22-05-1996", 10);
		Product product2 = new Product(2, "abcd", 55, "22-09-1996", 100);
		List<Product> list = Arrays.asList(product, product2);

		ResultActions resultActions = mockMvc.perform(
				get("/products").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(list)))
				.andExpect(status().isOk());

		String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
		System.out.println(contentAsString.length());

	}

	@Test
	public void findByIdTest() throws JsonProcessingException, Exception {
		Product product = new Product(1, "abc", 100, "26-05-1996", 25);

		ResultActions resultActions = mockMvc.perform(get("/product/{pid}", 1).contentType(MediaType.APPLICATION_JSON)
				.contentType(objectMapper.writeValueAsString(product))).andExpect(status().isOk());
		String string = resultActions.andReturn().getResponse().getContentAsString();
		System.out.println(string.length());
		 Assertions.assertTrue(string.length() > 0);
	}

	@Test
	public void AddProductTest() throws JsonProcessingException, Exception {
		Product product = new Product(1, "abc", 100, "26-05-1996", 55);

		ResultActions resultActions = mockMvc.perform(post("/addproduct").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(product))).andExpect(status().isOk());

		String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
		System.out.println(contentAsString);

		Assertions.assertTrue(contentAsString.length() > 0);

	}

}
