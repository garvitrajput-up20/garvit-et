package com.garvitrajput.exittest.entity;

import java.util.Arrays;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class ProductStructure {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;

	@Column(nullable = false)
	private String productName;

	@Column(nullable = false)
	private String brand;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.UUID)
	private String productCode;

	@Lob 
	@Column(name = "image", columnDefinition = "MEDIUMBLOB")
	private byte[] image;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<PincodeStructure> pincodes;

	@Column(nullable = false)
	private double price;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public ProductStructure(int productId, String productName, String brand, String description, String productCode,
			byte[] image, double price) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.brand = brand;
		this.description = description;
		this.productCode = productCode;
		this.image = image;
		this.price = price;
	}

	public ProductStructure() {
		super();
		// TODO Auto-generated constructor stub

	}
	@Override
	public String toString() {
		return "ProductStructure [productId=" + productId + ", productName=" + productName + ", brand=" + brand
				+ ", description=" + description + ", productCode=" + productCode + ", image=" + Arrays.toString(image)
				+ ", price=" + price + "]";
	}
}