package com.EngineTest.terrain;

import com.AtomicGE.mathUtil.PerlinNoise3D;
import com.AtomicGE.mathUtil.Vector;
import com.EngineTest.game.Material;
import com.EngineTest.game.MaterialLibrary;

public class WorldGenerator {
	private static final String WATER_NAME = "Water";
	private static final String STONE_NAME = "Stone";
	private static final String SAND_NAME  = "Sand";
	private static final String GRASS_NAME = "Grass";
	
	private long seed;
	private PerlinNoise3D[] noises;
	private PerlinNoise3D cliffNoise;
	private PerlinNoise3D roundNoise;
	private MaterialLibrary matLib;
	
	public WorldGenerator(long seed, MaterialLibrary matLib){
		this.seed = seed;
		this.cliffNoise = new PerlinNoise3D(seed-1, 100, 100, 1, 1);
		this.roundNoise = new PerlinNoise3D(seed-2, 200, 200, 1, 10);
		this.noises = new PerlinNoise3D[]{
				new PerlinNoise3D(seed  ,8000.0,8000.0,1,800),
				new PerlinNoise3D(seed+1,4000.0,4000.0,1,400),
				new PerlinNoise3D(seed+2,2000.0,2000.0,1,200 ),
				new PerlinNoise3D(seed+3,1000.0,1000.0,1,100 ),
				new PerlinNoise3D(seed+4,500.00,500.00,1,50  ),
				new PerlinNoise3D(seed+5,250.00,250.00,1,25  ),
				new PerlinNoise3D(seed+6,125.00,125.00,1,12   ),
				new PerlinNoise3D(seed+6,62.500,62.500,1,6   ),
				new PerlinNoise3D(seed+6,31.250,31.250,1,3   ),
				new PerlinNoise3D(seed+6,15.800,15.800,1,1   ),
		};
		this.matLib = matLib;
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
	public TerrainMap generateTerrainMap(int x, int z){
		int width = Sector.POINTS_ACROSS_SECTOR + 1;
		double[][] heightMap = new double[width+2][width+2]; //add 2 for hidden edges on each side
		Material[][] materialMap = new Material[width+2][width+2];
		for(int i = 0; i < heightMap.length; i++){
			for(int j = 0; j < heightMap[0].length; j++){
				double xLookup = j * Sector.DISTANCE_BETWEEN_HEIGHTMAP_POINTS + x * Sector.SECTOR_WIDTH; //scales the sector to look between adjacent integer values for heights
				double yLookup = i * Sector.DISTANCE_BETWEEN_HEIGHTMAP_POINTS + z * Sector.SECTOR_WIDTH;
				heightMap[j][i] = evaluateHeight(xLookup, yLookup);
				materialMap[j][i] = selectMaterial(heightMap[j][i]);
			}
		}
		Vector pos = new Vector(x,0,z);
		return new TerrainMap(heightMap, materialMap, pos, matLib.get(WATER_NAME));
	}
	
	
	private Material selectMaterial(double height){
		if(height < Sector.SEA_LEVEL + 5) return matLib.get(SAND_NAME);
		if(height > 200) return matLib.get(STONE_NAME);
		return matLib.get(GRASS_NAME);
	}
	
	
	/**
	 * 
	 * @return the long seed for this WorldGenerator object
	 */
	public long getSeed(){
		return this.seed;
	}
	
}
