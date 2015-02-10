package com.AtomicGE.physics.collisionDetection;

import com.AtomicGE.mathUtil.Vector;

public abstract class CollisionDetector {
	
	
	protected Vector  position;
	protected Vector  rotation;
	/**
	 * if true then this Collision Detector checks its own collisions every tick. 
	 * If false, this Collision Detector will not be checked for collisions with other Collision Detectors,
	 * other Collision Detectors can still collide with this Collision Detector.
	 */
	protected boolean active;
	
	/**
	 * Sets this Collision Detector's position and rotation Vectors.
	 * @param position the position Vector
	 * @param rotation the rotation Vector
	 * @param active whether or not the Collision Detector checks its own collisions every tick,
	 * note: active Collision Detectors can still collide with this CD.
	 */
	CollisionDetector(Vector position, Vector rotation, boolean active){
		this.position = position;
		this.rotation = rotation;
		this.active   = active;
	}
	
	//each subclass must implement methods for colliding with each subclass
	public abstract CollisionReport getCollisionReport(SphereCollisionDetector sphere);
	
	public abstract CollisionReport getCollisionReport(AABBCollisionDetector aabb);
	
	
	/**
	 * 
	 * @return the position Vector for this Collision Detector
	 */
	public Vector getPosition(){
		return this.position;
	}
	
	
	/**
	 * 
	 * @return the rotation Vector for this Collision Detector
	 */
	public Vector getRotation(){
		return this.rotation;
	}
	
	
	/**
	 * Describes if this Collision Detector checks its own collisions every tick.
	 * @return true if active, false if inactive
	 */
	public boolean isActive(){
		return this.active;
	}
	
}
