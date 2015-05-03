package com.AtomicGE.modernRender.texture;

import java.util.HashMap;

import com.AtomicGE.modernRender.render.RenderQueue;

public class TextureLibrary {
	
	public static final String TEXTURE_FILE_EXTENSION = ".png";
	
	
	private HashMap<String, Texture> textures;
	
	/**
	 * Constructs a new TextureLibrary from given String file names.
	 * The file names should not include an extension and will have TextureLibrary.TEXTURE_FILE_EXTENSION
	 * added to the file name before being loaded.
	 * The resulting textures will be associated with the given file name (no file extension).
	 * @param fileNames a String array of file names without file extensions
	 */
	public TextureLibrary(RenderQueue renderQueue, String... fileNames){
		textures = new HashMap<String, Texture>();
		for(String fileName : fileNames){
			Texture t = renderQueue.makeTexture(fileName + TEXTURE_FILE_EXTENSION);
			textures.put(fileName, t);
		}
	}
	
	
	/**
	 * @param textureName the name of the texture to get, usually the file name of the texture,
	 * not including the file extension
	 * @return the Texture object associated with the given textureName
	 */
	public Texture get(String textureName){
		return textures.get(textureName);
	}
	
	/**
	 * Adds a Texture to this TextureLibrary and associates it with a textureName.
	 * @param textureName the name to associate with this texture
	 * @param tex the texture to add
	 */
	public void add(String textureName, Texture tex){
		textures.put(textureName, tex);
	}
	
	
}
