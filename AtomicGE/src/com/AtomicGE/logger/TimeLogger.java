package com.AtomicGE.logger;

public class TimeLogger {
	
	
	
	
	public Ticket checkIn(Class c, Thread thread, String note){
		Ticket ticket = new Ticket(c, thread, note);
		return ticket;
	}
	
	
	public void checkOut(Ticket ticket, String note){
		ticket.addNote(note);
		Logger.logReport.addTicket(ticket);
	}
	
	
	
}
