package com.spring.domain;

import lombok.Data;

@Data
public class ChangeVO {
	private String userid;
	private String current_pw;
	private String confirm_pw;
	private String new_pw;
	
	public boolean newpwequalconfirmpw() {
		
		return new_pw.equals(confirm_pw);
	}
}
