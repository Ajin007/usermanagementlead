package com.techneat.securedusermanagement.userregistration.token;


import java.util.Calendar;
import java.util.Date;

import com.techneat.securedusermanagement.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class VerificationToken {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String token;
	private Date experationtime;
	private static final int EXPARATION_TIME = 10;
	
	
	@OneToOne
	@JoinColumn(name="user_id")
	private User user;
	
	public VerificationToken(String token,User user){
		// super();
		this.token=token;
		this.user=user;
		this.experationtime=this.getTokenExperationTime();
	}
	
	public VerificationToken(String token) {
		// super();
		this.token = token;
		this.experationtime=this.getTokenExperationTime();
	}

	private Date getTokenExperationTime() {
		// TODO Auto-generated method stub
		Calendar calendar= Calendar.getInstance();
		calendar.setTimeInMillis(new Date().getTime());
		calendar.add(calendar.MINUTE, EXPARATION_TIME);
		
	//	return new Date (calendar.getTimeInMillis());

	return new Date(calendar.getTime().getTime());
	}

}
