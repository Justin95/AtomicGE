package com.AtomicGE.physics.collisionDetection;

import com.AtomicGE.mathUtil.Vector;

public abstract class CollisionDetector {
	
	private static Class[] collisionDetectorTypes;
	
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
	CollisionDetector(Vector position, Vector rotation){
		this.position = position;
		this.rotation = rotation;
	}
	
	//each subclass must implement methods for colliding with each subclass
	public abstract CollisionReport getCollisionReport(SphereCollisionDetector sphere);
	
	public abstract CollisionReport getCollisionReport(AABBCollisionDetector aabb);
	
	/**
	 * Does a collision check against any type of collision detector
	 * @param colDet
	 * @return
	 */
	public CollisionReport getCollisionReport(CollisionDetector colDet){
		for()
	}
	
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
	
	
	
}
