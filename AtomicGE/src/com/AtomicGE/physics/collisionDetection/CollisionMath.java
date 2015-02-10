package com.AtomicGE.physics.collisionDetection;

import com.AtomicGE.mathUtil.Vector;
import com.AtomicGE.mathUtil.VectorMath;

/**
 * 
 * @author Justin
 *This static class exists to calculate collisions between different Collision Detectors.
 *This means each method gets written once instead of twice and helper functions can be reused.
 */
public class CollisionMath {
	
	
	/**
	 * Determines whether or not two spheres are colliding.
	 * @param sphere1 The first sphere to check
	 * @param sphere2 The second sphere to check
	 * @return true if they intersect, false otherwise
	 */
	public static boolean isColliding(SphereCollisionDetector sphere1, SphereCollisionDetector sphere2){
		Vector distanceVector = VectorMath.subtractVectors(sphere1.getPosition(), sphere2.getPosition());
		double distance = VectorMath.magnitude(distanceVector);
		boolean intersect = distance <= sphere1.getRadius() + sphere2.getRadius() ? true : false;
		return intersect;
	}
	
	
	
	/**
	 * Determines whether or not a sphere and an AABB are colliding.
	 * @param sphere the sphere to check
	 * @param aabb the Axis Aligned Bounding Box to check
	 * @return true if they intersect, false otherwise
	 */
	public static boolean isColliding(SphereCollisionDetector sphere, AABBCollisionDetector aabb){
		double radius = sphere.getRadius();
		AABBCollisionDetector expanded = aabb.getExpanded(radius);
		boolean intersect = isColliding(sphere.getPosition(), expanded);
		return intersect;
	}
	
	
	public static boolean isColliding(AABBCollisionDetector aabb1, AABBCollisionDetector aabb2){
		
	}
	
	
	/**
	 * Determines whether or not a point is within an Axis Aligned Bounding Box.
	 * @param point a Vector describing a point in space
	 * @param aabb the AABB Collision Detector to test if the point is within
	 * @return true if the point lies within the AABB, false otherwise
	 */
	public static boolean isColliding(Vector point, AABBCollisionDetector aabb){
		Vector low  = aabb.getPositionLow();
		Vector high = aabb.getPositionHigh();
		boolean xIntersect = inBetween(low.getIHat(), high.getIHat(), point.getIHat());
		boolean yIntersect = inBetween(low.getJHat(), high.getJHat(), point.getJHat());
		boolean zIntersect = inBetween(low.getKHat(), high.getKHat(), point.getKHat());
		return xIntersect && yIntersect && zIntersect;
	}
	
	
	/**
	 * Calculates if a value is between two numbers.
	 * @param small the small boundary number
	 * @param big the large boundary number
	 * @param value the number to check if it is between big and small
	 * @return true if value falls between big and small
	 */
	private static boolean inBetween(double small, double big, double value){
		return value > small && value < big;
	}
	
}
