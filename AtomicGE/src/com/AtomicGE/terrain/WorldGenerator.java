package com.AtomicGE.terrain;

import com.AtomicGE.mathUtil.PerlinNoise3D;
import com.AtomicGE.mathUtil.Vector;

public class WorldGenerator {
	
	private long seed;
	private PerlinNoise3D[] noises;
	private PerlinNoise3D cliffNoise;
	private PerlinNoise3D roundNoise;
	
	public WorldGenerator(long seed){
		this.seed = seed;
		this.cliffNoise = new PerlinNoise3D(seed-1, 100, 100, 1, 1);
		this.roundNoise = new PerlinNoise3D(seed-2, 200, 200, 1, 10);
		this.noises = new PerlinNoise3D[]{
				new PerlinNoise3D(seed  ,10000.0,10000.0,1,500),
				new PerlinNoise3D(seed+1,5000.00,5000.00,1,250),
				new PerlinNoise3D(seed+2,1000.00,1000.00,1,200 ),
				new PerlinNoise3D(seed+3,500.000,500.000,1,100 ),
				new PerlinNoise3D(seed+4,100.000,100.000,1,20  ),
				new PerlinNoise3D(seed+5,50.0000,50.0000,1,10  ),
				new PerlinNoise3D(seed+6,10.0000,10.0000,1,2   ),
		};
		
	}
	
	
	
	
	/**
	 * Evaluates the height at the given x,y coordinates
	 * @param x the x coordinate to evaluate at
	 * @param y the y coordinate to evaluate at
	 * @return the height at x,y
	 */
	private double evaluateHeight(double x, double y){
		double height = 0;
		for(PerlinNoise3D noise : noises){
			height += noise.evaluateAt(x, y, 0);
		}
		height = adjustHeight(x,y,height);
		return height;
	}
	
	
	private double adjustHeight(double x, double y, double height){
		double cliffValue = cliffNoise.evaluateAt(x, y, 0);
		double roundValue = roundNoise.evaluateAt(x, y, 0);
		if(cliffValue > .9) height += height + ((cliffValue - roundValue) * 10 * cliffValue - .9);
		if(cliffValue < .1) height += height + ((cliffValue - roundValue) * 10 * cliffValue + .1);
		return height;
	}
	
	
	/**
	 * Generates a new Sector at position x,z
	 * @param x the x coordinate of the new Sector
	 * @param z the z coordinate of the new Sector
	 * @return a new Sector object
	 */
	public Sector generateSector(int x, int z){
		int width = Sector.POINTS_ACROSS_SECTOR + 1;
		double[][] heightMap = new double[width+2][width+2]; //add 2 for hidden edges on each side
		TerrainMaterial[][] materialMap = new TerrainMaterial[width+2][width+2];
		for(int i = 0; i < heightMap.length; i++){
			for(int j = 0; j < heightMap[0].length; j++){
				double xLookup = j * Sector.DISTANCE_BETWEEN_HEIGHTMAP_POINTS + x * Sector.SECTOR_WIDTH; //scales the sector to look between adjacent integer values for heights
				double yLookup = i * Sector.DISTANCE_BETWEEN_HEIGHTMAP_POINTS + z * Sector.SECTOR_WIDTH;
				heightMap[j][i] = evaluateHeight(xLookup, yLookup);
				materialMap[j][i] = selectMaterial(heightMap[j][i]);
			}
		}
		TerrainMap terrain = new TerrainMap(heightMap, materialMap);
		Vector pos = new Vector(x,0,z);
		return new Sector(pos,terrain);
	}
	
	
	private TerrainMaterial selectMaterial(double height){
		if(height < Sector.SEA_LEVEL + 5) return TerrainMaterial.SAND;
		if(height > 200) return TerrainMaterial.STONE;
		return TerrainMaterial.GRASS;
	}
	
	
	/**
	 * 
	 * @return the long seed for this WorldGenerator object
	 */
	public long getSeed(){
		return this.seed;
	}
	
}
