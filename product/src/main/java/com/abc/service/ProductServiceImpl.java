package com.abc.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.entity.Product;
import com.abc.exception.DataNotFoundException;
import com.abc.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();

	}

	@Override
	public Product findById(Integer pid) {
		return productRepository.findById(pid).orElse(new Product());

	}

	@Override
	public int updateProduct(String pname, int pid) {
		int updateProduct = productRepository.updateProduct(pname, pid);
		if (updateProduct > 0) {
			return 1;
		} else {
			return 0;
		}

	}

	@Override
	public void deleteProduct(int pid) {
		productRepository.deleteById(pid);
	}

	@Override
	public Product addProduct(Product product) {
		return productRepository.save(product);
	}

}
