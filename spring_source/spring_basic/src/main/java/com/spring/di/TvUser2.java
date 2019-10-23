package com.spring.di;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TvUser2 {
	public static void main(String[] args) {
	//TV tv = new LgTV();
	//TV tv = new SamsungTV(new AppleSpeaker());
	AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext2.xml");
		
		//AppleSpeaker speaker = new AppleSpeaker();
	TV tv = (SamsungTV)factory.getBean("tv2");
	//tv.setSpeaker(speaker);
	tv.powerOn();
	tv.volumeUp();
	tv.volumeDown();
	tv.powerOff();
	
	//컨테이너 종료
	factory.close();
	
	
	}
}
