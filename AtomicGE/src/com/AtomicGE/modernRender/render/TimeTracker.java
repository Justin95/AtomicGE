package com.AtomicGE.modernRender.render;

public class TimeTracker {
	
	private long lastFrameTime;
	
	TimeTracker(){
		this.lastFrameTime = System.nanoTime();
	}
	
	/**
	 * Calculates the change in time (seconds) since this method was last called.
	 * @return the delta time since this was last called
	 */
	public double getDeltaTime(){
		long currentTime = System.nanoTime();
		long nanoDelta = currentTime - lastFrameTime;
		this.lastFrameTime = currentTime;
		double delta = nanoDelta / 1000000000.0; //convert to seconds from nanoseconds by dividing by one billion
		return delta;
	}
	
	
	
}
