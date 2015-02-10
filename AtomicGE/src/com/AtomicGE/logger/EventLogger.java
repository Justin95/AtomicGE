package com.AtomicGE.logger;

import java.io.PrintStream;
import java.util.ArrayList;

public class EventLogger {
	
	private ArrayList<Event> events;
	
	protected EventLogger(){
		this.events = new ArrayList<Event>();
	}
	
	public void addEvent(Class c, String info){
		Event event = new Event(c,info);
		this.events.add(event);
	}
	
	
	protected void printLog(PrintStream stream){
		stream.println("Log Report: \n");
		for(Event e : this.events){
			stream.println(e);
		}
	}
	
	
	
}
