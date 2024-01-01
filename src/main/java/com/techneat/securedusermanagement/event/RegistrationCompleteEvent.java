package com.techneat.securedusermanagement.event;

import org.springframework.context.ApplicationEvent;

import com.techneat.securedusermanagement.user.User;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RegistrationCompleteEvent extends ApplicationEvent {
	
	private User user;
	private String applicationUrl;

	public RegistrationCompleteEvent(User source,String applicationUrl) {
		super(source);
		this.user=source;
		this.applicationUrl=applicationUrl;
		
		// TODO Auto-generated constructor stub
	}

}
