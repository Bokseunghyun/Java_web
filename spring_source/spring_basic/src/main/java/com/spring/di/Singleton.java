package com.spring.di;

public class Singleton {
	private Singleton() {}
	private static Singleton singleton;

	public Singleton getInstance() {
		
		if(singleton==null)
		{
			singleton = new Singleton();
		}
		
		return singleton;
	}
}
