package com.AtomicGE.game;

import com.AtomicGE.modernRender.renderObject.RenderObject;
import com.AtomicGE.mathUtil.Vector;
import com.AtomicGE.terrain.Sector;
import com.AtomicGE.terrain.Sky;
import com.AtomicGE.terrain.Terrain;

public class World {
	
	private Terrain terrain;
	private long seed;
	private Vector loadCenter; //currently unused
	private Sky sky;
	
	World(long seed, int loadDiameter){
		this.seed = seed;
		this.loadCenter = new Vector(0,0,0);
		this.terrain = new Terrain(seed, loadDiameter, this.loadCenter);
		this.sky = new Sky();
	}
	
	/**
	 * Gets the RenderObjects this world needs to render
	 * @return an array of RenderObjects to render
	 */
	public RenderObject[] getRenderObjects(){
		RenderObject[] renderObjects = new RenderObject[terrain.getRenderObjects().length + 1];
		RenderObject[] terrainRObjects = terrain.getRenderObjects();
		renderObjects[0] = sky.getRenderObject(); //skybox must be first
		for(int i = 0; i < terrainRObjects.length; i++){
			renderObjects[i+1] = terrainRObjects[i];
		}
		return renderObjects;
	}
	
	
	/**
	 * Keeps the proper sectors loaded around the center
	 * @param center the vector describing the center of the loaded sectors
	 */
	public void updateWorld(Vector center){
		this.terrain.updateSectors(center);
	}
	
	/**
	 * Gets the Sector at the given x and z coordinates, does not recreate the Sector if it is already loaded.
	 * @param x the x coordinate
	 * @param z the z coordinate
	 * @return the Sector at x,z
	 */
	public Sector getSector(int x, int z){
		return terrain.getSector(x,z);
	}
	
	/**
	 * 
	 * @return the long seed for this world object
	 */
	public long getSeed(){
		return this.seed;
	}
	
	/**
	 * NOTE: currently unused
	 * @return the Vector representing the center of the loaded sectors
	 */
	public Vector getLoadCenter(){
		return this.loadCenter;
	}
	
}
