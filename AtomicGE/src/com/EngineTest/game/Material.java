package com.EngineTest.game;

import com.AtomicGE.modernRender.texture.Texture;

public class Material {
	
	
	private Texture texture;
	
	public Material(Texture texture){
		this.texture = texture;
	}
	
	
	
	public Texture getTexture(){
		return this.texture;
	}
	
	
}
