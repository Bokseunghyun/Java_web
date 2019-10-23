package com.spring.domain;

import lombok.Data;

@Data
public class AuthInfo {
	//로그인 성공시 userid,name 담을 객체
	private String userid;
	private String name;
}
