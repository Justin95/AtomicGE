package com.AtomicGE.physics.physicsEngine;

import com.AtomicGE.mathUtil.Vector;
import com.AtomicGE.mathUtil.VectorMath;

public class PhysicsObject {
	
	
	private Vector position;
	private Vector velocity;
	private PhysicsReigon surroundingReigon; //optional
	
	public PhysicsObject(Vector pos){
		this.position = pos;
		this.velocity = new Vector(0, 0, 0);
	}
	
	public PhysicsObject(Vector pos, Vector vel){
		this(pos);
		this.velocity = vel;
	}
	
	
	public void tick(){
		this.position = VectorMath.addVectors(position, velocity);
	}
	
	
}
