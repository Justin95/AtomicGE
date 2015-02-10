package com.AtomicGE.modernRender.model;

import java.awt.Color;
import java.util.ArrayList;

import com.AtomicGE.mathUtil.Vector;
import com.AtomicGE.mathUtil.VectorMath;
import com.AtomicGE.modernRender.GPUprograms.Shaders;
import com.AtomicGE.modernRender.texture.Texture;
import com.AtomicGE.modernRender.texture.Textures;
import com.AtomicGE.terrain.Sector;

public class ModelMaker {
	
	public static double MAX_GRASS_HEIGHT = 100;
	public static double CLIFF_SLOPE = 1;
	
	
	/**
	 * Creates a model from the given heightMap
	 * @param heightMap an array of arrays of doubles, which describe heights
	 * @return a com.OpenGLPractace.modernRender.model.Model object which describes how to render the given heightMap
	 */
	public static Model makeModel(double[][] heightMap,double distanceBetweenPoints){
		int shaderProgramID = Shaders.MESH_SHADER_PROGRAM;
		ArrayList<ModelTriangle> triangles = new ArrayList<ModelTriangle>();
		for(int i = 0; i < heightMap.length - 1; i++){
			for(int j = 0; j < heightMap[0].length - 1; j++){
				ModelTriangle[] twoTriangles = createHeightMapSquare(i, j, heightMap, distanceBetweenPoints);
				triangles.add(twoTriangles[0]);
				triangles.add(twoTriangles[1]);
			}
		}
		addOcean(triangles, distanceBetweenPoints * (heightMap.length - 1));
		Model model = new Model(triangles,shaderProgramID);
		return model;
	}
	
	
	
	/**
	 * Creates the 2 ModelTriangles to the top right of the given point of the given heightMap.
	 * @param x the x coordinate
	 * @param z the z coordinate
	 * @param heightMap an array of arrays of doubles representing heights
	 * @param distance the distance between points in the heightMap
	 * @return an array of ModelTriangle objects of length 2
	 */
	private static ModelTriangle[] createHeightMapSquare(int x, int z, double[][] heightMap,double distance){
		Color triangleColor = Color.WHITE;
		Texture texture = getMaterialTexture(x, z, heightMap);
		
		Vector pos  = new Vector(x * distance,heightMap[z][x],z*distance);
		Vector norm = getNormal(x, z, heightMap);
		Vector texCoord = new Vector(x,z,0);
		Vertex v1   = new Vertex(pos,norm,texCoord,triangleColor, texture);
		
		pos         = new Vector((x+1)*distance,heightMap[z][x+1],z  *distance);
		norm		=  getNormal(x+1, z  , heightMap);
		texCoord    = new Vector(x+1, z  ,0); 
		Vertex v2   = new Vertex(pos,norm,texCoord,triangleColor, texture);
		
		pos         = new Vector(x * distance,heightMap[z+1][x],(z+1)*distance);
		norm 		=  getNormal(x  ,z+1, heightMap);
		texCoord    = new Vector(x  ,z+1, 0);
		Vertex v3   = new Vertex(pos,norm,texCoord,triangleColor, texture);
		
		pos         = new Vector((x+1)*distance,heightMap[z+1][x+1],(z+1)*distance);
		norm 		=  getNormal( x+1, z+1, heightMap);
		texCoord    = new Vector( x+1, z+1, 0);
		Vertex v4   = new Vertex(pos,norm,texCoord,triangleColor, texture);
		
		
		ModelTriangle modelTri1 = new ModelTriangle(v1,v4,v2);
		ModelTriangle modelTri2 = new ModelTriangle(v1,v3,v4);
		return new ModelTriangle[]{modelTri1,modelTri2};
	}
	
	
	private static Texture getMaterialTexture(int x, int z, double[][] heightMap){
		if(Math.abs(heightMap[z][x] - heightMap[z+1][x+1]) > CLIFF_SLOPE) return Textures.STONE;
		if(Math.abs(heightMap[z+1][x] - heightMap[z][x+1]) > CLIFF_SLOPE) return Textures.STONE;
		else return Textures.GRASS;
	}
	
	
	/**
	 * Creates a triangle for Debugging purposes.
	 * @return
	 */
	public static Model getTestModel(){
		ArrayList<ModelTriangle> triangles = new ArrayList<ModelTriangle>();
		Vertex a = new Vertex(new Vector(0,0,0),new Vector(0,1,0),new Vector(0,0,0), Color.BLUE, Textures.GRASS);
		Vertex b = new Vertex(new Vector(1,0,0),new Vector(0,1,0),new Vector(1,0,0), Color.RED , Textures.GRASS);
		Vertex c = new Vertex(new Vector(1,1,1),new Vector(0,1,0),new Vector(0,1,0), Color.GREEN,Textures.GRASS);
		triangles.add(new ModelTriangle(a,b,c));
		return new Model(triangles,Shaders.MESH_SHADER_PROGRAM);
	}
	
	//dirty temp method
	private static void addOcean(ArrayList<ModelTriangle> triangles, double length){
		double sea = Sector.SEA_LEVEL;
		Vector normal = new Vector(0,1,0);
		Vertex aa = new Vertex(new Vector(0,sea,0), normal, new Vector(0,0,0), Color.WHITE, Textures.WATER);
		Vertex ba = new Vertex(new Vector(length,sea,0), normal, new Vector(1,0,0), Color.WHITE, Textures.WATER);
		Vertex ab = new Vertex(new Vector(0,sea,length), normal,new Vector(0,1,0), Color.WHITE, Textures.WATER);
		Vertex bb = new Vertex(new Vector(length,sea,length), normal, new Vector(1,1,0), Color.WHITE, Textures.WATER);
		ModelTriangle first      = new ModelTriangle(aa,bb,ba);
		ModelTriangle second     = new ModelTriangle(aa,ab,bb);
		ModelTriangle firstBack  = new ModelTriangle(aa,ba,bb);
		ModelTriangle secondBack = new ModelTriangle(aa,bb,ab);
		triangles.add(first);
		triangles.add(second);
		triangles.add(firstBack);
		triangles.add(secondBack);
	}
	
	/**
	 * Calculates the surface normal at a given x and z coordinate on the given heightMap.
	 * @param x the x coordinate
	 * @param z the z coordinate
	 * @param heightMap the array of arrays of doubles representing heights
	 * @return a Vector representing the surface normal at the given point
	 */
	private static Vector getNormal(int x, int z, double[][] heightMap){
		Vector[] adjacentPoints = getAdjacentPoints(x, z, heightMap);
		Vector[] normals = new Vector[adjacentPoints.length];
		Vector point = new Vector(x, heightMap[x][z], z);
		for(int i = 0; i < adjacentPoints.length; i++){
			int j = i < adjacentPoints.length - 1 ? i + 1 : 0;
			Vector adjPoint1 = adjacentPoints[i];
			Vector adjPoint2 = adjacentPoints[j];
			Vector relative1 = VectorMath.subtractVectors(adjPoint1, point);
			Vector relative2 = VectorMath.subtractVectors(adjPoint2, point);
			Vector normal   = getNormal(relative1, relative2);
			normals[i] = normal;
		}
		Vector normal = VectorMath.average(normals);
		return normal;
	}
	
	/**
	 * Gets the normal from two adjacent Vectors
	 * @param adjacent1
	 * @param adjacent2
	 * @return the normal of two adjacent Vectors
	 */
	private static Vector getNormal(Vector adjacent1, Vector adjacent2){
		Vector normal = VectorMath.crossProduct(adjacent1, adjacent2);
		if(normal.getJHat() < 0) normal = VectorMath.getInverse(normal);
		normal = VectorMath.getUnitVector(normal);
		//System.out.println(normal + " " + adjacent1 + " " + adjacent2);
		return normal;
	}
	
	/**
	 * Creates an array of adjacent Vectors from the heightMap
	 * @param x the x coordinate
	 * @param z the z coordinate
	 * @param heightMap
	 * @return an array of Vector points which are adjacent to the given point
	 */
	private static Vector[] getAdjacentPoints(int x, int z, double[][] heightMap){
		//get available directions
		boolean left   = x > 0;
		boolean right  = x < heightMap.length - 1;
		boolean bottom = z > 0;
		boolean top    = z < heightMap[0].length - 1;
		//sum number true
		int sum = 0;
		if(top)    sum++;
		if(right)  sum++;
		if(left)   sum++;
		if(bottom) sum++;
		Vector[] points = new Vector[sum];
		//assign values
		int index = 0;
		if(top){
			points[index] = new Vector(x,   heightMap[x][z+1], z+1);
			index++;
		}
		if(right){
			points[index] = new Vector(x+1, heightMap[x+1][z], z);
			index++;
		}
		if(bottom){
			points[index] = new Vector(x,   heightMap[x][z-1], z-1);
			index++;
		}
		if(left){
			points[index] = new Vector(x-1, heightMap[x-1][z], z);
			index++;
		}
		
		return points;
	}
	
	
}
