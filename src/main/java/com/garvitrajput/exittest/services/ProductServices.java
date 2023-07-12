package com.garvitrajput.exittest.services;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.garvitrajput.exittest.dao.PincodeDAO;
import com.garvitrajput.exittest.dao.ProductDAO;
import com.garvitrajput.exittest.entity.ProductStructure;
import com.garvitrajput.exittest.exceptions.ProductNotFoundException;

import java.util.List;

@Service
public class ProductServices {

	private ProductDAO productDAO;

	@Autowired
	public void ProductService(ProductDAO productDAO, PincodeDAO pincodeDAO) {
		this.productDAO = productDAO;
	}

	public ProductStructure addProduct(ProductStructure product) {
		// Generate product code
		String productCode = generateProductCode();

		// Set the generated product code
		product.setProductCode(productCode);

		return productDAO.save(product);
	}
	
	public String generateProductCode() {
        String alphanumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        // Generate a 10-character productCode
        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(alphanumeric.length());
            sb.append(alphanumeric.charAt(index));
        }

        return sb.toString();
    }

	public List<ProductStructure> searchProducts(String name, String productCode, String brand){
		if (name != null && productCode != null && brand != null) {
			return productDAO.findByProductNameAndProductCodeAndBrand(name, productCode, brand);
		} else if (name != null && productCode != null) {
			return productDAO.findByProductNameAndProductCode(name, productCode);
		} else if (name != null && brand != null) {
			return productDAO.findByProductNameAndBrand(name, brand);
		} else if (productCode != null && brand != null) {
			return productDAO.findByProductCodeAndBrand(productCode, brand);
		} else if (name != null) {
			return productDAO.findByProductName(name);
		} else if (productCode != null) {
			return productDAO.findByProductCode(productCode);
		} else if (brand != null) {
			return productDAO.findByBrand(brand);
		} else {
			return productDAO.findAll();
		}
	}

	public ProductStructure getProductById(int productId) throws ProductNotFoundException {
		return productDAO.findById(productId)
				.orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + productId));
	}

	public double getProductPriceById(int productId) throws ProductNotFoundException {
		ProductStructure product = getProductById(productId);
		return product.getPrice();
	}
}