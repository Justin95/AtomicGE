package com.AtomicGE.game;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import com.AtomicGE.mathUtil.Vector;
import com.AtomicGE.mathUtil.VectorMath;

public class Controller {
	
	public static final int FORWARDS  = Keyboard.KEY_W;
	public static final int RIGHT     = Keyboard.KEY_D;
	public static final int LEFT      = Keyboard.KEY_A;
	public static final int BACKWARDS = Keyboard.KEY_S;
	public static final int UP        = Keyboard.KEY_Q;
	public static final int DOWN      = Keyboard.KEY_Z;
	
	//Vectors use same coordinate scheme as openGL, I think
	public static final Vector FORWARDS_VECTOR  = new Vector( 0, 0,-1);
	public static final Vector BACKWARDS_VECTOR = new Vector( 0, 0, 1);
	public static final Vector RIGHT_VECTOR     = new Vector( 1, 0, 0);
	public static final Vector LEFT_VECTOR      = new Vector(-1, 0, 0);
	public static final Vector UP_VECTOR        = new Vector( 0, 1, 0);
	public static final Vector DOWN_VECTOR      = new Vector( 0,-1, 0);
	
	//booleans describing if each key is down
	private boolean forwards_down  = false;
	private boolean backwards_down = false;
	private boolean right_down     = false;
	private boolean left_down      = false;
	private boolean up_down        = false;
	private boolean down_down      = false;
	
	private long lastPosTime;
	private long lastRotTime;
	
	Controller(){
		this.lastPosTime = System.nanoTime();
		this.lastRotTime = System.nanoTime();
	}
	
	/**
	 * Calculates the CHANGE in position according to the received inputs since the last time this method was called.
	 * @return the DELTA position
	 */
	public Vector getDeltaPos(Vector currentRot){
		long currentTime = System.nanoTime();
		double deltaTime = (currentTime - lastPosTime) / 1000000000.0; //time delta since last method call in seconds
		this.lastPosTime = currentTime;
		while(Keyboard.next()){
			handleInput(Keyboard.getEventKey(),Keyboard.getEventKeyState());
		}
		double multiplier = deltaTime * Player.MAX_SPEED;
		Vector deltaPos = getDeltaVector();
		deltaPos = VectorMath.multiply(deltaPos,multiplier);
		deltaPos = accountForRotation(deltaPos,currentRot);
		return deltaPos;
	}
	
	/**
	 * Rotates the vector v1 by vector rot, rot is assumed to describe a rotation.
	 * @param v1 The vector to rotate
	 * @param rot The vector to rotate v1 by
	 * @return a new vector which is v1 rotated by rot
	 */
	private Vector accountForRotation(Vector v1, Vector rot){
		return v1;//VectorMath.rotateVector(v1, rot);
	}
	
	
	/**
	 * Updates the boolean key_down variables according to whether or not they are down
	 * @param key the key to handle
	 * @param keyDown whether the key is down or not
	 */
	private void handleInput(int key,boolean keyDown){
		switch(key){
		case FORWARDS:
			this.forwards_down  = keyDown;
			break;
		case BACKWARDS:
			this.backwards_down = keyDown;
			break;
		case RIGHT:
			this.right_down     = keyDown;
			break;
		case LEFT:
			this.left_down      = keyDown;
			break;
		case UP:
			this.up_down        = keyDown;
			break;
		case DOWN:
			this.down_down      = keyDown;
			break;
		}
	}
	
	/**
	 * Calculates the delta position vector from the down input keys.
	 * @return a new vector calculated from the currently down keys
	 */
	private Vector getDeltaVector(){
		Vector v = new Vector(0,0,0);
		if(forwards_down)  v = VectorMath.addVectors(v, FORWARDS_VECTOR);
		if(backwards_down) v = VectorMath.addVectors(v, BACKWARDS_VECTOR);
		if(right_down)     v = VectorMath.addVectors(v, RIGHT_VECTOR);
		if(left_down)      v = VectorMath.addVectors(v, LEFT_VECTOR);
		if(up_down)        v = VectorMath.addVectors(v, UP_VECTOR);
		if(down_down)      v = VectorMath.addVectors(v, DOWN_VECTOR);
		return v;
	}
	
	
	/**
	 * Calculates the CHANGE in rotation according to the received inputs since the last time this method was called.
	 * @return the DELTA rotation
	 */
	public Vector getDeltaRot(){ //this method needs to take current rotation into account
		long currentTime = System.nanoTime();
		double deltaTime = (currentTime - lastRotTime) / 1000000000.0; //time delta since last method call in seconds
		this.lastRotTime = currentTime;
		if(Mouse.isButtonDown(0)){
			double xRot = -Mouse.getDY();
			double yRot = Mouse.getDX();
			double zRot = 0; //Mouse.getDWheel();
			Vector rawRot = new Vector(xRot,yRot,zRot);
			double multiplier = deltaTime * Player.MAX_ROT_SPEED;
			return VectorMath.multiply(rawRot, multiplier);
		}
		return new Vector(0,0,0);
	}
	
	
	
}
