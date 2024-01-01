package com.techneat.securedusermanagement.event.listener;

import java.util.UUID;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.techneat.securedusermanagement.event.RegistrationCompleteEvent;
import com.techneat.securedusermanagement.user.User;
import com.techneat.securedusermanagement.user.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/*
 * Listener is implemented with the interface ApplicationListener
 * Steps to be followed in the application listener
 * 1. Get newly registered users
 * 2. Create verfication token for the user
 * 3. Save the verification token
 * 4. Build the verfication url to sen to the user
 * 5. send the email finally----> done using the slf4j 
 */

@Component
@RequiredArgsConstructor
@Slf4j
public  class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

	private final UserService userservice;
	
	@Override
	public void onApplicationEvent(RegistrationCompleteEvent event) {
		// TODO Auto-generated method stub
		User theUser=event.getUser();
		String verificationtoken=UUID.randomUUID().toString();
		
		userservice.saveUserVerficationtoken(theUser, verificationtoken);;
		
		String url=event.getApplicationUrl()+"/register/verifyEmail?token="+verificationtoken;
		
		log.info("click the link to verify your registration : {} ",url);
	}
;	
}
