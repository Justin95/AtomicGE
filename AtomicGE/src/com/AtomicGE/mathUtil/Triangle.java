package com.AtomicGE.mathUtil;

public class Triangle {
	
	private Vector a;
	private Vector b;
	private Vector c;
	
	
	public Triangle(Vector a, Vector b, Vector c){
		this.a = a;
		this.b = b;
		this.c = c;
	}
	
	
	public String toString(){
		return "Triangle: [" + a + " " + b + " " + c + "]"; 
	}
	
	
	/**
	 * 
	 * @return The first point of the triangle
	 */
	public Vector getA() {
		return a;
	}


	public void setA(Vector a) {
		this.a = a;
	}

	/**
	 * 
	 * @return The second point of the triangle
	 */
	public Vector getB() {
		return b;
	}


	public void setB(Vector b) {
		this.b = b;
	}

	/**
	 * 
	 * @return The third point of the triangle
	 */
	public Vector getC() {
		return c;
	}


	public void setC(Vector c) {
		this.c = c;
	}
	
	
}
