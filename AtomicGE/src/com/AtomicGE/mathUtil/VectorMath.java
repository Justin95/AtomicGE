package com.AtomicGE.mathUtil;

import java.util.Random;

public class VectorMath {
	
	/**
	 * Makes a new vector which is the addition of v1 and v2, does not modify the original vectors
	 * @param v1 Vector 1
	 * @param v2 Vector 2
	 * @return the sum of v1 and v2
	 */
	public static Vector addVectors(Vector v1, Vector v2){
		double x = v1.getIHat() + v2.getIHat();
		double y = v1.getJHat() + v2.getJHat();
		double z = v1.getKHat() + v2.getKHat();
		return new Vector(x,y,z);
	}
	
	/**
	 * Subtracts v2 from v1 (v1 - v2), does not modify original vectors
	 * @param v1 Vector 1
	 * @param v2 Vector 2
	 * @return Vector 1 minus Vector 2
	 */
	public static Vector subtractVectors(Vector v1, Vector v2){
		double x = v1.getIHat() - v2.getIHat();
		double y = v1.getJHat() - v2.getJHat();
		double z = v1.getKHat() - v2.getKHat();
		return new Vector(x,y,z);
	}
	
	/**
	 * calculates the inverse of the given vector (vector * -1), does not modify original vector
	 * @param v The vector to find the inverse of
	 * @return the inverse of v
	 */
	public static Vector getInverse(Vector v){
		double x = -(v.getIHat());
		double y = -(v.getJHat());
		double z = -(v.getKHat());
		return new Vector(x,y,z);
	}
	
	/**
	 * Calculates the dot product of the two vectors (v1 * v2), does not modify original Vectors.
	 * @param v1 Vector 1
	 * @param v2 Vector 2
	 * @return the dot product of v1 and v2
	 */
	public static double dotProduct(Vector v1, Vector v2){
		double x = v1.getIHat() * v2.getIHat();
		double y = v1.getJHat() * v2.getJHat();
		double z = v1.getKHat() * v2.getKHat();
		return x + y + z;
	}
	
	/**
	 * Calculates the cross product of two vectors (v1 X v2), does not modify the original Vectors.
	 * @param v1 the first Vector to multiply
	 * @param v2 the second Vector to multiply
	 * @return a new Vector which is a cross product of v1 X v2
	 */
	public static Vector crossProduct(Vector v1, Vector v2){
		double x =  (v1.getJHat() * v2.getKHat() - v1.getKHat() * v2.getJHat());
		double y = -(v1.getIHat() * v2.getKHat() - v1.getKHat() * v2.getIHat());
		double z =  (v1.getIHat() * v2.getJHat() - v1.getJHat() * v2.getIHat());
		return new Vector(x,y,z);
	}
	
	
	/**
	 * Calculates the magnitude of the given vector, does not modify the original vector
	 * @param v the vector to find the magnitude of
	 * @return the magnitude of v
	 */
	public static double magnitude(Vector v){
		double x = v.getIHat();
		double y = v.getJHat();
		double z = v.getKHat();
		return Math.sqrt(x * x + y * y + z * z);
	}
	
	/**
	 * Calculates the unit vector which points in the same direction as the given vector, does not modify the original vector
	 * @param v the Vector to find the unit vector for
	 * @return a vector of length 1 which points the same direction as v
	 */
	public static Vector getUnitVector(Vector v){
		double length = magnitude(v);
		double x = v.getIHat() / length;
		double y = v.getJHat() / length;
		double z = v.getKHat() / length;
		return new Vector(x,y,z);
	}
	
	/**
	 * Calculates the vector described by rotating vector v1 by vector rot.
	 * @param v1 the vector to rotate
	 * @param rot a vector describing the rotation (each component in degrees)
	 * @return a new vector equal to the calculated one
	 */
	public static Vector rotateVector(Vector v1, Vector rot){ //note: counterclockwise is positive for each rot measure, also clean this up
		double x1 = v1.getIHat();
		double y1 = v1.getJHat();
		double z1 = v1.getKHat();
		double xRot = Math.toRadians(rot.getIHat());
		double yRot = Math.toRadians(rot.getJHat());
		double zRot = Math.toRadians(rot.getKHat());
		
		double x2 = x1;
		double y2 = (y1 *  Math.cos(xRot)) + (z1 * -Math.sin(xRot));
		double z2 = (y1 *  Math.sin(xRot)) + (z1 *  Math.cos(xRot));
		
		x1 = x2;
		y1 = y2;
		z1 = z2;

		x2 = (x1 *  Math.cos(yRot)) + (z1 *  Math.sin(yRot));
		z2 = (x1 * -Math.sin(yRot)) + (z1 *  Math.cos(yRot));
		
		x1 = x2;
		y1 = y2;
		z1 = z2;
		
		x2 = (x1 *  Math.cos(zRot)) + (y1 * -Math.sin(zRot));
		y2 = (x1 *  Math.sin(zRot)) + (y1 *  Math.cos(zRot));
		
		return new Vector(x2,y2,z2);
	}
	
	/**
	 * Creates a random 2D Vector from the given Random object
	 * @param ran the random object to use (nextDouble is called on it twice)
	 * @return a random 2D Vector of length 1
	 */
	public static Vector getRandom2DVector(Random ran){
		double x = ran.nextDouble();
		double y = ran.nextDouble();
		Vector vector = new Vector(x,y,0);
		vector = getUnitVector(vector);
		return vector;
	}
	
	
	/**
	 * Calculates the component of Vector b that is in the direction of Vector a
	 * @param b Vector b
	 * @param a Vector a
	 * @return the component of b onto a
	 */
	public static double component(Vector b, Vector a){
		double dot = dotProduct(a,b);
		double comp = dot/magnitude(a);
		return comp;
	}
	
	
	/**
	 * Calculates the projection of Vector b onto Vector a
	 * @param b Vector b
	 * @param a Vector a
	 * @return a new vector describing the projection of b onto a
	 */
	public static Vector projected(Vector b, Vector a){
		double comp = component(b,a);
		Vector unitVectorA = getUnitVector(a);
		Vector proj = multiply(unitVectorA,comp);
		return proj;
	}
	
	
	/**
	 * Creates a new vector which is described by vector v * scalar a.
	 * @param v The vector to multiply
	 * @param a The scalar to multiply by
	 * @return a new vector describing v * a
	 */
	public static Vector multiply(Vector v, double a){
		double x = v.getIHat() * a;
		double y = v.getJHat() * a;
		double z = v.getKHat() * a;
		return new Vector(x,y,z);
	}
	
	
	/**
	 * Calculates the average of a set of Vectors.
	 * @param vectors an array of Vectors to take the average of
	 * @return a new Vector which equals the average of the given vectors
	 */
	public static Vector average(Vector... vectors){
		double x = 0;
		double y = 0;
		double z = 0;
		for(Vector vector : vectors){
			x += vector.getIHat();
			y += vector.getJHat();
			z += vector.getKHat();
		}
		x /= vectors.length;
		y /= vectors.length;
		z /= vectors.length;
		Vector average = new Vector(x, y, z);
		return average;
	}
	
	
}
