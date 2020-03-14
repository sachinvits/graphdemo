package com.discovery.graphdemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class GraphDbException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public GraphDbException(final String message) {
		super(message);
	}

	public GraphDbException(final String message, final Throwable e) {
		super(message, e);
	}
}
