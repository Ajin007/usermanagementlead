package com.techneat.securedusermanagement.userregistration;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

import com.techneat.securedusermanagement.event.RegistrationCompleteEvent;
import com.techneat.securedusermanagement.user.User;
import com.techneat.securedusermanagement.user.UserService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

/*
 * ApplicationEvent is used over here 
 * 
 */

@RestController
@RequestMapping("/register")
//@RequiredArgsConstructor
public class RegistrationController {

	private final UserService userservice = null;
	
	//Directly injected event for publishing
	private ApplicationEventPublisher publisher;
	
	@PostMapping
	public String registeredUsers( RegistrationRequest registrationrequest,final HttpServletRequest request) {
		User user=userservice.registeredUser(registrationrequest);
		//publish registration event
		publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
		return "Success !, check you registered mail to activate the Email further ";
		
	}

	
	//method to pass the url as planned
	private String applicationUrl(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
	}
	
}
