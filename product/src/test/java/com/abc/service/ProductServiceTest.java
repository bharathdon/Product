package com.abc.service;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.abc.entity.Product;
import com.abc.repository.ProductRepository;

@ExtendWith(value = MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class ProductServiceTest {

	@MockBean
	private ProductService productService;

	@MockBean
	private ProductRepository productRepository;

	@Test
	void getAllProductsTest() {

		List<Product> list = Arrays.asList(new Product(1, "abc", 100, "22-02-1996", 100),
				new Product(2, "def", 100, "20-05-1996", 25));
		when(productService.getAllProducts()).thenReturn(list);

		Assertions.assertEquals(list, productService.getAllProducts());
	}

	@Test
	void findByIdTest() {
		Product product = new Product(2, "def", 888, "04-08-96", 5000);
		when(productService.findById(Mockito.anyInt())).thenReturn(product);

		Assertions.assertEquals(product, productService.findById(2));

	}

	@Test
	void updateProductTest() {
		when(productService.updateProduct(Mockito.anyString(), Mockito.anyInt())).thenReturn(1);

		Assertions.assertEquals(1, productService.updateProduct("ac", 1));

	}

	
	//how to write test case for void return types
	@Test
	void deleteProductTest() {
		Product product = new Product(3, "abc", 444, "05-05-95", 100);

		doNothing().when(productService).deleteProduct(Mockito.anyInt());

		verify(productService, times(1)).deleteProduct(3);
	}

}
