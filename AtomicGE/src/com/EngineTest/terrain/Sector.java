package com.EngineTest.terrain;

import com.AtomicGE.modernRender.model.Model;
import com.AtomicGE.modernRender.model.ModelMaker;
import com.AtomicGE.modernRender.render.RenderQueue;
import com.AtomicGE.modernRender.renderObject.RenderObject;
import com.AtomicGE.mathUtil.Vector;
import com.EngineTest.game.MaterialLibrary;

public class Sector {
	
	public static final int POINTS_ACROSS_SECTOR = 32;
	public static final double DISTANCE_BETWEEN_HEIGHTMAP_POINTS = 1; //the world distance between points in the heightMap
	public static final double SECTOR_WIDTH = POINTS_ACROSS_SECTOR * DISTANCE_BETWEEN_HEIGHTMAP_POINTS;
	public static final double SEA_LEVEL = 0;
	
	private Vector absPos;
	private Vector sectorPos;
	private TerrainMap terrainMap;
	private RenderObject renObject;
	private RenderQueue renQueue;
	
	
	Sector(TerrainMap terrainMap, RenderQueue renderQueue, MaterialLibrary matLib){
		this.renQueue = renderQueue;
		this.sectorPos = terrainMap.getTerrainPosition();
		this.absPos = new Vector(sectorPos.getIHat()*SECTOR_WIDTH,sectorPos.getJHat()*SECTOR_WIDTH,sectorPos.getKHat()*SECTOR_WIDTH);
		this.terrainMap = terrainMap;
		Vector rot = new Vector(0,0,0);
		Model model = ModelMaker.makeModel(terrainMap,DISTANCE_BETWEEN_HEIGHTMAP_POINTS, matLib);
		setRenderObject(createRenderObject(model, absPos, rot)); //use setRenderObject to eliminate race condition
	}
	
	
	public synchronized void setRenderObject(RenderObject renderObject){
		this.renObject = renderObject;
	}
	
	
	private RenderObject createRenderObject(Model model, Vector pos, Vector rot){
		Thread initalizationThread = new RenderObjectInitalizationThread(model, pos, rot, this, renQueue);
		initalizationThread.start();
		return RenderObject.BLANK_RENDEROBJECT;
	}
	
	
	/**
	 * Gets the stored RenderObject for this Sector
	 * @return a RenderObject for this Sector
	 */
	public RenderObject getRenderObject(){
		return this.renObject;
	}
	
	
	/**
	 * Gets the terrainMap of this Sector
	 * @return a TerrainMap object representing this Sector's terrain
	 */
	public TerrainMap getTerrainMap(){
		return this.terrainMap;
	}
	
	/**
	 * Gets this Sector's position relative to other sectors.
	 * @return a Vector describing this Sector's position relative to other sectors
	 */
	public Vector getSectorPosition(){
		return this.sectorPos;
	}
	
	/**
	 * Gets this Sector's world position vector
	 * @return a position vector
	 */
	public Vector getWorldPosition(){
		return this.absPos;
	}
	
	public String toString(){
		return "Sector: x: " + (int)this.getSectorPosition().getIHat() + " y: " + (int)this.getSectorPosition().getKHat();
	}
	
}
