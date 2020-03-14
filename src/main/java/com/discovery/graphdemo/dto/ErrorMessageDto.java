package com.discovery.graphdemo.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ErrorMessageDto {
	public static ErrorMessageDto newInstance() {
		return new ErrorMessageDto();
	}

	String error;
	String message;
	String path;
	int status;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.sss a zzz", timezone = "America/New_York")
	Date timestamp;

	private ErrorMessageDto() {

	}

	public String getError() {
		return error;
	}

	public String getMessage() {
		return message;
	}

	public String getPath() {
		return path;
	}

	public int getStatus() {
		return status;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public ErrorMessageDto setError(final String error) {
		this.error = error;
		return this;
	}

	public ErrorMessageDto setMessage(final String message) {
		this.message = message;
		return this;
	}

	public ErrorMessageDto setPath(final String path) {
		this.path = path;
		return this;
	}

	public ErrorMessageDto setStatus(final int status) {
		this.status = status;
		return this;
	}

	public ErrorMessageDto setTimestamp(final Date timestamp) {
		this.timestamp = timestamp;
		return this;
	}

	@Override
	public String toString() {
		return "ErrorMessageDto [error=" + error + ", message=" + message + ", path=" + path + ", status=" + status
				+ ", timestamp=" + timestamp + "]";
	}

}
