package com.abc.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.abc.entity.Product;
import com.abc.service.ProductService;
import com.abc.service.ProductServiceTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = ProductController.class)
public class ProductControllerTest {

	@MockBean
	private ProductService productService;

	@InjectMocks
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

		OngoingStubbing<List<Product>> thenReturn = when(productService.getAllProducts()).thenReturn(list);

		ResultActions resultActions = mockMvc.perform(
				get("/products").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(list)))
				.andExpect(status().isOk());

		String contentAsString = resultActions.andReturn().getResponse().getContentAsString();

		// System.out.println(contentAsString);

		Assertions.assertTrue(contentAsString.length() > 0);

		verify(productService, times(1)).getAllProducts();
		verifyNoMoreInteractions(productService);

	}

	@Test
	public void getAllProductsTest_Not_Found() throws JsonProcessingException, Exception {


		OngoingStubbing<List<Product>> thenReturn = when(productService.getAllProducts()).thenReturn(null);

		ResultActions resultActions = mockMvc.perform(
				get("/products"))
				.andExpect(status().isConflict());

		String contentAsString = resultActions.andReturn().getResponse().getContentAsString();

		 System.out.println(contentAsString);

		Assertions.assertTrue(contentAsString.length() > 0);

		verify(productService, times(1)).getAllProducts();
		verifyNoMoreInteractions(productService);

	}

	@Test
	public void findByIdTest() throws JsonProcessingException, Exception {
		Product product = new Product(1, "abc", 100, "26-05-1996", 25);

		when(productService.findById(Mockito.anyInt())).thenReturn(product);

		ResultActions resultActions = mockMvc.perform(get("/product/{pid}", 1).contentType(MediaType.APPLICATION_JSON)
				.contentType(objectMapper.writeValueAsString(product))).andExpect(status().isOk());
		String string = resultActions.andReturn().getResponse().getContentAsString();
		// System.out.println(string);
		// Assertions.assertTrue(string.contentEquals(string));

		verify(productService, times(1)).findById(1);
		verifyNoMoreInteractions(productService);
	}

	@Test
	public void findByIdTest_Fail_Not_Found() throws Exception {
//		Product product = new Product(1, "abc", 100, "26-05-1996", 25);

		when(productService.findById(Mockito.anyInt())).thenReturn(null);
		mockMvc.perform(get("/product/{pid}", 1)).andExpect(status().isConflict());

		verify(productService, times(1)).findById(1);
		verifyNoMoreInteractions(productService);
	}

	@Test
	public void AddProductTest() throws JsonProcessingException, Exception {
		Product product = new Product(1, "abc", 100, "26-05-1996", 55);

		when(productService.addProduct(Mockito.any(Product.class))).thenReturn(product);

		ResultActions resultActions = mockMvc.perform(post("/addproduct").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(product))).andExpect(status().isCreated());

		String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
		System.out.println(contentAsString);

		Assertions.assertTrue(contentAsString.length() > 0);

		verify(productService, times(1)).addProduct(product);
		verifyNoMoreInteractions(productService);

	}

	@Test
	public void updateProductTest() throws Exception {

		Product product = new Product(1, "abc", 55, "22-05-1996", 50);

		when(productService.updateProduct(Mockito.anyString(), Mockito.anyInt())).thenReturn(1);

		ResultActions resultActions = mockMvc.perform(put("/updateproduct/{pname}/{pid}", "abc", 1)
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(product)))
				.andExpect(status().isOk());
		String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
		System.out.println(contentAsString);

		verify(productService, times(1)).updateProduct(product.getPname(), product.getPid());
		verifyNoMoreInteractions(productService);

		// Assertions.assertTrue(contentAsString.length() > 0);
	}

	@Test
	public void updateProductTest_Fail_NotFound() throws Exception {
		when(productService.updateProduct(Mockito.anyString(), Mockito.anyInt())).thenReturn(0);

		ResultActions resultActions = mockMvc
				.perform(put("/updateproduct/{pname}/{pid}", "abc", 1).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
		System.out.println(contentAsString);

		// verify(productService, times(1)).updateProduct("abc", 1);
		// verifyNoMoreInteractions(productService);
	}

	@Test
	public void deleteProductByIdTest() throws JsonProcessingException, Exception {

		Product product = new Product(1, "Abc", 55, "26-051996", 50);

//		when(productService.deleteProduct(Mockito.anyInt()));

		ResultActions resultActions = mockMvc.perform(delete("/deleteproduct/{pid}", 1)
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(product)))
				.andExpect(status().isOk());

		String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
		System.out.println(contentAsString);

//		verify(productService, times(1)).deleteProduct(1);
//		verifyNoMoreInteractions(productService);

	}

}
