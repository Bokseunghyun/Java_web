package com.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

@Component("log")
public class LogAdvice {
	public void beforeLog() {
		System.out.println("[공통로그] 비즈니스 로직 수행 전 호출");
	}
	
	public void afterLog() {
		System.out.println("[공통로그] 비즈니스 로직 수행 후 예외여부와 상관없이 호출");
	}
	public void afterThrowingLog() {
		System.out.println("[공통로그] 비즈니스 로직 수행 시 예외발생 시 호출");
	}
	public void afterReturnLog() {
		System.out.println("[공통로그] 비즈니스 로직 수행  정상종료 시 호출");
	}
	
	public void aroundLog(ProceedingJoinPoint pjp) {
		
		//비지니스 메소드 호출
		try {
			pjp.proceed(); //getinfo메소드를  호출할 수 있게 해줌
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.out.println("[공통로그] 비즈니스 로직 수행 전 후 호출");
	}
}
