package com.AtomicGE.modernRender.texture;

import com.AtomicGE.IO.TextureFileReader;

public class Textures {
	
	public static Texture GRASS   	 = TextureFileReader.loadTexture("Grass.png");
	public static Texture STONE   	 = TextureFileReader.loadTexture("Stone.png");
	public static Texture GRASS_2 	 = TextureFileReader.loadTexture("Grass256.png");
	public static Texture WATER   	 = TextureFileReader.loadTexture("Water.png");
	public static Texture SAND    	 = TextureFileReader.loadTexture("Sand.png");
	public static Texture NO_TEXTURE = TextureFileReader.loadTexture("NO_TEXTURE.png");
	
}
