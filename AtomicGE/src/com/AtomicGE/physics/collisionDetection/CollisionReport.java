package com.AtomicGE.physics.collisionDetection;

public class CollisionReport {
	
	
	private CollisionDetector a;
	private CollisionDetector b;
	private boolean intersecting;
	private double penetrationDistance;
	
	
	CollisionReport(CollisionDetector a, CollisionDetector b, double penetrationDistance, boolean intersecting){
		this.a = a;
		this.b = b;
		this.penetrationDistance = penetrationDistance;
		this.intersecting = intersecting;
	}
	
	
	
	
	
	
	
	/**
	 * 
	 * @return the Collision Detector which instigated the collision
	 */
	public CollisionDetector getCollisionDetectorA(){
		return this.a;
	}
	
	
	/**
	 * 
	 * @return the Collision Detector which was collided with
	 */
	public CollisionDetector getCollisionDetectorB(){
		return this.b;
	}
	
	
	/**
	 * 
	 * @return the distance CollisionDetector a collided into CollisionDetector B
	 */
	public double getPenetrationDistace(){
		return this.penetrationDistance;
	}
	
	
	/**
	 * 
	 * @return whether or not the two CollisionDetectors where intersecting, true if they were, false if not
	 */
	public boolean isIntersecting(){
		return this.intersecting;
	}
	
}
