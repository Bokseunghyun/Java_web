package com.spring.domain;

import lombok.Data;

@Data
public class Memberinfo {
	//로그인 성공시 userid,username을 담음
	private String userpw;
	private String userid;
}
