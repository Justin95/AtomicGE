package com.EngineTest.terrain;

import com.AtomicGE.mathUtil.Vector;
import com.AtomicGE.modernRender.render.RenderQueue;
import com.AtomicGE.modernRender.renderObject.RenderObject;
import com.EngineTest.game.MaterialLibrary;

public class Terrain {
	
	private long seed;
	private WorldGenerator worldGen;
	private Sector[][] sectors; // sectors[z][x]
	private int loadDiameter;
	private RenderQueue renderQueue;
	private MaterialLibrary matLib;
	
	public Terrain(long seed, int loadDiameter, Vector center, RenderQueue renderQueue, MaterialLibrary matLib){
		this.seed = seed;
		this.matLib = matLib;
		this.worldGen = new WorldGenerator(seed, matLib);
		this.sectors = new Sector[loadDiameter][loadDiameter];
		this.loadDiameter = loadDiameter;
		this.renderQueue = renderQueue;
	}
	
	
	
	/**
	 * Keeps the proper sectors loaded around the center
	 * @param center the vector describing the center of the loaded sectors
	 */
	public void updateSectors(Vector center){
		Sector[][] newSectors = new Sector[loadDiameter][loadDiameter];
		int lowestX = (int) (center.getIHat() / Sector.SECTOR_WIDTH - (loadDiameter/2));
		int lowestZ = (int) (center.getKHat() / Sector.SECTOR_WIDTH - (loadDiameter/2));
		for(int i = 0; i < loadDiameter; i++){
			for(int j = 0; j < loadDiameter; j++){
				newSectors[j][i] = getSector(i + lowestX, j + lowestZ);
			}
		}
		this.sectors = newSectors;
	}
	
	
	/**
	 * Generates a new Sector at the given x and y coordinates
	 * @param x the x coordinate of the sector
	 * @param y the y coordinate of the sector
	 * @return a new Sector at the x and y coordinates
	 */
	private Sector generateSector(int x, int y){
		TerrainMap terrain = this.worldGen.generateTerrainMap(x, y);
		return new Sector(terrain, this.renderQueue, matLib);
	}
	
	
	/**
	 * Gets the Sector at the given x and z coordinates. Either returns the Sector or generates a new Sector if not already created.
	 * @param x the x coordinate of the sector
	 * @param z the z coordinate of the sector
	 * @return the Sector at x,z
	 */
	public Sector getSector(int x, int z){
		for(int i = 0; i < this.sectors.length; i++){
			for(int j = 0; j < this.sectors[0].length; j++){
				if(sectors[j][i] == null) continue;
				Vector pos = sectors[j][i].getSectorPosition();
				if(pos.getIHat() == x && pos.getKHat() == z) return sectors[j][i];
			}
		}
		return this.generateSector(x, z);
	}
	
	
	/**
	 * Gets the seed, used for Terrain Generation, of this Terrain object
	 * @return a long seed
	 */
	public long getSeed(){
		return this.seed;
	}
	
	
	/**
	 * Gets the RenderObjects this world needs to render
	 * @return an array of RenderObjects to render
	 */
	public RenderObject[] getRenderObjects(){
		RenderObject[] objects = new RenderObject[sectors.length * sectors[0].length];
		for(int i = 0; i < sectors.length; i++){
			for(int j = 0 ; j < sectors[0].length; j++){
				objects[i * sectors.length + j] = sectors[j][i].getRenderObject();
			}
		}
		return objects;
	}
	
}
