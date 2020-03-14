/**
 *
 */
package com.discovery.graphdemo.dto;

/**
 *
 *
 */
public class AddEmployeeResponseDto {
	int empId;
	String message;

	public int getEmpId() {
		return empId;
	}

	public String getMessage() {
		return message;
	}

	public void setEmpId(final int empId) {
		this.empId = empId;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "CreateResponseDto [empId=" + empId + ", message=" + message + "]";
	}

}
