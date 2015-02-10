package com.AtomicGE.logger;

public class Event {
	
	private Class c;
	private String info;
	
	
	protected Event(Class c, String info){
		this.c = c;
		this.info = info;
	}
	
	public String toString(){
		return "Class: " + c.getSimpleName() + "   INFO: " + this.info;
	}
	
	
}
