package com.AtomicGE.modernRender.GPUprograms;

import static org.lwjgl.opengl.GL20.*;

import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;


public class ShaderProgram {
	
	public static final int POSITION_INDEX       = 0;
	public static final int TEXTURE_COORDS_INDEX = 1;
	public static final int COLOR_INDEX          = 2;
	public static final int TEXTURE_NUMBER       = 3;
	public static final int NORMAL_INDEX         = 4;
	
	private int programID;
	
	
	public ShaderProgram(String vertexShaderName, String fragmentShaderName){
		this.programID = glCreateProgram();
		Shader vertexShader   = new Shader(vertexShaderName,GL_VERTEX_SHADER);
		Shader fragmentShader = new Shader(fragmentShaderName,GL_FRAGMENT_SHADER);
		glAttachShader(programID, vertexShader.getShaderID());
		glAttachShader(programID, fragmentShader.getShaderID());
		setUpVertexShader();
		setUpFragmentShader();
		glLinkProgram(programID);
		glValidateProgram(programID);
		checkError();
	}
	
	
	/**
	 * Checks for an error in linking the program.
	 */
	private void checkError(){
		IntBuffer check = BufferUtils.createIntBuffer(1);
		glGetProgram(programID,GL_LINK_STATUS,check);
		int status = check.get();
		if(status != GL11.GL_TRUE){
			String log = glGetProgramInfoLog(programID,512);
			System.out.println("LINKING ERROR -----------");
			System.out.println(log);
			System.out.println("-------------------------");
		}
	}
	
	
	/**
	 * binds the inputs of the vertex shader to the generic vertex attribute indexes used.
	 * position      = 0
	 * textureCoord  = 1
	 * color         = 2
	 */
	private void setUpVertexShader(){
		GL20.glBindAttribLocation(this.programID, POSITION_INDEX      , "position");
		GL20.glBindAttribLocation(this.programID, TEXTURE_COORDS_INDEX, "textureCoord");
		GL20.glBindAttribLocation(this.programID, COLOR_INDEX         , "color");
	}
	
	
	/**
	 * Binds the Fragment Shader's outputs. May need to change this method with Fragment Shader
	 */
	private void setUpFragmentShader(){
		GL30.glBindFragDataLocation(programID, 0, "outColor");
	}
	
	/**
	 * 
	 * @return the openGL recognized int ID of this program
	 */
	public int getProgramID(){
		return this.programID;
	}
	
	
}
