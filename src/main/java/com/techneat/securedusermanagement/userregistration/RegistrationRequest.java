package com.techneat.securedusermanagement.userregistration;


public record RegistrationRequest(
		 String firstName,
		String lastName,
		
		String email,
		String password,
		String role) {

}
