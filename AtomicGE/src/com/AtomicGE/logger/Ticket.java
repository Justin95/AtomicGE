package com.AtomicGE.logger;


/**
 * 
 * @author Justin
 * 
 *
 */
public class Ticket {
	
	private long startTime;
	private Class c;
	private Thread thread;
	private String note;
	private long livedTime;
	
	protected Ticket(Class c, Thread thread, String note){
		startTime = System.nanoTime();
		this.c = c;
		this.thread = thread;
		this.note = note;
	}
	
	
	/**
	 * Adds a new note to this Ticket
	 * @param newNote the new String message to be associated with this Ticket
	 */
	protected void addNote(String newNote){
		this.note = this.note + " Closeing Note: " + newNote;
	}
	
	
	/**
	 * Stops the clock of this ticket
	 */
	protected void endTicket(){
		long endTime = System.nanoTime();
		this.livedTime = endTime - startTime;
	}
	
	
	protected Class getTicketClass(){
		return this.c;
	}
	
	protected Thread getTicketThread(){
		return this.thread;
	}
	
	protected long getTime(){
		return this.livedTime;
	}
	
	protected String getNote(){
		return this.note;
	}
	
}
