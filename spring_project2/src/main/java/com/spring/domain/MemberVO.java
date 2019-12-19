package com.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class MemberVO {
	private String userid;
	private String userpw;
	private String confirm_pw;
	private String username;
	private String gender;
	private String email;

	
	
	// 비밀번호 , 비밀번호 재입력이 일치하는지 체크 true false
	public boolean userpwequalconfirm_pw() {

		return userpw.equals(confirm_pw);
	}
}

