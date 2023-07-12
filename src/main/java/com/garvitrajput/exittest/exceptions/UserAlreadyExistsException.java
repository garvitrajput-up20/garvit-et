package com.garvitrajput.exittest.exceptions;

public class UserAlreadyExistsException extends Exception{

	private static final long serialVersionUID = 232649334109383751L;

	public UserAlreadyExistsException(String msg) {
		super(msg);
	}
	
}
