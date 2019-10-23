package com.spring.factorial;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class FactMain {
	public static void main(String[] args) {
		AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext.xml");
		
		Calculator cal = (Calculator)factory.getBean("fact");
		cal.factorial(10);
		System.out.println();
		
		cal = (Calculator)factory.getBean("rec");
		cal.factorial(10);
		
		factory.close();
	}
}
