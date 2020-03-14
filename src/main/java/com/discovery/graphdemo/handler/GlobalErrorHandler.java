package com.discovery.graphdemo.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.discovery.graphdemo.dto.ErrorMessageDto;
import com.discovery.graphdemo.exception.BadRequestException;
import com.discovery.graphdemo.exception.GraphDbException;

@ControllerAdvice
public class GlobalErrorHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(BadRequestException.class)
	public final ResponseEntity<ErrorMessageDto> handleBadRequestException(final BadRequestException ex,
			final WebRequest request) {
		final ErrorMessageDto messageDto = ErrorMessageDto.newInstance()//
				.setError(HttpStatus.BAD_REQUEST.getReasonPhrase())//
				.setMessage(ex.getMessage())//
				.setPath(request.getDescription(true))//
				.setStatus(HttpStatus.BAD_REQUEST.value())//
				.setTimestamp(new Date());//

		return new ResponseEntity<>(messageDto, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(GraphDbException.class)
	public final ResponseEntity<ErrorMessageDto> handleGraphDbException(final GraphDbException ex,
			final WebRequest request) {
		final ErrorMessageDto messageDto = ErrorMessageDto.newInstance()//
				.setError(ex.getMessage())//
				.setMessage(ex.getCause().getMessage())//
				.setPath(request.getDescription(true))//
				.setStatus(HttpStatus.BAD_REQUEST.value())//
				.setTimestamp(new Date());//

		return new ResponseEntity<>(messageDto, HttpStatus.BAD_REQUEST);
	}
}
