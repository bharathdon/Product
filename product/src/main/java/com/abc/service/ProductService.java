package com.abc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;

import com.abc.entity.Product;

public interface ProductService {

	public List<Product> getAllProducts();

	public Product findById(Integer pid);

	public int updateProduct(String pname,int pid);
	
	public void deleteProduct(int pid);
	
	public Product addProduct(Product product);

}