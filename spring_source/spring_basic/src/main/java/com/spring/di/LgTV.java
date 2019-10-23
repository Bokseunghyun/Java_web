package com.spring.di;

public class LgTV implements TV{

	/*
	 * private AppleSpeaker speaker;
	 * 
	 * public void setSpeaker(AppleSpeaker speaker) { this.speaker = speaker; }
	 */
	
	@Override
	public void powerOn() {
		System.out.println("LGTv - 전원 ON");
		
	}

	@Override
	public void powerOff() {
		System.out.println("LGTv - 전원 OFF");
	}

	@Override
	public void volumeUp() {
		System.out.println("LGTv - Sound Up");
		//speaker.volumeUp();
	}

	@Override
	public void volumeDown() {
		System.out.println("LGTv - Sound Down");
		//speaker.volumeDown();
	}
	
}
