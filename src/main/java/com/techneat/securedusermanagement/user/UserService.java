package com.techneat.securedusermanagement.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.techneat.securedusermanagement.exception.UserAvailableException;
import com.techneat.securedusermanagement.userregistration.RegistrationRequest;
import com.techneat.securedusermanagement.userregistration.token.VerificationToken;
import com.techneat.securedusermanagement.userregistration.token.VerificationTokenRepository;


import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor 
public class UserService implements UserServiceInterface {

	private final UserRepository userrepository ;
	private final PasswordEncoder passwordencoder ;
	private final VerificationTokenRepository verificationtokenrepository;
	
	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return userrepository.findAll();
	}

	@Override
	public Optional<User> findByMail(String useremail) {
		// TODO Auto-generated method stub
		return userrepository.findByEmail(useremail);
	}

	@Override
	public User registeredUser(RegistrationRequest request) {
		// TODO Auto-generated method stub
		Optional<User> User=this.findByMail(request.email());
		if(User.isPresent()) {
			throw new UserAvailableException("User Email named "+request.email()+"is already available");
		}
		
		var newUser=new User();
		newUser.setFirstName(request.firstName());
		newUser.setLastName(request.lastName());
		newUser.setPassword(passwordencoder.encode(request.password()));
		newUser.setRole(request.role());
		newUser.setEmail(request.email());
		return userrepository.save(newUser);
	}

	@Override
	public void saveUserVerficationtoken(User theUSer, String verificationToken) {
		// TODO Auto-generated method stub
		var verficationToken= new VerificationToken(verificationToken,theUSer);
		verificationtokenrepository.save(verficationToken);
	}

}
