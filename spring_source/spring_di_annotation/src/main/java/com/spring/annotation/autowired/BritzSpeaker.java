package com.spring.annotation.autowired;

public class BritzSpeaker implements Speaker{

	public BritzSpeaker() {
		System.out.println("BritzSpeaker 객체 생성");
	}
	@Override
	public void volumeUp() {
		System.out.println("BritzSpeaker Volume Up");
	}

	@Override
	public void volumeDown() {
		System.out.println("BritzSpeaker Volume Down");
	}

}
