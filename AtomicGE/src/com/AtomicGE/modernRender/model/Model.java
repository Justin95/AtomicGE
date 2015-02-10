package com.AtomicGE.modernRender.model;

import java.util.ArrayList;

import com.AtomicGE.modernRender.texture.Texture;


/**
 * 
 * @author Justin Bonner
 *
 *Model objects contain the data necessary to render themselves.
 *They do not do the rendering themselves but rather give the information
 *to a Renderer which requests it.
 *
 */
public class Model {
	
	private int shaderProgram;
	private int vaoAddress;
	private int numIndices;
	private Texture[] textures;
	
	/**
	 * Constructor to create a Model. Contains rendering information.
	 * @param triangles a List of ModelTriangles to make a VAO out of
	 * @param shaderProgramID the integer ID of a shaderProgram who's inputs are associated with this model
	 */
	public Model(ArrayList<ModelTriangle> triangles, int shaderProgramID){
		VAO vaoInfo = VAOcreater.createVAO(triangles, shaderProgramID);
		this.vaoAddress = vaoInfo.getAddress();
		this.numIndices = vaoInfo.numVertices();
		this.textures = vaoInfo.getTextures();
		this.shaderProgram = shaderProgramID;
	}
	
	
	
	
	/**
	 * Gets the openGL ID of the ShaderProgram meant to render this model.
	 * @return an openGL recognized int ID
	 */
	public int getShaderProgramID(){
		return this.shaderProgram;
	}
	
	
	
	/**
	 * Gets the openGL ID of this model's VAO.
	 * @return an openGL recognized int ID
	 */
	public int getVAO(){
		return this.vaoAddress;
	}
	
	
	
	/**
	 * Gets the number of indices this model's VAO is meant to render.
	 * @return the number of indices this model's VAO is meant to render
	 */
	public int getNumIndices(){
		return this.numIndices;
	}
	
	
	/**
	 * 
	 * @return an array of Textures used by this Model
	 */
	public Texture[] getTextures(){
		return this.textures;
	}
	
	
}
