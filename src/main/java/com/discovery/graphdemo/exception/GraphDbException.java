package com.discovery.graphdemo.exception;

public class GraphDbException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public GraphDbException(final String message) {
		super(message);
	}

	public GraphDbException(final String message, final Throwable e) {
		super(message, e);
	}
}
