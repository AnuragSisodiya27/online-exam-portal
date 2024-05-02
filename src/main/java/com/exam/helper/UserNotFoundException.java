package com.exam.helper;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserNotFoundException() {
		super("User Not Found on Server!!");
	}
	
	public UserNotFoundException(String message) {
		super(message);
	}
}
