package com.AtomicGE.modernRender.model;

import com.AtomicGE.modernRender.texture.Texture;

public class VAO {
	
	private int address;
	private int numVerticies;
	private Texture[] texturesUsed;
	
	
	/**
	 * Creates an object containing the data needed to use a VAO.
	 * This constructor does not create a Vertex Array Object.
	 * @param address The address of the VAO
	 * @param numVertices the number of vertices in the VAO
	 * @param texturesUsed an array of Textures which are used by the VAO
	 */
	VAO(int address, int numVertices, Texture[] texturesUsed){
		this.address = address;
		this.numVerticies = numVertices;
		this.texturesUsed = texturesUsed;
	}
	
	/**
	 * 
	 * @return the openGL recognized address of this VAO
	 */
	public int getAddress(){
		return this.address;
	}
	
	
	/**
	 * 
	 * @return the number of vertices used by this VAO
	 */
	public int numVertices(){
		return this.numVerticies;
	}
	
	
	/**
	 * 
	 * @return an array of Textures used by this VAO
	 */
	public Texture[] getTextures(){
		return this.texturesUsed;
	}
	
	
}
