package com.AtomicGE.terrain;

import com.AtomicGE.modernRender.texture.Texture;
import com.AtomicGE.modernRender.texture.Textures;

public enum TerrainMaterial {
	
	GRASS		(Textures.GRASS_2),
	STONE		(Textures.STONE),
	SAND		(Textures.SAND)
	;
	
	private Texture texture;
	
	TerrainMaterial(Texture texture){
		this.texture = texture;
	}
	
	
	
	public Texture getTexture(){
		return this.texture;
	}
	
	
}
