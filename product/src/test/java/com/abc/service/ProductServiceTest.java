package com.abc.service;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.abc.entity.Product;
import com.abc.repository.ProductRepository;

@ExtendWith(value = MockitoExtension.class)
public class ProductServiceTest {
	
	@MockBean
	private ProductService productService;
	
	@MockBean
	private ProductRepository productRepository;

	@Test
	public void getAllProductsTest() {
		
		List<Product> list = Arrays.asList(new Product(1, "abc", 100, "22-02-1996", 100));
		
		OngoingStubbing<List<Product>> thenReturn = when(productService.getAllProducts()).thenReturn(list);
		
		System.out.println(thenReturn);
		
	}
}
