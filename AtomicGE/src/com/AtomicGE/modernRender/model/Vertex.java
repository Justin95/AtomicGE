package com.AtomicGE.modernRender.model;

import java.awt.Color;

import com.AtomicGE.mathUtil.Vector;
import com.AtomicGE.modernRender.texture.Texture;

public class Vertex {
	
	private Vector position;
	private Vector textureCoord;
	private Color  color;
	private Texture texture;
	private Vector normal;
	
	Vertex(Vector position, Vector normal, Vector textureCoord, Color color, Texture texture){
		this.position = position;
		this.textureCoord = textureCoord;
		this.color = color;
		this.texture = texture;
		this.normal = normal;
	}
	
	
	
	/**
	 * @return the position Vector of this Vertex
	 */
	public Vector getPosition() {
		return position;
	}
	
	/**
	 * @return the texture coordinate Vector of this Vertex
	 */
	public Vector getTextureCoord() {
		return textureCoord;
	}
	
	/**
	 * @return the color of this Vertex, described by a java.awt.Color object
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * 
	 * @return the texture of this Vertex
	 */
	public Texture getTexture(){
		return this.texture;
	}
	
	
	/**
	 * 
	 * @return the normal Vector of this vertex
	 */
	public Vector getNormal(){
		return this.normal;
	}
	
}
