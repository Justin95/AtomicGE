package com.AtomicGE.mathUtil;

public class Vector {
	
	private double iHat;
	private double jHat;
	private double kHat;
	
	public Vector(double x, double y,double z){
		this.iHat = x;
		this.jHat = y;
		this.kHat = z;
	}
	
	
	
	@Override
	public String toString(){
		return "Vector:  iHat:" + iHat + " jHat:" + jHat + " kHat:" + kHat + " ";
	}
	
	
	/**
	 * 
	 * @return the iHat of this vector
	 */
	public double getIHat() {
		return iHat;
	}
	
	/**
	 * sets the iHat of this vector
	 * @param iHat the new iHat
	 */
	public void setIHat(double iHat) {
		this.iHat = iHat;
	}
	
	/**
	 * 
	 * @return the jHat of this vector
	 */
	public double getJHat() {
		return jHat;
	}
	
	/**
	 * sets the jHat of this vector
	 * @param jHat the new jHat
	 */
	public void setJHat(double jHat) {
		this.jHat = jHat;
	}
	
	/**
	 * 
	 * @return the kHat of this vector
	 */
	public double getKHat(){
		return this.kHat;
	}
	
	/**
	 * sets the kHat of this vector
	 * @param kHat the new kHat
	 */
	public void setKHat(double kHat){
		this.kHat = kHat;
	}
	
}
