package com.AtomicGE.modernRender.model;

import com.AtomicGE.modernRender.texture.Texture;




/**
 * 
 * @author Justin Bonner
 *
 *	This Class represents a Triangle used in models, instead of three points it contains three Vertexes
 *Vertexes contain position,texture coordinate, and color information.
 */
public class ModelTriangle {
	
	private Vertex a;
	private Vertex b;
	private Vertex c;
	
	
	ModelTriangle(Vertex a, Vertex b, Vertex c){
		this.a = a;
		this.b = b;
		this.c = c;
	}
	
	/**
	 * 
	 * @return the openGL recognized integer textureID of this ModelTriangle
	 */
	public int getTextureID(){
		return this.a.getTexture().getTextureID();
	}
	
	
	/**
	 * 
	 * @return the Texture of this ModelTriangle
	 */
	public Texture getTexture(){
		return this.a.getTexture();
	}
	
	/**
	 * 
	 * @return the first Vertex of this ModelTriangle object
	 */
	public Vertex getA() {
		return a;
	}
	
	/**
	 * 
	 * @return the second Vertex of this ModelTriangle object
	 */
	public Vertex getB() {
		return b;
	}
	
	/**
	 * 
	 * @return the third Vertex of this ModelTriangle object
	 */
	public Vertex getC() {
		return c;
	}
	
	
}
