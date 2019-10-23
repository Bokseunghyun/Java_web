package com.spring.di;

public class SamsungTV implements TV {

	
	 private AppleSpeaker speaker;
	 private int price;
	 
	 public SamsungTV(AppleSpeaker speaker, int price) {
		 super();
		 this.speaker = speaker; 
		 this.price = price;
	 }
	 
	
	@Override
	public void powerOn() {
		System.out.println("SamsungTV - 전원 ON" + price);
	}
	

	@Override
	public void powerOff() {
		System.out.println("SamsungTV - 전원 OFF");
	}

	@Override
	public void volumeUp() {
		System.out.println("SamsungTV - volume Up");
		//speaker.volumeUp();
	}

	@Override
	public void volumeDown() {
		System.out.println("SamsungTV - volume Down");
		//speaker.volumeDown();
	}
}
