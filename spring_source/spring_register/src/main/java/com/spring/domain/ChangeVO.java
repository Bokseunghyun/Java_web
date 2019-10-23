package com.spring.domain;

import lombok.Data;

@Data
public class ChangeVO {
	private String current_password;
	private String confirm_password;
	private String new_password;
	
	
	public boolean isNewPasswordToConfirmPassword() {
		return new_password.equals(confirm_password);
	}
}
