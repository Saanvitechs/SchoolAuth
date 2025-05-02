package com.erp.school.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
	private String jwt;
	private String error;

	private String role;

	private String name;

	private String empId;



}
