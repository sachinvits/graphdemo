/**
 *
 */
package com.discovery.graphdemo.dto;

/**
 *
 *
 */
public class DeleteEmployeeResponseDto {
	String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "DeleteEmployeeResponseDto [message=" + message + "]";
	}

}
