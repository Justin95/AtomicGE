package com.AtomicGE.terrain;

import com.AtomicGE.mathUtil.Vector;
import com.AtomicGE.mathUtil.VectorMath;

/**
 * 
 * @author Justin   Github: Justin95
 *
 *Stores a square of Terrain data. Information stored includes height, and material.
 *Stores extra points of terrain around the edges, referred to as hidden edges.
 *Hidden edges cannot be accessed and are used only to prevent border issues.
 */
public class TerrainMap {
	
	private double[][] heights;
	private TerrainMaterial[][] materials;
	private Vector[][] normals;
	private int width;
	
	/**
	 * Creates a TerrainMap which stores information about a square of terrain. 
	 * Each TerrainMap will contain one more point in each direction around it to prevent border issues (hidden edges).
	 * @param heights a double[][] which stores heights. Relevant heights should be between 1 and length - 1. 
	 * @param materials a TerrainMaterial[][] which contains information about which material makes up the terrain.
	 */
	public TerrainMap(double[][] heights, TerrainMaterial[][] materials){
		this.width = heights.length - 2; //width does not count hidden edges, hence -2
		this.heights = heights;
		this.materials = materials;
		this.normals = createNormals(heights);
	}
	
	
	/**
	 * Creates a grid of Vectors which are perpendicular to the grid of heights.
	 * @param heightMap an array of arrays of doubles which represent heights.
	 * @return a new array of arrays of Vectors representing surface normals
	 */
	private Vector[][] createNormals(double[][] heightMap){
		Vector[][] normals = new Vector[heightMap[0].length - 2][heightMap.length - 2]; //normals don't cover hidden edges
		for(int i = 1; i < heightMap.length - 1; i++){
			for(int j = 1; j < heightMap[0].length - 1; j++){
				normals[i-1][j-1] = calculateNormal(i, j, heightMap);
			}
		}
		return normals;
	}
	
	
	private Vector calculateNormal(int x, int z, double[][] heightMap){
		double height = heightMap[x][z];
		Vector xPlus = new Vector( 1, heightMap[x+1][z] - height, 0); //create cardinal vectors
		Vector xNega = new Vector(-1, heightMap[x-1][z] - height, 0);
		Vector zPlus = new Vector( 0, heightMap[x][z+1] - height, 1);
		Vector zNega = new Vector( 0, heightMap[x][z-1] - height,-1);
		Vector plusCross    = VectorMath.crossProduct(zPlus, xPlus); //cross product of positive vectors
		Vector negaCross    = VectorMath.crossProduct(zNega, xNega); //I finally get to name a variable "NegaCross"
		Vector topLeftCross = VectorMath.crossProduct(xPlus, zNega);
		Vector botRighCross = VectorMath.crossProduct(xNega, zPlus);
		Vector normal = VectorMath.average(plusCross, negaCross, topLeftCross, botRighCross); //the normal is the average of the cross products
		return VectorMath.getUnitVector(normal);
	}
	
	
	/**
	 * @param x the x coordinate to retrieve the height at
	 * @param z the z coordinate to retrieve the height at
	 * @return the height at a given point x, z relative to this TerrainMap. Indices start at 0.
	 */
	public double heightAt(int x, int z){
		return this.heights[x+1][z+1];
	}
	
	
	/**
	 * 
	 * @param x the x coordinate to retrieve the TerrainMaterial at
	 * @param z the z coordinate to retrieve the TerrainMaterial at
	 * @return The TerrainMaterial at the given x, z. Indices start at 0.
	 */
	public TerrainMaterial materialAt(int x, int z){
		return this.materials[x+1][z+1];
	}
	
	
	/**
	 * 
	 * @param x the x coordinate to retrieve the Normal Vector at
	 * @param z the z coordinate to retrieve the Normal Vector at
	 * @return The normal Vector at a given x, z. Indices start at 0.
	 */
	public Vector normalAt(int x, int z){
		return this.normals[x][z];
	}
	
	
	/**
	 * 
	 * @return the width of this TerrainMap, does not include hidden edges
	 */
	public int getWidth(){
		return this.width;
	}
	
	
}
