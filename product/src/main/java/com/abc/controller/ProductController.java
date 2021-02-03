package com.abc.controller;

import java.sql.SQLException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abc.entity.Product;
import com.abc.exception.ProductsNotFoundException;
import com.abc.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@CrossOrigin

public class ProductController {

	@Autowired
	private ProductService productService;

	@Operation(description = "Get all list of products")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Found Products"),
			@ApiResponse(responseCode = "404", description = "products not found") })
	@GetMapping(path = "/products", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.TEXT_PLAIN_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<List<Product>> getAllProducts() throws SQLException {
		List<Product> products = productService.getAllProducts();
		if (products == null || products.isEmpty()) {
			throw new ProductsNotFoundException("No Products Found");
		}
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	@Operation(description = "Get a product by its id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Found Products"),
			@ApiResponse(responseCode = "404", description = "products not found"),
			@ApiResponse(responseCode = "400", description = "invalid id supplied") })
	@GetMapping(path = "product/{pid}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.TEXT_PLAIN_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<Product> findById(@PathVariable("pid") int pid) throws SQLException {
		Product product = productService.findById(pid);
		if (product.getPid() == null) {
			throw new ProductsNotFoundException("No product found with pid: " + pid);
		}
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	@Operation(description = "update a product by its id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = " Product is updated"),
			@ApiResponse(responseCode = "404", description = "products not found") })
	@PutMapping(path = "/updateproduct/{pname}/{pid}", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.TEXT_PLAIN_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<Product> updateProduct(@PathVariable("pname") String pname, @PathVariable("pid") Integer pid)
			throws SQLException {
		productService.updateProduct(pname, pid);
		return new ResponseEntity<Product>(HttpStatus.OK);
	}

	@Operation(description = "delete a product by its id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "product deleted"),
			@ApiResponse(responseCode = "404", description = "products not found") })
	@DeleteMapping("/deleteproduct/{pid}")
	public void deleteProduct(@PathVariable("pid") Integer pid) throws SQLException {
		productService.deleteProduct(pid);
	}

	@PostMapping("/addproduct")
	public ResponseEntity<Product> addProduct(@Valid @RequestBody Product product) throws SQLException {
		Product addProduct = productService.addProduct(product);
		return new ResponseEntity<Product>(addProduct, HttpStatus.CREATED);
	}

}
