package com.abc.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abc.entity.Product;
import com.abc.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping(path = "/getAll", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.TEXT_PLAIN_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> products = productService.getAllProducts();
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	@GetMapping(path = "getById/{pid}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.TEXT_PLAIN_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<Product> findById(@PathVariable("pid") int pid) {
		Product findById = productService.findById(pid);
		return new ResponseEntity<Product>(findById, HttpStatus.OK);
	}

	@PutMapping(path = "/update/{pname}/{pid}", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.TEXT_PLAIN_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<Product> updateProduct(@PathVariable("pname") String pname,
			@PathVariable("pid") Integer pid) {
		productService.updateProduct(pname, pid);
		return new ResponseEntity<Product>(HttpStatus.OK);
	}

	@DeleteMapping("/delete/{pid}")
	public void deleteProduct(@PathVariable("pid") Integer pid) {
		productService.deleteProduct(pid);
	}

	@PostMapping("/addproduct")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		Product addProduct = productService.addProduct(product);
		return new ResponseEntity<Product>(addProduct, HttpStatus.CREATED);
	}

}
