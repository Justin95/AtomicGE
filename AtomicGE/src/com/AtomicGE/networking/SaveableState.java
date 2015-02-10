package com.AtomicGE.networking;

public interface SaveableState {
	
	
	/**
	 * Creates a PacketStream containing all the information which has changed about the game state
	 * since the last time this method was called.
	 * @return a PacketStream describing the changes
	 */
	public PacketStream getDeltaStream();
	
	
	/**
	 * Creates a PacketStream containing all the information in this game state. Used for keeping clients
	 * synchronized and can also be used to save the game to a file.
	 * @return
	 */
	public PacketStream getKeyFrameStream();
	
	
	
	/**
	 * Updates this SaveableState from a PacketStream describing the changes since the previous PacketStream
	 * arrived.
	 * @param delta A PacketStream describing the changes
	 */
	public void updateFromDelta(PacketStream delta);
	
	
	/**
	 * Updates this SavableState from a PacketStream describing the entire SaveableState.
	 * @param keyFrame A PacketStream describing the entire SaveableState
	 */
	public void updateFromKeyFrame(PacketStream keyFrame);
	
	
	
}
