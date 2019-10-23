package com.spring.aop;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class Product {
	private String company;
	private String pname;
	private String price;
	
	
	public void getInfo() throws Exception {
		
		System.out.println("회사명 : " +company);
		System.out.println("제품명 : " + pname);
		System.out.println("가격 : " + price);
		
		//throw new Exception("예외발생");
	}
	
	
}
