package com.AtomicGE.modernRender.model;

import java.util.ArrayList;

import com.AtomicGE.modernRender.texture.Texture;


/**
 *Model objects contain the data necessary to render themselves.
 *They do not do the rendering themselves but rather give the information
 *to a Renderer which requests it.
 * 
 * @author Justin Bonner
 */
public class Model {
	
	private int shaderProgram;
	private VAO vaoInfo;
	private ArrayList<ModelTriangle> triangles;
	private boolean initalized;
	
	/**
	 * Constructor to create a Model. Contains rendering information.
	 * @param triangles a List of ModelTriangles to make a VAO out of
	 * @param shaderProgramID the integer ID of a shaderProgram who's inputs are associated with this model
	 */
	public Model(ArrayList<ModelTriangle> triangles, int shaderProgramID){
		this.shaderProgram = shaderProgramID;
		this.triangles = triangles;
		this.initalized = false;
	}
	
	
	/**
	 * Initialize this model by creating a VAO and sending it to the GPU.
	 * This method must be called in a thread with an openGL rendering context.
	 */
	public void initialize(){
		this.vaoInfo = VAOcreater.createVAO(triangles, shaderProgram);
		this.initalized = true;
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
		return this.vaoInfo.getAddress();
	}
	
	
	
	/**
	 * Gets the number of indices this model's VAO is meant to render.
	 * @return the number of indices this model's VAO is meant to render
	 */
	public int getNumIndices(){
		return this.vaoInfo.numVertices();
	}
	
	
	/**
	 * 
	 * @return an array of Textures used by this Model
	 */
	public Texture[] getTextures(){
		return this.vaoInfo.getTextures();
	}
	
	public boolean isInitialized(){
		return this.initalized;
	}
	
}
