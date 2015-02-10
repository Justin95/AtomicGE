package com.AtomicGE.sound;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public class Sound {
	
	public static final String SOUND_FOLDER_LOCATION = "sounds//";
	public static final String FILE_EXTENSION = ".wav";
	
	private AudioInputStream stream;
	private AudioFormat format;
	private DataLine.Info info;
	private Clip clip;
	private SoundThread thread;
	
	protected Sound(String fileName){
		 File soundFile = new File(SOUND_FOLDER_LOCATION + fileName + FILE_EXTENSION);
		 try{
		 	this.stream = AudioSystem.getAudioInputStream(soundFile);
		 	this.format = stream.getFormat();
		 	this.info = new DataLine.Info(Clip.class, format);
			this.clip = (Clip) AudioSystem.getLine(info);
		 }catch(Exception e){
			System.out.println("Error loading sound: " + fileName);
			e.printStackTrace();
		 }
		 
	}
	
	
	/**
	 * Plays the sound associated with this Sound object.
	 */
	protected void play(){
		if(this.thread != null) stop();
		this.thread = new SoundThread(this.clip, this.stream);
		thread.start();
	}
	
	
	
	/**
	 * Stops the Sound playing
	 */
	protected void stop(){
		if(this.thread == null) return;
		this.thread.killThread();
	}
	
}
