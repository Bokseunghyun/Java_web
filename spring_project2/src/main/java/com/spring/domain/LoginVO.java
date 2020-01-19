package com.spring.domain;

import lombok.Data;

@Data
public class LoginVO {
	private String userid;
	private String current_pw;

	
	private String confirm_pw;
	private String new_pw;
	
}
