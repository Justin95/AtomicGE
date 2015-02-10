package com.AtomicGE.sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;

public class SoundThread extends Thread{
	
	private Clip soundClip;
	private AudioInputStream stream;
	private boolean keepRunning;
	
	SoundThread(Clip soundClip, AudioInputStream stream){
		this.soundClip = soundClip;
		this.stream = stream;
		this.keepRunning = true;
	}
	
	public void run(){
		try{
			soundClip.open(stream);
			soundClip.start();
			long startTime = System.nanoTime();
			long sleepTimeNano = soundClip.getMicrosecondLength() * 1000;
			long endTime = startTime + sleepTimeNano;
			while(System.nanoTime() < endTime && keepRunning){
				Thread.sleep(2);
			}
		}catch(Exception e){
			System.out.println("Error playing Sound");
			e.printStackTrace();
		}finally{
			soundClip.close();
		}
		return;
	}
	
	
	/**
	 * Kills this SoundThread within 2 milliseconds, stopping the sound playing.
	 */
	public void killThread(){
		this.keepRunning = false;
	}
	
	
	
}
