package com.exam.helper;

public class UserFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserFoundException() {
		super("User Not Found on Server!!");
	}
	
	public UserFoundException(String message) {
		super(message);
	}
}
