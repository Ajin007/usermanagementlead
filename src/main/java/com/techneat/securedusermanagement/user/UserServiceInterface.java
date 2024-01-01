package com.techneat.securedusermanagement.user;

import java.util.List;
import java.util.Optional;

import com.techneat.securedusermanagement.userregistration.RegistrationRequest;

public interface UserServiceInterface {

	List<User> getUsers();
	Optional<User> findByMail(String useremail);
	User registeredUser(RegistrationRequest Request);
	public void saveUserVerficationtoken(User theUSer,String verificationToken);
}
