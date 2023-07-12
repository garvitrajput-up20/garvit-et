package com.garvitrajput.exittest.exceptions;

public class ProductNotFoundException  extends Exception{
	
	private static final long serialVersionUID = 232649334109383751L;

	public ProductNotFoundException(String msg) {
		super(msg);
	}

}
