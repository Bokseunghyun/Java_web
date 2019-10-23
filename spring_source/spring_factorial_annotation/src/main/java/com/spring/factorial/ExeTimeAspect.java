package com.spring.factorial;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class ExeTimeAspect {
	//factorial()를 실행하기 전 후로 동작하여 실행시간을 재기 위한 메소드
	
	//factorial 패키지와 하위 패키지의 모든 메소드에 적용
	@Around(value="execution(* com.spring.factorial..*(..))")
	public Object measure(ProceedingJoinPoint pjp) {
		long start=System.nanoTime();
		Object obj = null;
		try {
			obj=pjp.proceed(); //factorial() 호출
		} catch (Throwable e) {
			e.printStackTrace();
		}finally {
			long end = System.nanoTime();
			System.out.println("걸린 시간 : " + (end-start));
		}
		
		return obj;
	}

}
