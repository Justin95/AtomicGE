package com.AtomicGE.physics.collisionDetection;

import com.AtomicGE.mathUtil.Vector;
import com.AtomicGE.mathUtil.VectorMath;

/**
 * 
 * @author Justin Bonner
 *
 *This Class represents an Axis Aligned Bounding Box and acts as a Collision Detector for this AABB.
 */
public class AABBCollisionDetector extends CollisionDetector{
	
	/**
	 * the corner of this Axis Aligned Bounding Box with the lowest value in its position
	 */
	private Vector positionLow;
	/**
	 * the corner of this AABB with the greatest value in its position
	 */
	private Vector positionHigh;
	
	public AABBCollisionDetector(Vector positionLow, Vector positionHigh, boolean free){
		super(positionLow, new Vector(0, 0, 0), free);
		this.positionLow = positionLow;
		this.positionHigh = positionHigh;
	}
	
	
	/**
	 * Creates a CollisionReport detailing the possible intersection of this AABB and the sphere.
	 * @param sphere the sphere to check if this AABB is intersecting
	 * @return a CollisionReport detailing the status of intersection
	 */
	public CollisionReport getCollisionReport(SphereCollisionDetector sphere){
		boolean intersect = CollisionMath.isColliding(sphere, this);
	}
	
	
	/**
	 * Creates a CollisionReport detailing the possible intersection of this AABB and the sphere.
	 * @param aabb the AABB to check if this AABB is intersecting
	 * @return a CollisionReport detailing the status of intersection
	 */
	public CollisionReport getCollisionReport(AABBCollisionDetector aabb){ //note: make static helper class to do collision booleans, and helper to check if point in an aabb
		boolean intersect = CollisionMath.isColliding(this, aabb);
		
	}
 
	
	
	
	/**
	 * 
	 * @return the corner of this Axis Aligned Bounding Box with the lowest value in its position
	 */
	public Vector getPositionLow(){
		return this.positionLow;
	}
	
	/**
	 * 
	 * @return the corner of this AABB with the greatest value in its position
	 */
	public Vector getPositionHigh(){
		return this.positionHigh;
	}
	
	
	/**
	 * Creates a new AABBCollisionDetector who's corners are expanded by the given value in all directions.
	 * This is useful in enough hit box detection methods to merit a method here.
	 * @param expansion the length to extend each of the corners out by in each of the x, y, z directions.
	 * @return a new AABBCollisionDetector
	 */
	public AABBCollisionDetector getExpanded(double expansion){
		Vector growth = new Vector(expansion,expansion,expansion);
		Vector expandedLow  = VectorMath.addVectors(this.positionLow, VectorMath.getInverse(growth));
		Vector expandedHigh = VectorMath.addVectors(this.positionHigh, growth);
		AABBCollisionDetector expandedAABB = new AABBCollisionDetector(expandedLow, expandedHigh, false);
		return expandedAABB;
	}
	
	
	
}
