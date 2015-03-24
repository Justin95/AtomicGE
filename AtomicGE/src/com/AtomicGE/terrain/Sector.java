package com.AtomicGE.terrain;

import com.AtomicGE.modernRender.model.ModelMaker;
import com.AtomicGE.modernRender.renderObject.RenderObject;
import com.AtomicGE.mathUtil.Vector;

public class Sector {
	
	public static final int POINTS_ACROSS_SECTOR = 32;
	public static final double DISTANCE_BETWEEN_HEIGHTMAP_POINTS = 1; //the world distance between points in the heightMap
	public static final double SECTOR_WIDTH = POINTS_ACROSS_SECTOR * DISTANCE_BETWEEN_HEIGHTMAP_POINTS;
	public static final double SEA_LEVEL = 0;
	
	private Vector absPos;
	private Vector sectorPos;
	private TerrainMap terrainMap; //heightMap[x][z]
	private RenderObject renObject;
	
	Sector(Vector pos, TerrainMap terrainMap){
		this.sectorPos = pos;
		this.absPos = new Vector(pos.getIHat()*SECTOR_WIDTH,pos.getJHat()*SECTOR_WIDTH,pos.getKHat()*SECTOR_WIDTH);
		this.terrainMap = terrainMap;
		Vector rot = new Vector(0,0,0);
		this.renObject = new RenderObject(
				ModelMaker.makeModel(terrainMap,DISTANCE_BETWEEN_HEIGHTMAP_POINTS),
				absPos,
				rot  
		);
		
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
	
}
