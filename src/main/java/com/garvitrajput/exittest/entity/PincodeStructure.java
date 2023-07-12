package com.garvitrajput.exittest.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "pincodes")
public class PincodeStructure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pincodeid;

    @Column(nullable = false)
    private String pinCode;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "productid", referencedColumnName = "productId")
    private ProductStructure product;

    @Column(nullable = false)
    private String deliveryDays;

    public int getPincodeId() {
        return pincodeid;
    }

    public void setPincodeId(int pincodeId) {
        this.pincodeid = pincodeId;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public ProductStructure getProduct() {
        return product;
    }

    public void setProduct(ProductStructure product) {
        this.product = product;
    }

    public String getDeliveryDays() {
        return deliveryDays;
    }

    public void setDeliveryDays(String deliveryDays) {
        this.deliveryDays = deliveryDays;
    }

	public PincodeStructure() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PincodeStructure(int pincodeId, String pinCode, ProductStructure product, String deliveryDays) {
		super();
		this.pincodeid = pincodeId;
		this.pinCode = pinCode;
		this.product = product;
		this.deliveryDays = deliveryDays;
	}

    
}
