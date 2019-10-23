package com.spring.aop;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class ProductClient {
	public static void main(String[] args) throws Exception {
		AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext.xml");
		Product product = (Product)factory.getBean("product");
		
		product.setCompany("LEGO");
		product.setPname("LEGO블럭");
		product.setPrice("150000");
		product.getInfo();
		
		factory.close();
	}
}
