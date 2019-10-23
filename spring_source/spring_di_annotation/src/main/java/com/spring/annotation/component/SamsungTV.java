package com.spring.annotation.component;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("tv2")
public class SamsungTV implements TV {
	//@Autowired
//	@Inject
//	@Qualifier("britz")
	@Resource(name = "britz")
	
	private Speaker speaker;
	
	public SamsungTV() {
		System.out.println("SamsungTV 객체 생성");
		
	}

	@Override
	public void powerOn() {
		System.out.println("SamsungTV - 전원 ON ");
	}

	@Override
	public void powerOff() {
		System.out.println("SamsungTV - 전원 OFF");
	}

	@Override
	public void volumeUp() {
		//System.out.println("SamsungTV - volume Up");
		speaker.volumeUp();
	}

	
	@Override
	public void volumeDown() {
		//System.out.println("SamsungTV - volume Down");
		speaker.volumeDown();
	}
	
}
