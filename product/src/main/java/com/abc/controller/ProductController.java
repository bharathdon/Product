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
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@CrossOrigin(allowedHeaders = "*")

public class ProductController {

	@Autowired
	private ProductService productService;

	@Operation(summary = "Get all list of products")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Found Products", content = @Content),
			@ApiResponse(responseCode = "404", description = "products not found", content = @Content) })
	@GetMapping(path = "/products")
	public ResponseEntity<List<Product>> getAllProducts() throws SQLException {
		List<Product> products = productService.getAllProducts();
		if (products == null || products.isEmpty()) {
			throw new ProductsNotFoundException("No Products Found");
		}
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	@Operation(summary = "Get a product by its id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Found Products", content = @Content),
			@ApiResponse(responseCode = "404", description = "products not found", content = @Content),
			@ApiResponse(responseCode = "400", description = "invalid id supplied", content = @Content) })
	@GetMapping(path = "product/{pid}")
	public ResponseEntity<Product> findById(@Parameter(description = "id of the product") @PathVariable("pid") int pid)
			throws SQLException {
		Product product = productService.findById(pid);
		if (product.getPid() == null) {
			throw new ProductsNotFoundException("No product found with pid: " + pid);
		}
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	@Operation(summary = "update a product by its id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = " Product is updated", content = @Content),
			@ApiResponse(responseCode = "404", description = "products not found", content = @Content) })
	@PutMapping(path = "/updateproduct/{pname}/{pid}", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.TEXT_PLAIN_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<Product> updateProduct(
			@Parameter(description = "name of product") @PathVariable("pname") String pname,
			@Parameter(description = "id of a product") @PathVariable("pid") Integer pid) throws SQLException {
		productService.updateProduct(pname, pid);
		return new ResponseEntity<Product>(HttpStatus.OK);
	}

	@Operation(summary = "delete a product by its id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "product deleted", content = @Content),
			@ApiResponse(responseCode = "404", description = "products not found", content = @Content) })
	@DeleteMapping("/deleteproduct/{pid}")
	public void deleteProduct(@Parameter(description = "id of the product") @PathVariable("pid") Integer pid)
			throws SQLException {
		productService.deleteProduct(pid);
	}

	@PostMapping("/addproduct")
	public ResponseEntity<Product> addProduct(@Valid @RequestBody Product product) throws SQLException {
		Product addProduct = productService.addProduct(product);
		return new ResponseEntity<Product>(addProduct, HttpStatus.CREATED);
	}

}
