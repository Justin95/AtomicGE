package com.AtomicGE.modernRender.GPUprograms;

import static org.lwjgl.opengl.GL20.*;

import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;



public class Shader {
	
	private String name;
	private int shaderID;
	private int shaderType;
	
	Shader(String name,int type){
		this.name = name;
		this.shaderType = type;
		String source = ShaderFileReader.getShaderSource(name);
		CharSequence charSeqSource = source.subSequence(0, source.length() - 1);
		this.shaderID = glCreateShader(type);
		glShaderSource(shaderID, charSeqSource);
		glCompileShader(shaderID);
		errorCheck();
	}
	
	/**
	 * 
	 * @return true if the shader compiled successfully, false otherwise
	 */
	private boolean errorCheck(){
		IntBuffer check = BufferUtils.createIntBuffer(1);
		glGetShader(shaderID,GL_COMPILE_STATUS,check);
		if(check.get() != GL11.GL_TRUE){
			System.out.println("------------------------------------------------");
			System.out.println("MAJOR ERROR: Shader compilation failure: " + name);
			System.out.println("------------------------------------------------");
			String log = glGetShaderInfoLog(shaderID,512);
			System.out.println(log);
			System.out.println("------------------------------------------------");
			return false;
		}
		return true;
	}
	
	
	public int getShaderType(){
		return this.shaderType;
	}
	
	/**
	 * 
	 * @return the openGL recognized integer ID of this already compiled shader
	 */
	public int getShaderID(){
		return this.shaderID;
	}
	
	
}
