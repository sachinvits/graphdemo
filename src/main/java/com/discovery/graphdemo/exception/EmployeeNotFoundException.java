package com.discovery.graphdemo.exception;

public class EmployeeNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmployeeNotFoundException(final String message) {
		super(message);
	}
}
