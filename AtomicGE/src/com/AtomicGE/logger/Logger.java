package com.AtomicGE.logger;

import java.io.PrintStream;

public class Logger {
	
	public static final EventLogger eventLog = new EventLogger();
	
	public static final TimeLogger  timeLog  = new TimeLogger(); 
	
	protected static final LogReport logReport = new LogReport();
	
	
	
	
	public static void displayLogReport(PrintStream stream){
		
	}
	
	
	public static void clearLog(){
		
	}
	
}
