package com.EngineTest.game;

import com.AtomicGE.mathUtil.Vector;
import com.AtomicGE.mathUtil.VectorMath;

public class Player {
	
	private Vector pos;
	private Vector rot;
	public static final double MAX_SPEED = 5.0; // measured in meters/second
	public static final double MAX_ROT_SPEED = 30.0;
	private Controller control;
	
	
	public Player(Vector pos, Vector rot){
		this.pos = pos;
		this.rot = rot;
		this.control = new Controller();
	}
	
	
	/**
	 * Updates the position and rotation of the player according to received inputs.
	 */
	public void update(){
		this.rot = VectorMath.addVectors(this.rot, control.getDeltaRot());
		this.pos = VectorMath.addVectors(this.pos, control.getDeltaPos(this.rot));
	}
	
	
	/**
	 * 
	 * @return the position vector of this player
	 */
	public Vector getPosition(){
		return this.pos;
	}
	
	/**
	 * 
	 * @return the rotation vector of this player
	 */
	public Vector getRotation(){
		return this.rot;
	}
	
}
