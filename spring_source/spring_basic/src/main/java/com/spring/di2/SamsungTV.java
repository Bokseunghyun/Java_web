package com.spring.di2;

public class SamsungTV implements TV {
	private Speaker speaker;
	private int price;
	
	public SamsungTV() {
		System.out.println("SamsungTV 객체 생성");
		
	}

	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	/* (non-Javadoc)
	 * @see com.spring.di2.TV#powerOn()
	 */
	@Override
	public void powerOn() {
		System.out.println("SamsungTV - 전원 ON " + price);
	}
	

	/* (non-Javadoc)
	 * @see com.spring.di2.TV#powerOff()
	 */
	@Override
	public void powerOff() {
		System.out.println("SamsungTV - 전원 OFF");
	}

	/* (non-Javadoc)
	 * @see com.spring.di2.TV#volumeUp()
	 */
	@Override
	public void volumeUp() {
		//System.out.println("SamsungTV - volume Up");
		speaker.volumeUp();
	}

	/* (non-Javadoc)
	 * @see com.spring.di2.TV#volumeDown()
	 */
	@Override
	public void volumeDown() {
		//System.out.println("SamsungTV - volume Down");
		speaker.volumeDown();
	}
	
}
