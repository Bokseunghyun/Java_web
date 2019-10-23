package com.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component("log")
@Aspect
public class LogAdvice {
	@Before(value = "execution(* com.spring.aop.Product.getInfo())")
	public void beforeLog() {
		System.out.println("[공통로그] 비즈니스 로직 수행 전 호출");
	}
	
	@After(value = "execution(* com.spring.aop.Product.getInfo())")
	public void afterLog() {
		System.out.println("[공통로그] 비즈니스 로직 수행 후 예외여부와 상관없이 호출");
	}
	
	@AfterThrowing(value = "execution(* com.spring.aop.Product.getInfo())")
	public void afterThrowingLog() {
		System.out.println("[공통로그] 비즈니스 로직 수행 시 예외발생 시 호출");
	}
	
	@AfterReturning(value = "execution(* com.spring.aop.Product.getInfo())")
	public void afterReturnLog() {
		System.out.println("[공통로그] 비즈니스 로직 수행  정상종료 시 호출");
	}
	
	@Around(value = "execution(* com.spring.aop.Product.getInfo())")
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
