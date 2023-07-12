package com.garvitrajput.exittest.controllers;

import com.garvitrajput.exittest.dao.PincodeDAO;
import com.garvitrajput.exittest.dao.ProductDAO;
import com.garvitrajput.exittest.entity.PincodeStructure;
import com.garvitrajput.exittest.entity.ProductStructure;
import com.garvitrajput.exittest.exceptions.ProductNotFoundException;
import com.garvitrajput.exittest.services.ProductServices;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

	private ProductDAO productDAO;
	private PincodeDAO pincodeDAO;
	private ProductServices productServices;

	@Autowired
	public ProductController(ProductDAO productDAO, PincodeDAO pincodeDAO, ProductServices productServices) {
		this.productDAO = productDAO;
		this.pincodeDAO = pincodeDAO;
		this.productServices = productServices;
	}

	@GetMapping
	public List<ProductStructure> searchProducts(@RequestParam(required = false) String name,
			@RequestParam(required = false) String productCode, @RequestParam(required = false) String brand) {
		return productServices.searchProducts(name, productCode, brand);
	}

	@GetMapping("/{productId}")
	public ProductStructure getProductDetails(@PathVariable int productId) throws ProductNotFoundException {
		return productServices.getProductById(productId);
	}

	@GetMapping("/{productId}/price")
	public double getProductPrice(@PathVariable int productId) throws ProductNotFoundException {
		return productServices.getProductPriceById(productId);
	}

	@GetMapping("/{productId}/delivery")
	public ResponseEntity<String> getProductDeliveryDays(@PathVariable int productId, @RequestParam String pincode) {
	    try {
	        @SuppressWarnings("unused")
			ProductStructure product = productDAO.findById(productId)
	                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + productId));

	        PincodeStructure pincodeInfo = pincodeDAO.findByPinCodeAndProduct_ProductId(pincode, productId);

	        if (pincodeInfo != null) {
	            String deliveryDays = pincodeInfo.getDeliveryDays();
	            if (deliveryDays != null) {
	                return ResponseEntity.status(HttpStatus.OK).body(deliveryDays);
	            } else {
	                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	            }
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
	        }
	    } catch (ProductNotFoundException e) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).build();
	    }
	}


	@PostMapping("/addProduct")
	public ResponseEntity<String> addProduct(@Valid @RequestBody ProductStructure productRequest) throws IOException {
		ProductStructure product = new ProductStructure();
		product.setProductName(productRequest.getProductName());
		product.setBrand(productRequest.getBrand());
		product.setDescription(productRequest.getDescription());
		product.setProductCode(productServices.generateProductCode());
		product.setImage(productRequest.getImage());
		product.setPrice(productRequest.getPrice());

		// Save the product in the database
		ProductStructure savedProduct = productDAO.save(product);

		if (savedProduct != null) {
		    return ResponseEntity.ok("Product added successfully");
		} else {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add product");
		}
	}

}