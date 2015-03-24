package com.AtomicGE.physics.collisionDetection;

import com.AtomicGE.mathUtil.Vector;
import com.AtomicGE.mathUtil.VectorMath;

public class SphereCollisionDetector extends CollisionDetector{
	
	private double radius;
	
	public SphereCollisionDetector(Vector position, Vector rotation, double radius){
		super(position, rotation);
		this.radius = radius;
	}
	
	
	
	/**
	 * Creates a CollisionReport detailing the interaction between the two CollisionDetectors
	 * @param sphere the CollisionDetector which was collided with
	 * @return a CollisionReport detailing the status of a collision between this CollisionDetector and the given one
	 */
	public CollisionReport getCollisionReport(SphereCollisionDetector sphere){
		Vector distanceVector = VectorMath.subtractVectors(super.position, sphere.getPosition());
		double distance = VectorMath.magnitude(distanceVector);
		boolean intersect = CollisionMath.isColliding(this, sphere);
		CollisionReport collisionReport = new CollisionReport(sphere, this, distance, intersect);
		return collisionReport;
	}
	
	
	
	
	public CollisionReport getCollisionReport(AABBCollisionDetector aabb){
		
	}
	
	
	
	/**
	 * 
	 * @return the radius of this SphereCollisionDetector
	 */
	public double getRadius(){
		return this.radius;
	}
	
}
