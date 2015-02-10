package com.AtomicGE.sound;



public class SoundPlayer {
	
	
	
	public void playSound(Sound sound){
		sound.play();
	}
	
	
	/**
	 * Shuts down this SoundPlayer, stopping any playing music
	 */
	public void shutDown(){
		for(Sound sound : Sounds.ALL_SOUNDS){
			sound.stop();
		}
	}
	
	
	
	
	
	
}
