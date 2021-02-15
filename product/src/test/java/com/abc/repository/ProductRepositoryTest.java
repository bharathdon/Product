package com.abc.repository;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.abc.entity.Product;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ExtendWith(SpringExtension.class)
@Transactional
public class ProductRepositoryTest {

	@Autowired
	private TestEntityManager testEntityManager;

	@MockBean
	private ProductRepository productRepository;

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	public void saveProductTest() {
		Product product = new Product(1, "Abc", 100, "22-05-96", 500);

		when(productRepository.save(product)).thenReturn(product);

		ProductRepository productRepository2 = applicationContext.getBean(productRepository.getClass());
		Product product2 = productRepository2.save(product);

		Assertions.assertTrue(product2.equals(product));
	}

	@Test
	public void getAllProducts() {

		List<Product> list = Arrays.asList(new Product(1, "abc", 100, "22-05-1996", 555),
				new Product(2, "Def", 666, "22-05-1996", 8887));

		when(productRepository.findAll()).thenReturn(list);

		ProductRepository productRepository2 = applicationContext.getBean(productRepository.getClass());
		List<Product> list2 = productRepository2.findAll();

		Assertions.assertTrue(list2.equals(list));

	}

	@Test
	public void getProductByIdTest() {

		Optional<Product> product = Optional.of(new Product(1, "abc", 555, "23-11-2021", 350));

		when(productRepository.findById(Mockito.anyInt())).thenReturn(product);

		ProductRepository productRepository2 = applicationContext.getBean(productRepository.getClass());
		Optional<Product> id = productRepository2.findById(1);

		Assertions.assertTrue(id.equals(product));

	}

	@Test
	public void updateProductTest() {

		Product product = new Product(1, "abc", 222, "26-11-2021", 588);

		when(productRepository.updateProduct(Mockito.anyString(), Mockito.anyInt())).thenReturn(1);

		ProductRepository productRepository2 = applicationContext.getBean(productRepository.getClass());
		int product2 = productRepository2.updateProduct(product.getPname(), product.getPid());

		Assertions.assertTrue(product2 > 0);

	}

}
