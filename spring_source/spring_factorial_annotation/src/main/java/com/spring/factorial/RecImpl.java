package com.spring.factorial;

import org.springframework.stereotype.Component;

@Component("rec")
public class RecImpl implements Calculator {

	@Override
	public long factorial(long num) {
		
		//재귀호출을 이용한 factorial 구하기
		if(num==0) {
			return 1;
		}else {
			return num*factorial(num-1);
		}
		
	}

}
