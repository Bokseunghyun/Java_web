package com.spring.factorial;

import org.springframework.stereotype.Component;

@Component("fact")
public class FactImpl implements Calculator{

	@Override
	public long factorial(long num) {
		//for문을 이용한 factorial 구하기
		
		long sum=1;
		for(long i=1; i<=num; i++)
		{
			sum = i*sum;	
			
		}
			
		return sum;
	}

}
